package cn.ling.android.threadpool;

import org.apache.http.client.HttpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class ThreadPool {

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 5;
    private static final long KEEP_ALIVE_TIME = 3000L;
    private static final int CAPACITY = 128;
    private static final BlockingQueue<Runnable> sBLOCKING_QUEUE = new ArrayBlockingQueue(
            CAPACITY, true);

    private static ThreadPool INSTANCE;
    private static ThreadPoolExecutor sMAIN_EXECUTOR;
    private static ThreadPoolExecutor sAUXILIARY_EXECUTOR;
    private static final Map<String, MyFutureTask<Void>> sMAIN_TASKS = new HashMap(4);
    private static final Map<String, MyFutureTask<Void>> sAUXILIARY_TASKS = new HashMap();
    private static final RejectedExecutionHandler sREJECTED_EXECUTION_HANDLER = new ThreadPoolExecutor.DiscardPolicy() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            if (!e.isShutdown()) {
                e.purge();
                if (e.getQueue().size() < CAPACITY) {
                    e.execute(r);
                } else {
                    ThreadPool.sAUXILIARY_EXECUTOR.execute(r);
                }
            }
        }
    };

    public static ThreadPool getInstance() {
        if (null == INSTANCE) {
            synchronized (ThreadPool.class) {
                if (null == INSTANCE) {
                    INSTANCE = new ThreadPool();
                    sMAIN_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE,
                            MAX_POOL_SIZE, KEEP_ALIVE_TIME,
                            TimeUnit.MILLISECONDS, sBLOCKING_QUEUE,
                            sREJECTED_EXECUTION_HANDLER);
                    sAUXILIARY_EXECUTOR = new ThreadPoolExecutor(0,
                            Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
                            new SynchronousQueue());
                }
            }
        }
        return INSTANCE;
    }


    public synchronized void addTask(ATask task) {
        if (task == null) {
            return;
        }
        if (isNeedNewTask(task)) {
            add(task, sAUXILIARY_TASKS, false);
        } else {
            add(task, sMAIN_TASKS, true);
        }
    }

    private void add(ATask task, Map<String, MyFutureTask<Void>> tasks, boolean isMain) {
        MyFutureTask<Void> futureTask = new MyFutureTask<Void>(task);
        if (task.getUrl() != null) {
            MyFutureTask<Void> tmpTask = (MyFutureTask) tasks.get(task.getUrl());
            if (tmpTask != null) {
                if ((tmpTask.isCancelled()) || (tmpTask.isDone())) {
                    tasks.put(task.getUrl(), futureTask);
                    submit(futureTask, isMain);
                } else {
                    tmpTask.getTask().setTaskListener(task.getTaskListener());
                }
            } else {
                tasks.put(task.getUrl(), futureTask);
                submit(futureTask, isMain);
            }
        } else {
            tasks.put(task.toString(), futureTask);
            submit(futureTask, isMain);
        }
    }

    private void release(Map<String, MyFutureTask<Void>> tasks, ThreadPoolExecutor executor) {
        Iterator<MyFutureTask<Void>> iterator = tasks.values().iterator();
        while (iterator.hasNext()) {
            ((MyFutureTask) iterator.next()).cancel(true);
        }
        tasks.clear();
        executor.purge();
    }

    public synchronized void releaseAllWorker() {
        release(sMAIN_TASKS, sMAIN_EXECUTOR);
        release(sAUXILIARY_TASKS, sAUXILIARY_EXECUTOR);
    }


    public synchronized void resetAllWorker() {
        reset(sMAIN_TASKS, sMAIN_EXECUTOR, true);
        reset(sAUXILIARY_TASKS, sAUXILIARY_EXECUTOR, false);
    }

    private void reset(Map<String, MyFutureTask<Void>> tasks, ThreadPoolExecutor executor, boolean isMain) {
        List<ATask> list = new ArrayList();
        Iterator<MyFutureTask<Void>> iterator = tasks.values().iterator();
        MyFutureTask<Void> futureTask = null;
        while (iterator.hasNext()) {
            futureTask = (MyFutureTask) iterator.next();
            if ((futureTask != null) && (!futureTask.isDone()) && (!futureTask.isCancelled())) {
                futureTask.cancel(true);
                list.add(futureTask.getTask());
            }
        }
        executor.purge();

        for (ATask task : list) {
            futureTask = new MyFutureTask(task);
            submit(futureTask, isMain);
        }
    }


    public void submit(MyFutureTask<Void> futureTask, boolean isMain) {
        if (isMain) {
            sMAIN_EXECUTOR.submit(futureTask);
        } else {
            sAUXILIARY_EXECUTOR.submit(futureTask);
        }
    }


    private boolean isNeedNewTask(ATask task) {
        return (task.getPiority() == 2 || task.getPiority() == 1) && sMAIN_EXECUTOR.getActiveCount() == MAX_POOL_SIZE;
    }


    public synchronized void cancelAllTask() {
        cancel(sMAIN_TASKS, sMAIN_EXECUTOR);
    }

    private void cancel(Map<String, MyFutureTask<Void>> tasks, ThreadPoolExecutor executor) {
        Map<String, MyFutureTask<Void>> tmpTasks = new HashMap();
        Iterator<String> iterator = tasks.keySet().iterator();
        String url = null;
        while (iterator.hasNext()) {
            url = (String) iterator.next();

            if ((((MyFutureTask) tasks.get(url)).isBackRunningLevel()) &&
                    (!((MyFutureTask) tasks.get(url)).isCancelled()) &&
                    (!((MyFutureTask) tasks.get(url)).isDone())) {
                tmpTasks.put(url, (MyFutureTask) tasks.get(url));
            } else {
                ((MyFutureTask) tasks.get(url)).cancel(false);
            }
        }
        tasks.clear();
        executor.purge();
        tasks.putAll(tmpTasks);
    }


    private static class Worker implements Runnable {

        private ATask mTask;
        private HttpClient mHttpClient;

        public Worker(ATask task) {
            this.mTask = task;
        }


        @Override
        public void run() {
            if(mHttpClient ==null){
                mTask.run();
            }else{
                mTask.run(mHttpClient);
            }
        }
    }


    private static class MyFutureTask<V> extends FutureTask<V> {

        private ATask mTask;

        public MyFutureTask(ATask task) {
            this(new Worker(task), null);
            this.mTask = task;
        }

        public MyFutureTask(Callable<V> callable) {
            super(callable);
        }

        public MyFutureTask(Runnable runnable, V result) {
            super(runnable, result);
        }


        public boolean isBackRunningLevel() {
            return mTask.getPiority() == 2;
        }

        public ATask getTask() {
            return this.mTask;
        }

    }

}

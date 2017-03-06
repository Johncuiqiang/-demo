package cn.ling.voice.chitchat.nlu;

import java.util.ArrayList;

import cn.ling.android.entities.ThirdPluginBaseDataEntity;

/**
 * Created by cuiqiang on 2016/12/22.
 */

public class NLUResultEntity extends ThirdPluginBaseDataEntity {

    private String answer;
    private String domain;//basic:music:weather:time:date:news:volume:joke:express:move:
    private String code;
    private Param param;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Param getParam() {
        return param;
    }

    public void setParam(Param param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "NLUResultEntity{" +
                "answer='" + answer + '\'' +
                ", domain='" + domain + '\'' +
                ", code='" + code + '\'' +
                ", param=" + param +
                '}';
    }

    public static class Param {

        private String action;
        private String artist;
        private String song;
        private String style;
        private Detail detail;

        @Override
        public String toString() {
            return "Param{" +
                    "action='" + action + '\'' +
                    ", artist='" + artist + '\'' +
                    ", song='" + song + '\'' +
                    ", style='" + style + '\'' +
                    ", detail=" + detail +
                    '}';
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getSong() {
            return song;
        }

        public void setSong(String song) {
            this.song = song;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public Detail getDetail() {
            return detail;
        }

        public void setDetail(Detail detail) {
            this.detail = detail;
        }
    }

    public static class Detail {

        private Condition condition;
        private ArrayList<Weather> forecast;

        public class Weather {

            private String conditionDay;
            private String conditionNight;
            private String tempNight;
            private String tempDay;
            private ArrayList<String> windDirDay;
            private ArrayList<String> windDirNight;
            private ArrayList<String> windLevelNight;
            private ArrayList<String> windLevelDay;

            public String getConditionNight() {
                return conditionNight;
            }

            public void setConditionNight(String conditionNight) {
                this.conditionNight = conditionNight;
            }

            public String getConditionDay() {
                return conditionDay;
            }

            public void setConditionDay(String conditionDay) {
                this.conditionDay = conditionDay;
            }


            public String getTempNight() {
                return tempNight;
            }

            public void setTempNight(String tempNight) {
                this.tempNight = tempNight;
            }

            public String getTempDay() {
                return tempDay;
            }

            public void setTempDay(String tempDay) {
                this.tempDay = tempDay;
            }

            public ArrayList<String> getWindLevelNight() {
                return windLevelNight;
            }

            public void setWindLevelNight(ArrayList<String> windLevelNight) {
                this.windLevelNight = windLevelNight;
            }

            public ArrayList<String> getWindLevelDay() {
                return windLevelDay;
            }

            public void setWindLevelDay(ArrayList<String> windLevelDay) {
                this.windLevelDay = windLevelDay;
            }

            public ArrayList<String> getWindDirDay() {
                return windDirDay;
            }

            public void setWindDirDay(ArrayList<String> windDirDay) {
                this.windDirDay = windDirDay;
            }

            public ArrayList<String> getWindDirNight() {
                return windDirNight;
            }

            public void setWindDirNight(ArrayList<String> windDirNight) {
                this.windDirNight = windDirNight;
            }
        }

        public Condition getCondition() {
            return condition;
        }

        public void setCondition(Condition condition) {
            this.condition = condition;
        }

        public ArrayList<Weather> getForecast() {
            return forecast;
        }

        public void setForecast(ArrayList<Weather> forecast) {
            this.forecast = forecast;
        }

        @Override
        public String toString() {
            return "Detail{" +
                    "condition=" + condition +
                    ", forecast=" + forecast +
                    '}';
        }
    }

    public static class Condition {

        private String windDir;
        private String windLevel;
        private String condition;
        private String humidity;
        private String temp;

        @Override
        public String toString() {
            return "Condition{" +
                    "windDir='" + windDir + '\'' +
                    ", windLevel='" + windLevel + '\'' +
                    ", condition='" + condition + '\'' +
                    ", humidity='" + humidity + '\'' +
                    ", temp='" + temp + '\'' +
                    '}';
        }

        public String getWindDir() {
            return windDir;
        }

        public void setWindDir(String windDir) {
            this.windDir = windDir;
        }

        public String getWindLevel() {
            return windLevel;
        }

        public void setWindLevel(String windLevel) {
            this.windLevel = windLevel;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getTemp() {return temp;}

        public void setTemp(String temp) {this.temp = temp;}
    }

}



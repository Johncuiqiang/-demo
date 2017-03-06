package cn.ling.voice.entities;

import java.util.List;

import cn.ling.android.entities.BaseEntity;

/**
 * Created by David小硕 on 2016/9/29.
 */

public class CategoryListEntity extends BaseEntity {

    private List<CategoryItemEntity> category_list;
    private List<CategoryItemEntity> category_rest;

    public List<CategoryItemEntity> getCategory_list() {
        return category_list;
    }

    public void setCategory_list(List<CategoryItemEntity> category_list) {
        this.category_list = category_list;
    }

    public List<CategoryItemEntity> getCategory_rest() {
        return category_rest;
    }

    public void setCategory_rest(List<CategoryItemEntity> category_rest) {
        this.category_rest = category_rest;
    }

    @Override
    public String toString() {
        return "CategoryListEntity{" +
                "category_list=" + category_list +
                ", category_rest=" + category_rest +
                '}';
    }

    public static class CategoryItemEntity extends BaseEntity{

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "categoryItemEntity{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}

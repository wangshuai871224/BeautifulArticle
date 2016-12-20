package com.example.dllo.hodgepodge.havematter;

import java.util.List;

/**
 * Created by TaiF on 16/12/20.
 */

public class BeanTab {
    /**
     * data : {"categories":[{"sub_categories":[{"id":1,"name":"首饰"},{"id":3,"name":"包袋"},{"id":4,"name":"鞋履"},{"id":2,"name":"配饰"},{"id":27,"name":"家居"},{"id":26,"name":"其他"}],"id":30,"name":"独立设计师"},{"id":31,"name":"大牌设计师"}]}
     * result : 1
     */

    private DataBean data;
    private int result;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public static class DataBean {
        private List<CategoriesBean> categories;

        public List<CategoriesBean> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoriesBean> categories) {
            this.categories = categories;
        }

        public static class CategoriesBean {
            /**
             * sub_categories : [{"id":1,"name":"首饰"},{"id":3,"name":"包袋"},{"id":4,"name":"鞋履"},{"id":2,"name":"配饰"},{"id":27,"name":"家居"},{"id":26,"name":"其他"}]
             * id : 30
             * name : 独立设计师
             */

            private int id;
            private String name;
            private List<SubCategoriesBean> sub_categories;

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

            public List<SubCategoriesBean> getSub_categories() {
                return sub_categories;
            }

            public void setSub_categories(List<SubCategoriesBean> sub_categories) {
                this.sub_categories = sub_categories;
            }

            public static class SubCategoriesBean {
                /**
                 * id : 1
                 * name : 首饰
                 */

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
            }
        }
    }
}

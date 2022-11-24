package com.example.fashionboomer.dto;

import java.io.Serializable;
import java.util.List;

public class CategoryBean implements Serializable {

    /**
     * code : 0
     * data :
     */

    private int code;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {


        private String type;
        private String moreIcon;
        private String moduleTitle;
        private String moreTextDisplay;
        private List<DataListBean> dataList;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMoreIcon() {
            return moreIcon;
        }

        public void setMoreIcon(String moreIcon) {
            this.moreIcon = moreIcon;
        }

        public String getModuleTitle() {
            return moduleTitle;
        }

        public void setModuleTitle(String moduleTitle) {
            this.moduleTitle = moduleTitle;
        }

        public String getMoreTextDisplay() {
            return moreTextDisplay;
        }

        public void setMoreTextDisplay(String moreTextDisplay) {
            this.moreTextDisplay = moreTextDisplay;
        }

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean {
            /**
             * type : category
             * title : 제목
             * category : 상위 항목
             * details : 하위 항목
             * imgURL : 이미지
             */

            private String type;
            private String title;
            private String category;
            private String details;
            private String imgURL;

            public DataListBean() {
                super();
            }

            public DataListBean(String type) {
                this.type = type;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTitle() {
                return title;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getDetails() {
                return details;
            }

            public void setDetails(String details) {
                this.details = details;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImgURL() {
                return imgURL;
            }

            public void setImgURL(String imgURL) {
                this.imgURL = imgURL;
            }
        }
    }
}

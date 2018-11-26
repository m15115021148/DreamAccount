package com.romantic.dreamaccount.bean;

import java.util.List;

/**
 * Created by ${chenM} on 2018/11/21.
 */
public class KindResult extends BaseResult {
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data{
        private String kind;
        private int type;//0 income ;1 expenses; 2 both
        private String kindID;

        private boolean isSelect;
        private int res;

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getKindID() {
            return kindID;
        }

        public void setKindID(String kindID) {
            this.kindID = kindID;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public int getRes() {
            return res;
        }

        public void setRes(int res) {
            this.res = res;
        }
    }
}

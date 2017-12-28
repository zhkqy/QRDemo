package com.qr.demo.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenlei on 2017/6/21.
 */
public class CommonCheckBoxModel {

    public String title = "";

    public List<CommonCheckBoxData> data = new ArrayList<>();

    private CommonCheckBoxListener checkBoxListener;

    public CommonCheckBoxModel() {

    }

    public CommonCheckBoxModel(String title, List<CommonCheckBoxData> d) {
        this.title = title;
        this.data.clear();
        if (d != null && d.size() > 0) {
            this.data.addAll(d);
        }
    }

    public List<CommonCheckBoxData> getData() {
        return data;
    }

    public CommonCheckBoxModel setData(List<CommonCheckBoxData> data) {
        this.data = data;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void clearCheckStatus() {
        if (data != null && this.data.size() > 0) {
            for (int x = 0; x < data.size(); x++) {
                data.get(x).setCheck(false);
            }
        }
    }

    public void setDataForPosition(int p) {
        if (data != null && this.data.size() > 0) {
            for (int x = 0; x < data.size(); x++) {
                if (x == p) {
                    data.get(x).setCheck(true);
                }
            }
        }
    }

    public CommonCheckBoxData getCheckData() {
        if (data != null && this.data.size() > 0) {
            for (int x = 0; x < data.size(); x++) {
                if (data.get(x).isCheck()) {
                    return data.get(x);
                }
            }
        }
        return null;
    }

    public CommonCheckBoxListener getCheckBoxListener() {
        return checkBoxListener;
    }

    public CommonCheckBoxModel setCheckBoxListener(CommonCheckBoxListener checkBoxListener) {
        this.checkBoxListener = checkBoxListener;
        return this;
    }

    public interface CommonCheckBoxListener {

        void onChecked(int position, String name);

    }
}

package com.ppyy.weathertest.ui.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/8/30.
 */

public class BaseHeFen {
    @SerializedName("status")
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

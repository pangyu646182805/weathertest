package com.ppyy.weathertest.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/30.
 */

public class HeFenCityBean extends BaseHeFen {
    /**
     * city : 南子岛
     * cnty : 中国
     * id : CN101310230
     * lat : 11.26
     * lon : 114.20
     * prov : 海南
     */
    private List<CityInfoBean> city_info;

    public List<CityInfoBean> getCity_info() {
        return city_info;
    }

    public void setCity_info(List<CityInfoBean> city_info) {
        this.city_info = city_info;
    }

    public static class CityInfoBean {
        private String city;
        private String cnty;
        private String id;
        private String lat;
        private String lon;
        private String prov;
        private String max;
        private String min;
        private String code;
        private String txt;

        public String getMax() {
            return max;
        }

        public void setMax(String max) {
            this.max = max;
        }

        public String getMin() {
            return min;
        }

        public void setMin(String min) {
            this.min = min;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCnty() {
            return cnty;
        }

        public void setCnty(String cnty) {
            this.cnty = cnty;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getProv() {
            return prov;
        }

        public void setProv(String prov) {
            this.prov = prov;
        }
    }
}

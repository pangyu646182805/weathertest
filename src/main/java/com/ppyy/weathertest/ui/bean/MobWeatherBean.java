package com.ppyy.weathertest.ui.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/8/30.
 */

public class MobWeatherBean extends BaseHeFen {

    @SerializedName("msg")
    private String msg;
    @SerializedName("result")
    private List<ResultBean> result;

    public String getMsg() {
        return msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public static class ResultBean {
        @SerializedName("airCondition")
        private String airCondition;
        @SerializedName("city")
        private String city;
        @SerializedName("coldIndex")
        private String coldIndex;
        @SerializedName("date")
        private String date;
        @SerializedName("distrct")
        private String distrct;
        @SerializedName("dressingIndex")
        private String dressingIndex;
        @SerializedName("exerciseIndex")
        private String exerciseIndex;
        @SerializedName("humidity")
        private String humidity;
        @SerializedName("pollutionIndex")
        private String pollutionIndex;
        @SerializedName("province")
        private String province;
        @SerializedName("sunrise")
        private String sunrise;
        @SerializedName("sunset")
        private String sunset;
        @SerializedName("temperature")
        private String temperature;
        @SerializedName("time")
        private String time;
        @SerializedName("updateTime")
        private String updateTime;
        @SerializedName("washIndex")
        private String washIndex;
        @SerializedName("weather")
        private String weather;
        @SerializedName("week")
        private String week;
        @SerializedName("wind")
        private String wind;
        @SerializedName("future")
        private List<FutureBean> future;

        public String getAirCondition() {
            return airCondition;
        }

        public String getCity() {
            return city;
        }

        public String getColdIndex() {
            return coldIndex;
        }

        public String getDate() {
            return date;
        }

        public String getDistrct() {
            return distrct;
        }

        public String getDressingIndex() {
            return dressingIndex;
        }

        public String getExerciseIndex() {
            return exerciseIndex;
        }

        public String getHumidity() {
            return humidity;
        }

        public String getPollutionIndex() {
            return pollutionIndex;
        }

        public String getProvince() {
            return province;
        }

        public String getSunrise() {
            return sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public String getTemperature() {
            return temperature;
        }

        public String getTime() {
            return time;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public String getWashIndex() {
            return washIndex;
        }

        public String getWeather() {
            return weather;
        }

        public String getWeek() {
            return week;
        }

        public String getWind() {
            return wind;
        }

        public List<FutureBean> getFuture() {
            return future;
        }

        public static class FutureBean {
            @SerializedName("date")
            private String date;
            @SerializedName("dayTime")
            private String dayTime;
            @SerializedName("night")
            private String night;
            @SerializedName("temperature")
            private String temperature;
            @SerializedName("week")
            private String week;
            @SerializedName("wind")
            private String wind;

            public String getDate() {
                return date;
            }

            public String getDayTime() {
                return dayTime;
            }

            public String getNight() {
                return night;
            }

            public String getTemperature() {
                return temperature;
            }

            public String getWeek() {
                return week;
            }

            public String getWind() {
                return wind;
            }
        }
    }
}

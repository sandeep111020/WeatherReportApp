package com.example.weatherreportapp.Model;

public class Modelclass {
    private String dt,tempmax,tempmin,hum,pressure;

    public String getDt() {
        return dt;
    }

    public String getTempmax() {
        return tempmax;
    }

    public void setTempmax(String tempmax) {
        this.tempmax = tempmax;
    }

    public String getTempmin() {
        return tempmin;
    }

    public void setTempmin(String tempmin) {
        this.tempmin = tempmin;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }


    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }



    public Modelclass(String dt, String tempmax,String tempmin, String hum, String pressure) {
        this.dt=dt;

        this.hum=hum;
        this.tempmax=tempmax;
        this.tempmin=tempmin;
        this.pressure=pressure;
    }

}
package net.adamcowell14.frogcoin.config;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class Point {
    private String name;
    private float startPeriod;
    private float endPeriod;

    public Point(String name, float startPeriod, float endPeriod) {
        this.name = name;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
    }

    public String getName() {return this.name;}

    public String toJson() {
        Map<String, String> point = new HashMap<>();
        point.put("name", this.name);
        point.put("startPeriod", "" + this.startPeriod);
        point.put("endPeriod", "" + this.endPeriod);
        Gson gson = new Gson();
        return gson.toJson(point);
    }

    public float getStartPeriod() {
        return this.startPeriod;
    }

    public float getEndPeriod() {
        return this.endPeriod;
    }
}
package net.adamcowell14.frogcoin.config;

import com.google.gson.*;
import net.fabricmc.loader.api.FabricLoader;
import net.adamcowell14.frogcoin.Frogcoin;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;
import java.io.FileWriter;
import java.util.Objects;

public class ConfigParser {
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static final ConfigParser INSTANCE = new ConfigParser(FabricLoader.getInstance().getConfigDir().resolve("frogcoin.json").toFile());
    static {
        INSTANCE.load();
    }

    public final File file;

    public ConfigParser(File file) {
        this.file = file;
    }

    public boolean deletePoint(String pointName) {
        boolean isPointHere = false;
        int pointIndex = 0;
        Point[] pointsArr = Frogcoin.points;
        for (int i = 0; i < pointsArr.length; i++) {
            if (Objects.equals(pointsArr[i].getName(), pointName)) {
                isPointHere = true;
                pointIndex = i;
                break;
            }
        }
        if (!isPointHere) {return false;}

        Point[] newPointsArr = new Point[pointsArr.length - 1];

        for (int i = 0, j = 0; i < pointsArr.length; i++) {
            if (i == pointIndex) {
                continue;
            }
            newPointsArr[j++] = pointsArr[i];
        }
        Frogcoin.points = newPointsArr;
        save();
        return true;
    }

    public void addPoint(Point point) {
        Point[] pointsArr = Frogcoin.points;
        Point[] newPointsArr = new Point[pointsArr.length + 1];
        for (int i = 0; i < pointsArr.length; i++) {newPointsArr[i] = pointsArr[i];}
        newPointsArr[pointsArr.length] = point;
        Frogcoin.points = newPointsArr;
        save();
    }

    public void changeDelay(long delay) {
        Frogcoin.delay = delay;
        save();
    }

    public void changeRunning(boolean running) {
        Frogcoin.running = running;
        save();
    }

    public boolean getRunning() {
        return Frogcoin.running;
    }

    public void load() {
        if (file.exists()) {
            try {
                String json_string = Files.readString(Path.of(file.toString()), StandardCharsets.US_ASCII);
                Frogcoin.delay = deltaFromJson(json_string);
                Frogcoin.running = runningFromJson(json_string);
                Frogcoin.points = pointsFromJson(json_string);
            } catch (Exception e) {
                //
            }
        }
        save();
    }

    public void save() {
        //
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(toJson());
        } catch (Exception e) {
            //
        }
    }

    public void getDelay() {
        if (file.exists()) {
            try {
                String json_string = Files.readString(Path.of(file.toString()), StandardCharsets.US_ASCII);
                Frogcoin.delay = deltaFromJson(json_string);
                Frogcoin.running = runningFromJson(json_string);
                Frogcoin.points = pointsFromJson(json_string);
            } catch (Exception e) {
                //
            }
        }
    }

    protected long deltaFromJson(String json_string) {
        JsonParser jsparser = new JsonParser();
        JsonObject object = jsparser.parse(json_string).getAsJsonObject();
        return object.getAsJsonPrimitive("delay").getAsLong();
    }

    protected boolean runningFromJson(String json_string) {
        JsonParser jsparser = new JsonParser();
        JsonObject object = jsparser.parse(json_string).getAsJsonObject();
        return object.getAsJsonPrimitive("running").getAsBoolean();
    }

    protected Point[] pointsFromJson(String json_string) {
        JsonParser jsparser = new JsonParser();
        JsonObject object = jsparser.parse(json_string).getAsJsonObject();
        String newJSONString = object.getAsJsonArray("points").toString();
        return GSON.fromJson(newJSONString, Point[].class);
    }


    protected String toJson(){
        Point[] points = Frogcoin.points;

        String[] jsonReprOfPoints = new String[points.length];
        for(int i = 0; i < points.length; i++) {
            jsonReprOfPoints[i] = points[i].toJson();
        }
        return "{\"delay\":" + Frogcoin.delay + ", \"running\":" + Frogcoin.running + ", \"points\":[" + String.join(",", jsonReprOfPoints)+ "]}";
    }
}
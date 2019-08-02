package com.app.nb.peruclimas.API.Deserializers;

import com.app.nb.peruclimas.Models.City;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class MyDeserializer implements JsonDeserializer<City> {


    /**
     * Deserializador Personalizado
     *
     * @param json
     * @param typeOfT
     * @param context
     * @return
     * @throws JsonParseException
     */
    @Override
    public City deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        int id = json.getAsJsonObject().get("id").getAsInt();
        String name = json.getAsJsonObject().get("name").getAsString();
        String icon = json.getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("icon").getAsString();
        double latitude = json.getAsJsonObject().get("coord").getAsJsonObject().get("lat").getAsDouble();
        double longitude = json.getAsJsonObject().get("coord").getAsJsonObject().get("lon").getAsDouble();
        String description = json.getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
        int temperature = json.getAsJsonObject().get("main").getAsJsonObject().get("temp").getAsInt();
        double windSpeed = json.getAsJsonObject().get("wind").getAsJsonObject().get("speed").getAsDouble();
        double clouds = json.getAsJsonObject().get("clouds").getAsJsonObject().get("all").getAsDouble();
        double humidity = json.getAsJsonObject().get("main").getAsJsonObject().get("humidity").getAsDouble();

        City city = new City();

        city.setId(id);
        city.setName(name);
        city.setIcon(icon);
        city.setLatitude(latitude);
        city.setLongitude(longitude);
        city.setDescription(description);
        city.setTemperature(temperature);
        city.setWindSpeed(windSpeed);
        city.setClouds(clouds);
        city.setHumidity(humidity);

        return city;
    }

}

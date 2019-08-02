package com.app.nb.peruclimas.API;

import com.app.nb.peruclimas.API.Deserializers.MyDeserializer;
import com.app.nb.peruclimas.Models.City;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit Singleton
 */
public class API {

    //Direccion de alojaiento del API
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    //APP KEY generada para usar el servicio
    public static final String APP_KEY = "ffe5b339f1e33ea21f2b7dc33d773d77";

    //Direccion de alojaiento de los iconos
    public static final String BASE_ICON_URL = "http://openweathermap.org/img/w/";

    //Retrofit Singleton
    private static Retrofit retrofit = null;

    /**
     * Obtiene la instancia del objeto Retrofit si existe, de lo contrario la crea una sola vez
     *
     * @return retrofit
     */
    public static Retrofit getApi() {
        if (retrofit == null) {

            //Registra deserializador para la clase City
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(City.class, new MyDeserializer());

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                    .build();
        }
        return retrofit;
    }
}

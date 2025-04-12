package com.Journaling.controller.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Journaling.controller.demo.api.response.WeatherResponse;
import com.Journaling.controller.demo.cache.AppCache;
import com.Journaling.controller.demo.constants.Placeholders;

@Service
public class WeatherService {



    @Value("${weather.api.key}")
    private String apiKey;
    // private static final String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;


    public WeatherResponse getWeather(String city) {
        // String finalAPI = appCache.appCache.get("AppCache.keys.WEATHER_API.toString()").replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apiKey);
        String finalAPI = appCache.appCache.get("WEATHER_API").replace(Placeholders.CITY, city).replace(Placeholders.API_KEY, apiKey);


        




        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.POST, null, WeatherResponse.class);
        WeatherResponse body=response.getBody();
        return body;

    }
}

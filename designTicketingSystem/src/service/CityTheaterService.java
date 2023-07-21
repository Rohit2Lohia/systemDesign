package service;

import lombok.Getter;
import model1.City;
import model1.Theaters;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CityTheaterService {

    private @Getter Map<City, Theaters> cityTheatersMap;

    public CityTheaterService() {
        this.cityTheatersMap = new HashMap<>();
    }


}

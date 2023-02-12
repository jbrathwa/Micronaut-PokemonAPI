package com.example.power;

import jakarta.inject.Singleton;

@Singleton
public class PowerService {

    private final PowerRepository powerRepository;


    public PowerService(PowerRepository powerRepository) {
        this.powerRepository = powerRepository;
    }


    public Power get(String name){
        return powerRepository.findByName(name);
    }
}

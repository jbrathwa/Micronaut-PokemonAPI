package com.example.Pokemon;

import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class PokemonRepository {
    List<Pokemon> store;

    public PokemonRepository() {
        this.store = new ArrayList<>();
        store.add(new Pokemon(1,"Bulbsaur","Water","SomeUrl"));
        store.add(new Pokemon(2,"Ivyasaur","Posion","SomeUrl"));

    }

    public List<Pokemon> get() {
        return store;
    }
}

package com.example.pokemon;

import com.example.exception.PokemonValidationException;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Singleton
public class PokemonService {

  private static final Logger log = LoggerFactory.getLogger(Pokemon.class);

  private final PokemonRepository pokemonRepository;

  public PokemonService(PokemonRepository pokemonRepository) {

    this.pokemonRepository = pokemonRepository;
  }

  public List<Pokemon> get() {
    return (List<Pokemon>) pokemonRepository.findAll();
  }

  public Pokemon create(PokemonCreationForm pokemonForm) {
    boolean isPokemonExist = pokemonRepository.existsByNameIgnoreCase(pokemonForm.getName());
    if (isPokemonExist) {
      throw new PokemonValidationException(
          "Pokemon With name: " + pokemonForm.getName() + " Already Exist");
    }

    Pokemon pokemon = new Pokemon();
    pokemon.setName(pokemonForm.getName());
    pokemon.setPower(pokemonForm.getPower());
    pokemonRepository.save(pokemon);
    return pokemon;
  }

  public Pokemon getById(Integer id) {
    return pokemonRepository
        .findById(id)
        .orElseThrow(() -> new PokemonValidationException("Pokemon with id: " + id + " Not found"));
  }

  public void update(Pokemon pokemon) {
    Pokemon pokemonWithId = pokemonRepository.findById(pokemon.getId()).orElseThrow(()->new PokemonValidationException("Pokemon With id: " + pokemon.getId() + " Not exist"));

    Pokemon pokemanWithNameExist = pokemonRepository.findByNameIgnoreCase(pokemon.getName()).orElse(pokemonRepository.update(pokemon));
    if(!Objects.equals(pokemanWithNameExist.getId(), pokemonWithId.getId())){
      throw new PokemonValidationException("Pokemon with name: " +pokemon.getName() +" Already exist");
    }
    pokemonRepository.update(pokemon);



  }

  public void delete(Integer id) {

    boolean isPokemonExist = pokemonRepository.existsById(id);
    if (!isPokemonExist) {
      throw new PokemonValidationException("Pokemon with id " + id + " Not Found");
    }
    pokemonRepository.deleteById(id);
  }
}

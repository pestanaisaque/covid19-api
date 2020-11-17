package com.pestana.covidapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pestana.covidapi.clients.Covid19BrazilApi;
import com.pestana.covidapi.entity.Covid19BrazilApiData;
import com.pestana.covidapi.entity.Favorite;
import com.pestana.covidapi.repository.FavoriteRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api (value = "COVID-19 API")
public class CovidApiController {

	@Autowired
	private Covid19BrazilApi covid19BrazilApi;
	
	@Autowired
	private FavoriteRepository favoriteRepository;
	
	@ApiOperation(value = "Retorna uma lista de Países com casos/mortes por COVID-19.")
	@GetMapping("/countries")
	public List<Covid19BrazilApiData> getCountriesListWithCases() {
		return covid19BrazilApi.getCountriesListWithCases();
	}
	
	@ApiOperation(value = "Retorna uma os casos/mortes por COVID-19 de um determinado país.")
	@GetMapping("/countries/{countryName}")
	public Covid19BrazilApiData getCountryByName(@PathVariable(value = "countryName") String countryName) {
		return covid19BrazilApi.getCountryByName(countryName);
	}
	
	@ApiOperation(value = "Adiciona um determinado país a lista de favoritos para o acompanhamento.")
	@PostMapping("/countries")
	public void saveCountryInFavorites(@RequestBody Covid19BrazilApiData selectedCountry) {
		Optional<Favorite> optional = favoriteRepository.findByCountryName(selectedCountry.getCountry());
		if (!optional.isPresent()) {
			favoriteRepository.save(new Favorite(selectedCountry.getCountry(), Boolean.TRUE));	
		}
	}
	
	@ApiOperation(value = "Atualiza a situação de um determinado país na lista de favoritos para o acompanhamento.")
	@PutMapping("/countries/{countryName}")
	public void updateCountryInFavorites(@PathVariable(value = "countryName") String countryName, @RequestBody Covid19BrazilApiData selectedCountry) {
		Optional<Favorite> optional = favoriteRepository.findByCountryName(countryName);
		if (optional.isPresent()) {
			Favorite favorite = optional.get(); 
			favorite.setActive(!optional.get().getActive());
			favoriteRepository.save(favorite);
		}
	}
	
	@ApiOperation(value = "Remove um determinado país da lista de favoritos.")
	@DeleteMapping("/countries/{countryName}")
	public void deleteCountryFromFavorites(@PathVariable(value = "countryName") String countryName) {
		Optional<Favorite> optional = favoriteRepository.findByCountryName(countryName);
		if (optional.isPresent()) {
			Favorite favorite = optional.get(); 
			favoriteRepository.delete(favorite);
		}
	}
}

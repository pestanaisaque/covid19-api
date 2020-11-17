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

@RestController
public class CovidApiController {

	@Autowired
	private Covid19BrazilApi covid19BrazilApi;
	
	@Autowired
	private FavoriteRepository favoriteRepository;
	
	@GetMapping("/countries")
	public List<Covid19BrazilApiData> getCountriesListWithCases() {
		return covid19BrazilApi.getCountriesListWithCases();
	}
	
	@GetMapping("/countries/{countryName}")
	public Covid19BrazilApiData getCountryByName(@PathVariable(value = "countryName") String countryName) {
		return covid19BrazilApi.getCountryByName(countryName);
	}
	
	@PostMapping("/countries")
	public void saveCountryInFavorites(@RequestBody Covid19BrazilApiData selectedCountry) {
		favoriteRepository.save(new Favorite(selectedCountry.getCountry(), Boolean.TRUE));
	}
	
	@PutMapping("/countries/{countryName}")
	public void updateCountryInFavorites(@PathVariable(value = "countryName") String countryName, @RequestBody Covid19BrazilApiData selectedCountry) {
		Optional<Favorite> optional = favoriteRepository.findByCountryName(countryName);
		if (optional.isPresent()) {
			Favorite favorite = optional.get(); 
			favorite.setActive(!optional.get().getActive());
			favoriteRepository.save(favorite);
		}
	}
	
	@DeleteMapping("/countries/{countryName}")
	public void deleteCountryFromFavorites(@PathVariable(value = "countryName") String countryName) {
		Optional<Favorite> optional = favoriteRepository.findByCountryName(countryName);
		if (optional.isPresent()) {
			Favorite favorite = optional.get(); 
			favoriteRepository.delete(favorite);
		}
	}
}

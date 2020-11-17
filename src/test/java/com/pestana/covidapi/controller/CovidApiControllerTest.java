package com.pestana.covidapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pestana.covidapi.clients.Covid19BrazilApi;
import com.pestana.covidapi.entity.Covid19BrazilApiData;
import com.pestana.covidapi.entity.Favorite;
import com.pestana.covidapi.repository.FavoriteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CovidApiControllerTest {
	private final String default_countries_response = "[{\"country\":\"Canada\",\"cases\":1299,\"confirmed\":1328,\"deaths\":19,\"recovered\":10,\"updated_at\":\"2020-03-21T23:43:02.000Z\"},{\"country\":\"Maldives\",\"cases\":13,\"confirmed\":13,\"deaths\":0,\"recovered\":0,\"updated_at\":\"2020-03-15T18:20:18.000Z\"}]";
	private final String default_country_request = "{\"country\":\"Canada\",\"cases\":1299,\"confirmed\":1328,\"deaths\":19,\"recovered\":10,\"updated_at\":\"2020-03-21T23:43:02.000Z\"}";
	private final Covid19BrazilApiData default_request = new Gson().fromJson(default_country_request, new TypeToken<Covid19BrazilApiData>(){}.getType());
	
	@InjectMocks
    private CovidApiController mockCovidApiController;
	
	@Mock
	private Covid19BrazilApi mockCovid19BrazilApi;
	
	@Mock
	private FavoriteRepository mockFavoriteRepository;
	
	@BeforeEach
	public void setupEachTest() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void shouldGetCountriesListWithCases() {
		List<Covid19BrazilApiData> response = new Gson().fromJson(default_countries_response, new TypeToken<List<Covid19BrazilApiData>>(){}.getType());
		doReturn(response).when(mockCovid19BrazilApi).getCountriesListWithCases();
		assertEquals(response, mockCovidApiController.getCountriesListWithCases());
	}
	
	@Test
	public void shouldGetCountryByName() {
		doReturn(default_request).when(mockCovid19BrazilApi).getCountryByName(default_request.getCountry());
		assertEquals(default_request, mockCovidApiController.getCountryByName(default_request.getCountry()));
	}
	
	@Test
	public void shouldSaveCountryInFavorites() {
		mockCovidApiController.saveCountryInFavorites(default_request);
		verify(mockFavoriteRepository, times(1)).save(Mockito.any());
	}
	
	@Test
	public void shouldUpdateCountryInFavorites() {
		Optional<Favorite> optFavorite = Optional.of(new Favorite(default_request.getCountry(), Boolean.TRUE));
		doReturn(optFavorite).when(mockFavoriteRepository).findByCountryName(default_request.getCountry());
		mockCovidApiController.updateCountryInFavorites(default_request.getCountry(), default_request);
		verify(mockFavoriteRepository, times(1)).save(optFavorite.get());
	}
	
	@Test
	public void shouldDeleteCountryFromFavorites() {
		Optional<Favorite> optFavorite = Optional.of(new Favorite(default_request.getCountry(), Boolean.TRUE));
		doReturn(optFavorite).when(mockFavoriteRepository).findByCountryName(default_request.getCountry());
		mockCovidApiController.deleteCountryFromFavorites(default_request.getCountry());
		verify(mockFavoriteRepository, times(1)).delete(optFavorite.get());
	}
}

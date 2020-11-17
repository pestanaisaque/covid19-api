package com.pestana.covidapi.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.pestana.covidapi.entity.Covid19BrazilApiData;

@Service
public class Covid19BrazilApi {

	@Value("${covid19.brazil.api}")
	private String uri;

	public List<Covid19BrazilApiData> getCountriesListWithCases() {
		
		JsonElement returnJson = JsonParser.parseString(getResponse("countries", String.class));
		
		return new Gson().fromJson(returnJson.getAsJsonObject().get("data").toString(),
				new TypeToken<List<Covid19BrazilApiData>>() {
				}.getType());
	}

	public Covid19BrazilApiData getCountryByName(String countryName) {
		JsonElement returnJson = JsonParser.parseString(getResponse(countryName, String.class));
		
		return new Gson().fromJson(returnJson.getAsJsonObject().get("data").toString(),
				new TypeToken<Covid19BrazilApiData>() {
				}.getType());
	}

	private <T> T getResponse(String resource, Class<T> t) {
		return new RestTemplate().getForObject(uri + "/" + resource, t);
	}
}

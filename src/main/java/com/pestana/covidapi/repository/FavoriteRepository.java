package com.pestana.covidapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pestana.covidapi.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
	public Optional<Favorite> findByCountryName(String countryName);
}

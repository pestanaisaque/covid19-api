package com.pestana.covidapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Covid19BrazilApiData {
	private String country;
	private Long cases;
	private Long confirmed;
	private Long deaths;
	private Long recovered;
}

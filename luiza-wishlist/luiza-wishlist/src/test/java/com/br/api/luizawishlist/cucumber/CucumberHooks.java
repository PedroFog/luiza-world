package com.br.api.luizawishlist.cucumber;

import org.springframework.beans.factory.annotation.Autowired;

import io.cucumber.java.Before;

public class CucumberHooks {

	@Autowired
	private DatabaseCleanupService databaseCleanupService;

	@Before
	public void beforeScenario() {
		databaseCleanupService.clearDatabase();
	}
}

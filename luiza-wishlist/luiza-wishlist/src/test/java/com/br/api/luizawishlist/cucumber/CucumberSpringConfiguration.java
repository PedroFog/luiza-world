package com.br.api.luizawishlist.cucumber;

import org.springframework.boot.test.context.SpringBootTest;

import com.br.api.luizawishlist.LuizaWishlistApplication;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = {LuizaWishlistApplication.class, TestSecurityConfig.class})
public class CucumberSpringConfiguration {
}

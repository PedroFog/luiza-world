package com.br.api.luizawishlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.mongock.runner.springboot.EnableMongock;

@SpringBootApplication
@ComponentScan(basePackages = "com.br.api.luizawishlist")
@EnableMongock
public class LuizaWishlistApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuizaWishlistApplication.class, args);
	}

}

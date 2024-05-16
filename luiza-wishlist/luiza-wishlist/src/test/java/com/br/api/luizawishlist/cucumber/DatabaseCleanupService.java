package com.br.api.luizawishlist.cucumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

@Service
public class DatabaseCleanupService {
	
	private static final String DATABASE_TEST = "wishlist-db-test";

	@Autowired
	private MongoClient mongoClient;

	public void clearDatabase() {
		MongoDatabase database = mongoClient.getDatabase(DATABASE_TEST);
		database.drop();
	}
}

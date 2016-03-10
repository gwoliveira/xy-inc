package me.impressione.config;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import me.impressione.util.ObjectIdSerializer;

@Configuration
public class AppConfig {

	private static final String MONGODB_URI = "mongodb://mongo/";

	@Bean
	public MongoClient mongoClient() {
		MongoClientURI uri = new MongoClientURI(MONGODB_URI);
		return new MongoClient(uri);
	}

	@Bean
	@Autowired
	public MongoDatabase mongoDatabase(MongoClient mongoClient) {
		return mongoClient.getDatabase("admin");
	}

	@Bean
	public Jackson2ObjectMapperBuilder configeJackson() {
		Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder = new Jackson2ObjectMapperBuilder();
		jackson2ObjectMapperBuilder.serializerByType(ObjectId.class, new ObjectIdSerializer());
		return jackson2ObjectMapperBuilder;
	}
}

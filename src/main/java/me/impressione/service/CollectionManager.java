package me.impressione.service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import me.impressione.util.IFilters;

@Service
public class CollectionManager {
	@Autowired
	MongoDatabase mongoDatabase;

	@Autowired
	IFilters filters;

	public CollectionHelper use(String collectionName) {
		
		Document modelo = mongoDatabase.getCollection("/").find(filters.filterByModel(collectionName)).first();
		if(modelo == null){
			throw new IllegalArgumentException("coleção sem modelo");
		}
		
		MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
		return new CollectionHelper(collection, filters, modelo);
	}

}

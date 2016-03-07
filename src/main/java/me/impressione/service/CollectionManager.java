package me.impressione.service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import me.impressione.util.IFilters;

@Service
public class CollectionManager {
	private static final Document META_MODELO = Document.parse(
			"{\"model\":\"/\",\"fields\":[{\"name\":\"model\",\"type\":\"String\",\"required\":true},{\"name\":\"fields\",\"type\":\"ArrayList\"}]}");

	@Autowired
	MongoDatabase mongoDatabase;

	@Autowired
	IFilters filters;

	public CollectionHelper use(String collectionName) {

		Document modelo = findModelo(collectionName);

		MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
		return new CollectionHelper(collection, filters, modelo);
	}

	private Document findModelo(String collectionName) {
		if ("/".equals(collectionName)) {
			return META_MODELO;
		}

		Document modelo = mongoDatabase.getCollection("/").find(filters.filterByModel(collectionName)).first();
		if (modelo == null) {
			throw new IllegalArgumentException("coleção sem modelo");
		}
		return modelo;
	}

}

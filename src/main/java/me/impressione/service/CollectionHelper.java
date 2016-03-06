package me.impressione.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.Block;
import com.mongodb.client.MongoCollection;

import me.impressione.util.IFilters;

public class CollectionHelper {
	private final MongoCollection<Document> collection;
	private final IFilters filters;
	private final ValidationService validationService;

	public CollectionHelper(MongoCollection<Document> collection, IFilters filters, Document modelo) {
		this.collection = collection;
		this.filters = filters;
		this.validationService = new ValidationService(modelo);
	}

	public List<Document> getAll() {
		List<Document> documents = new ArrayList<>();
		collection.find().forEach((Block<Document>) document -> documents.add(document));
		return documents;
	}

	public Document getOne(String documentID) {
		return collection.find(filterByID(documentID)).first();
	}

	public boolean delete(String documentID) {
		return collection.deleteOne(filterByID(documentID)).wasAcknowledged();
	}

	public Document save(Document doc) {
		validationService.validate(doc);
		collection.insertOne(doc);
		return doc;
	}

	public boolean update(String documentID, Document doc) {
		validationService.validate(doc);
		return collection.replaceOne(filterByID(documentID), doc).wasAcknowledged();
	}

	private Bson filterByID(String documentID) {
		return filters.filterByID(documentID);
	}

}

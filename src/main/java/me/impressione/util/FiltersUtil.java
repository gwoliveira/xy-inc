package me.impressione.util;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import com.mongodb.client.model.Filters;

@Component
public class FiltersUtil implements IFilters {
	@Override
	public Bson filterByID(String documentID) {
		ObjectId objectID = new ObjectId(documentID);
		return Filters.eq("_id", objectID);
	}

	@Override
	public Bson filterByModel(String collectionName) {
		return Filters.eq("model", collectionName);
	}
}

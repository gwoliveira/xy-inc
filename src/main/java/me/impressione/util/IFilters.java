package me.impressione.util;

import org.bson.conversions.Bson;

public interface IFilters {

	Bson filterByID(String documentID);
	
	Bson filterByModel(String collectionName);

}
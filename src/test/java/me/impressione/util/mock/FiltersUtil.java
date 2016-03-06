package me.impressione.util.mock;

import org.bson.BsonDocument;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Component;

import me.impressione.util.IFilters;

@Component
public class FiltersUtil implements IFilters {

	public Bson filterByID(String documentID) {
		return new MyBson(documentID);
	}

	@Override
	public Bson filterByModel(String collectionName) {
		return new MyBson(collectionName);
	}

	private static class MyBson implements Bson {
		private String documentID;

		public MyBson(String documentID) {
			this.documentID = documentID;
		}

		@Override
		public <TDocument> BsonDocument toBsonDocument(Class<TDocument> documentClass, CodecRegistry codecRegistry) {
			return null;
		}

		@Override
		public boolean equals(Object obj) {
			MyBson other = (MyBson) obj;
			return documentID.equals(other.documentID);
		}

	}

}

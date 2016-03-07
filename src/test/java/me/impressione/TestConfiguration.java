package me.impressione;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bson.Document;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import me.impressione.util.IFilters;

@Configuration
@SuppressWarnings("unchecked")
@ComponentScan(basePackages = { "me.impressione.service", "me.impressione.controller", "me.impressione.util.mock" })
public class TestConfiguration {

	@Autowired
	private IFilters filters;

	@Mock
	private MongoDatabase mongoDatabase;

	@Mock
	private MongoCollection<Document> collection;

	@Mock
	private MongoCollection<Document> modelCollection;

	public TestConfiguration() {
		MockitoAnnotations.initMocks(this);
	}

	@Bean
	@Autowired
	public MongoDatabase mongoDatabase(MongoCollection<Document> collection) {
		initModel();
		return mongoDatabase;
	}

	@Bean
	public MongoCollection<Document> collection() {
		return collection;
	}

	private void initModel() {
		when(mongoDatabase.getCollection("/")).thenReturn(modelCollection);
		when(mongoDatabase.getCollection("produto")).thenReturn(collection);
		when(modelCollection.find(filters.filterByModel(any()))).thenReturn(mock(FindIterable.class));

		FindIterable<Document> findIterable = mock(FindIterable.class);
		when(modelCollection.find(filters.filterByModel("produto"))).thenReturn(findIterable);
		String json = "	{\n" + "	    \"_id\" : ObjectId(\"56db4beb76785010ece5a336\"),\n"
				+ "	    \"model\" : \"produto\",\n" + "	    \"fields\" : [ \n" + "	        {\n"
				+ "	            \"name\" : \"nome\",\n" + "	            \"type\" : \"String\",\n"
				+ "	            \"required\" : true\n" + "	        }, \n" + "	        {\n"
				+ "	            \"name\" : \"preco\",\n" + "	            \"type\" : \"Double\",\n"
				+ "	            \"required\" : true\n" + "	        }, \n" + "	        {\n"
				+ "	            \"name\" : \"codigo\",\n" + "	            \"type\" : \"Integer\",\n"
				+ "	            \"required\" : false\n" + "	        }\n" + "	    ]\n" + "	}";
		Document modelDB = Document.parse(json);
		when(findIterable.first()).thenReturn(modelDB);
	}

}

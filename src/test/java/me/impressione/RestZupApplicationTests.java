package me.impressione;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import me.impressione.service.CollectionManager;
import me.impressione.util.IFilters;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfiguration.class)
@WebAppConfiguration
@SuppressWarnings("unchecked")
public class RestZupApplicationTests {
	@Autowired
	private CollectionManager collectionManager;

	@Autowired
	private MongoCollection<Document> collection;
	
	@Autowired
	private IFilters filters;
	
	@Before
	public void setUp() {
		Mockito.reset(collection);
	}

	@Test
	public void getOne() {
		String json = "{\"_id\" : ObjectId(\"56dad8c0aec8cb15c60ad7dc\"), \"nome\" : \"guilherme\"}";
		String key = "56dad8c0aec8cb15c60ad7dc";
		prepareCollectionToGetOne(key, json);
		
		Document document = collectionManager.use("produto").getOne(key);
		
		assertThat(document.getObjectId("_id"), is(new ObjectId(key)));
		assertThat(document.getString("nome"), is("guilherme"));
	}

	private void prepareCollectionToGetOne(String key, String json) {
	
		Document documentOnDB = Document.parse(json);
		FindIterable<Document> findIterable = mock(FindIterable.class);
		when(collection.find(filters.filterByID(key))).thenReturn(findIterable);
		when(findIterable.first()).thenReturn(documentOnDB);
	}

}

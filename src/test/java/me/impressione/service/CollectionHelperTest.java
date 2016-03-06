package me.impressione.service;

import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import me.impressione.util.TestsUtil;
import me.impressione.util.mock.FiltersUtil;

@SuppressWarnings("unchecked")
public class CollectionHelperTest {

	private static final String DOCUMENT_ID = "56db575076785016f0e62f19";
	private static final Document PRODUTO_COMPLETO = TestsUtil.loadAsDocument("/produto/ok/produtoComplete.json");
	private CollectionHelper collectionHelper;
	private MongoCollection<Document> collection;
	private FiltersUtil filters = new FiltersUtil();

	@Before
	public void setUp() throws Exception {
		Document modelo = TestsUtil.loadAsDocument("/produto/modelo.json");
		collection = mock(MongoCollection.class);
		collectionHelper = new CollectionHelper(collection, filters, modelo);
	}

	@Test
	public void testGetAll() {
		FindIterable<Document> iterable = mock(FindIterable.class);
		when(collection.find()).thenReturn(iterable);
		List<Document> all = collectionHelper.getAll();
		verify(collection).find();
		verify(iterable).forEach(any(Block.class));
		assertThat(all, emptyCollectionOf(Document.class));
	}

	@Test
	public void testSave() throws IOException {
		collectionHelper.save(PRODUTO_COMPLETO);
		verify(collection).insertOne(PRODUTO_COMPLETO);
	}

	@Test
	public void testDelete() throws IOException {
		Bson filterByID = filters.filterByID(DOCUMENT_ID);
		when(collection.deleteOne(filterByID)).thenReturn(mock(DeleteResult.class));

		collectionHelper.delete(DOCUMENT_ID);
		verify(collection).deleteOne(filterByID);
	}

	@Test
	public void testUpdate() throws IOException {
		Bson filterByID = filters.filterByID(DOCUMENT_ID);
		when(collection.replaceOne(filterByID, PRODUTO_COMPLETO)).thenReturn(mock(UpdateResult.class));
		collectionHelper.update(DOCUMENT_ID, PRODUTO_COMPLETO);
		verify(collection).replaceOne(filterByID, PRODUTO_COMPLETO);
	}
}

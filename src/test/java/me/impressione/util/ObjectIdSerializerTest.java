package me.impressione.util;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ObjectIdSerializerTest {
	ObjectIdSerializer objectIdSerializer;

	@Before
	public void setUp() throws Exception {
		objectIdSerializer = new ObjectIdSerializer();
	}

	@Test
	public void testSerializeNull() throws JsonProcessingException, IOException {
		JsonGenerator gen = mock(JsonGenerator.class);
		objectIdSerializer.serialize(null, gen, null);
		verify(gen).writeNull();
	}

	@Test
	public void testSerializeNotNull() throws JsonProcessingException, IOException {
		JsonGenerator gen = mock(JsonGenerator.class);
		ObjectId objectId = new ObjectId();
		objectIdSerializer.serialize(objectId, gen, null);
		verify(gen).writeString(objectId.toString());
	}

	@Test
	public void testHandledType() {
		Class<ObjectId> clazz = ObjectId.class;
		assertThat(objectIdSerializer.handledType(), equalTo(clazz));
	}

}

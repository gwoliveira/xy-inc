package me.impressione.util;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.bson.conversions.Bson;
import org.junit.Before;
import org.junit.Test;

public class FiltersUtilTest {
	private FiltersUtil filtersUtil;

	@Before
	public void setUp() throws Exception {
		filtersUtil = new FiltersUtil();
	}

	@Test
	public void testFilterByID() {
		Bson filterByID = filtersUtil.filterByID("56db575076785016f0e62f19");
		assertThat(filterByID, notNullValue());
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void testFilterByIDInvalidID() {
		Bson filterByID = filtersUtil.filterByID("56db5085016f0e62f19");
		assertThat(filterByID, notNullValue());
	}

	@Test
	public void testFilterByModel() {
		Bson filterByModel = filtersUtil.filterByModel("produto");
		assertThat(filterByModel, notNullValue());
	}
}

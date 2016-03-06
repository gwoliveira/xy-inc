package me.impressione.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.impressione.TestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestConfiguration.class)
public class CollectionManagerTest {
	@Autowired
	CollectionManager collectionManager;

	@Test
	public void testExitentCollection() {
		collectionManager.use("produto");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNoExitCollection() {
		collectionManager.use("notExists");
	}

}

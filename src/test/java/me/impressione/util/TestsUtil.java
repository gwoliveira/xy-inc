package me.impressione.util;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.bson.Document;

public class TestsUtil {
	public static Document loadAsDocument(String fileName) {
		Document modelo = null;
		try {
			String json = IOUtils.toString(TestsUtil.class.getResourceAsStream(fileName));
			modelo = Document.parse(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return modelo;
	}
}

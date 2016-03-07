package me.impressione.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.bson.Document;

@SuppressWarnings("unchecked")
public class ValidationService {
	private final Document modelo;

	public ValidationService(Document modelo) {
		this.modelo = modelo;
	}

	public void validate(Document doc) {
		if(doc.containsKey("_id")){
			throw new ValidationException("Não é permitido alterar o campo '_id'");
		}
		
		List<Document> fields = modelo.get("fields", List.class);
		List<String> fieldsName = new ArrayList<>();
		for (Document campo : fields) {
			String nameField = validateField(doc, campo);
			fieldsName.add(nameField);
		}
		
		for (String field : doc.keySet()) {
			if (!fieldsName.contains(field)) {
				throw new ValidationException(String.format("Campo '%s' não existe no modelo", field));
			}
		}
		
	}

	private String validateField(Document doc, Document campo) {
		String name = campo.getString("name");
		String type = campo.getString("type");
		Boolean required = campo.getBoolean("required");

		if (doc.containsKey(name)) {
			Object valorCampo = doc.get(name);
			if (!type.equals(valorCampo.getClass().getSimpleName())) {
				throw new ValidationException(String.format("Campo '%s' deveria ser do tipo %s", name, type));
			}
		} else if (required) {
			throw new ValidationException(String.format("Campo '%s' é obrigatorio", name));
		}
		return name;
	}

}

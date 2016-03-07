package me.impressione.controller;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import me.impressione.service.CollectionManager;

@Controller
@ResponseBody
public class ModeloController {

	@Autowired
	private CollectionManager collectionManager;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Document> modelos() {
		return collectionManager.use("/").getAll();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Document modeloSave(@RequestBody Document doc) {
		return collectionManager.use("/").save(doc);
	}
	

	@RequestMapping(value = "/{modelo}", method = RequestMethod.GET)
	public List<Document> getAll(@PathVariable("modelo") String modelo) {
		return collectionManager.use(modelo).getAll();
	}

	@RequestMapping(value = "/{modelo}/{documentid}", method = RequestMethod.GET)
	public Document getOne(@PathVariable("modelo") String modelo, @PathVariable("documentid") String documentID) {
		return collectionManager.use(modelo).getOne(documentID);
	}

	@RequestMapping(value = "/{modelo}", method = RequestMethod.POST)
	public Document save(@PathVariable("modelo") String modelo, @RequestBody Document doc) {
		return collectionManager.use(modelo).save(doc);
	}

	@RequestMapping(value = "/{modelo}/{documentid}", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> update(@PathVariable("modelo") String modelo,
			@PathVariable("documentid") String documentID, @RequestBody Document doc) {
		boolean update = collectionManager.use(modelo).update(documentID, doc);
		return update ? ResponseEntity.ok(true) : ResponseEntity.badRequest().body(false);
	}

	@RequestMapping(value = "/{modelo}/{documentid}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable("modelo") String modelo,
			@PathVariable("documentid") String documentID) {
		boolean delete = collectionManager.use(modelo).delete(documentID);
		return delete ? ResponseEntity.ok(true) : ResponseEntity.badRequest().body(false);
	}
}

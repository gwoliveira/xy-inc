package me.impressione.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import me.impressione.service.CollectionHelper;
import me.impressione.service.CollectionManager;
import me.impressione.util.TestsUtil;

@RunWith(MockitoJUnitRunner.class)
public class ModeloControllerTest {
	private static final String META_MODELO_PATH = "/";
	private static final String MODELO_PRODUTO_PATH = "produto";
	private static final String DOCUMENT_ID = "56db575076785016f0e62f19";
	private static final Document PRODUTO_COMPLETO = TestsUtil.loadAsDocument("/produto/ok/produtoComplete.json");
	private static final Document MODELO_PRODUTO = TestsUtil.loadAsDocument("/produto/modelo.json");

	@Mock
	private CollectionManager collectionManager;
	@InjectMocks
	private ModeloController modeloController = new ModeloController();
	@Mock
	private CollectionHelper modeloProdutoHelper;
	@Mock
	private CollectionHelper modeloHelper;

	@Before
	public void setUp() throws Exception {

		when(collectionManager.use(MODELO_PRODUTO_PATH)).thenReturn(modeloProdutoHelper);
		when(collectionManager.use(META_MODELO_PATH)).thenReturn(modeloHelper);
	}

	@Test
	public void testModelos() {
		modeloController.modelos();
		verify(collectionManager).use(META_MODELO_PATH);
		verify(modeloHelper).getAll();
	}

	@Test
	public void testModeloSave() {
		modeloController.modeloSave(MODELO_PRODUTO);
		verify(collectionManager).use(META_MODELO_PATH);
		verify(modeloHelper).save(MODELO_PRODUTO);
	}

	@Test
	public void testGetAll() {
		modeloController.getAll(MODELO_PRODUTO_PATH);
		verify(collectionManager).use(MODELO_PRODUTO_PATH);
		verify(modeloProdutoHelper).getAll();
	}

	@Test
	public void testGetOne() {
		modeloController.getOne(MODELO_PRODUTO_PATH, DOCUMENT_ID);
		verify(collectionManager).use(MODELO_PRODUTO_PATH);
		verify(modeloProdutoHelper).getOne(DOCUMENT_ID);
	}

	@Test
	public void testSave() {
		modeloController.save(MODELO_PRODUTO_PATH, PRODUTO_COMPLETO);
		verify(collectionManager).use(MODELO_PRODUTO_PATH);
		verify(modeloProdutoHelper).save(PRODUTO_COMPLETO);
	}

	@Test
	public void testUpdateGood() {
		when(modeloProdutoHelper.update(DOCUMENT_ID, PRODUTO_COMPLETO)).thenReturn(true);
		ResponseEntity<Boolean> update = modeloController.update(MODELO_PRODUTO_PATH, DOCUMENT_ID, PRODUTO_COMPLETO);
		verify(collectionManager).use(MODELO_PRODUTO_PATH);
		verify(modeloProdutoHelper).update(DOCUMENT_ID, PRODUTO_COMPLETO);
		assertThat(update.getBody(), is(true));
		assertThat(update.getStatusCode(), is(HttpStatus.OK));

	}

	@Test
	public void testUpdateBad() {
		when(modeloProdutoHelper.update(DOCUMENT_ID, PRODUTO_COMPLETO)).thenReturn(false);
		ResponseEntity<Boolean> update = modeloController.update(MODELO_PRODUTO_PATH, DOCUMENT_ID, PRODUTO_COMPLETO);
		verify(collectionManager).use(MODELO_PRODUTO_PATH);
		verify(modeloProdutoHelper).update(DOCUMENT_ID, PRODUTO_COMPLETO);
		assertThat(update.getBody(), is(false));
		assertThat(update.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	}

	@Test
	public void testDeleteGood() {
		when(modeloProdutoHelper.delete(DOCUMENT_ID)).thenReturn(true);
		ResponseEntity<Boolean> delete = modeloController.delete(MODELO_PRODUTO_PATH, DOCUMENT_ID);
		verify(collectionManager).use(MODELO_PRODUTO_PATH);
		verify(modeloProdutoHelper).delete(DOCUMENT_ID);
		assertThat(delete.getBody(), is(true));
		assertThat(delete.getStatusCode(), is(HttpStatus.OK));
	}

	@Test
	public void testDeleteBad() {
		when(modeloProdutoHelper.delete(DOCUMENT_ID)).thenReturn(false);
		ResponseEntity<Boolean> delete = modeloController.delete(MODELO_PRODUTO_PATH, DOCUMENT_ID);
		verify(collectionManager).use(MODELO_PRODUTO_PATH);
		verify(modeloProdutoHelper).delete(DOCUMENT_ID);
		assertThat(delete.getBody(), is(false));
		assertThat(delete.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	}

}

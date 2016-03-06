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
	private static final String MODELO_PRODUTO = "produto";
	private static final String DOCUMENT_ID = "56db575076785016f0e62f19";
	private static final Document PRODUTO_COMPLETO = TestsUtil.loadAsDocument("/produto/ok/produtoComplete.json");
	private static final Document MODELO = TestsUtil.loadAsDocument("/produto/modelo.json");

	@Mock
	CollectionManager collectionManager;

	@InjectMocks
	ModeloController modeloController = new ModeloController();
	@Mock
	CollectionHelper modeloProdutoHelper;
	@Mock
	CollectionHelper modeloHelper;

	@Before
	public void setUp() throws Exception {

		when(collectionManager.use(MODELO_PRODUTO)).thenReturn(modeloProdutoHelper);
		when(collectionManager.use("/")).thenReturn(modeloHelper);
	}

	@Test
	public void testModelos() {
		modeloController.modelos();
		verify(collectionManager).use("/");
		verify(modeloHelper).getAll();
	}

	@Test
	public void testModeloSave() {
		modeloController.modeloSave(MODELO);
		verify(collectionManager).use("/");
		verify(modeloHelper).save(MODELO);
	}

	@Test
	public void testGetAll() {
		modeloController.getAll(MODELO_PRODUTO);
		verify(collectionManager).use(MODELO_PRODUTO);
		verify(modeloProdutoHelper).getAll();
	}

	@Test
	public void testGetOne() {
		modeloController.getOne(MODELO_PRODUTO, DOCUMENT_ID);
		verify(collectionManager).use(MODELO_PRODUTO);
		verify(modeloProdutoHelper).getOne(DOCUMENT_ID);
	}

	@Test
	public void testSave() {
		modeloController.save(MODELO_PRODUTO, PRODUTO_COMPLETO);
		verify(collectionManager).use(MODELO_PRODUTO);
		verify(modeloProdutoHelper).save(PRODUTO_COMPLETO);
	}

	@Test
	public void testUpdateGood() {
		when(modeloProdutoHelper.update(DOCUMENT_ID, PRODUTO_COMPLETO)).thenReturn(true);
		ResponseEntity<Boolean> update = modeloController.update(MODELO_PRODUTO, DOCUMENT_ID, PRODUTO_COMPLETO);
		verify(collectionManager).use(MODELO_PRODUTO);
		verify(modeloProdutoHelper).update(DOCUMENT_ID, PRODUTO_COMPLETO);
		assertThat(update.getBody(), is(true));
		assertThat(update.getStatusCode(), is(HttpStatus.OK));

	}

	@Test
	public void testUpdateBad() {
		when(modeloProdutoHelper.update(DOCUMENT_ID, PRODUTO_COMPLETO)).thenReturn(false);
		ResponseEntity<Boolean> update = modeloController.update(MODELO_PRODUTO, DOCUMENT_ID, PRODUTO_COMPLETO);
		verify(collectionManager).use(MODELO_PRODUTO);
		verify(modeloProdutoHelper).update(DOCUMENT_ID, PRODUTO_COMPLETO);
		assertThat(update.getBody(), is(false));
		assertThat(update.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	}

	@Test
	public void testDeleteGood() {
		when(modeloProdutoHelper.delete(DOCUMENT_ID)).thenReturn(true);
		ResponseEntity<Boolean> delete = modeloController.delete(MODELO_PRODUTO, DOCUMENT_ID);
		verify(collectionManager).use(MODELO_PRODUTO);
		verify(modeloProdutoHelper).delete(DOCUMENT_ID);
		assertThat(delete.getBody(), is(true));
		assertThat(delete.getStatusCode(), is(HttpStatus.OK));
	}

	@Test
	public void testDeleteBad() {
		when(modeloProdutoHelper.delete(DOCUMENT_ID)).thenReturn(false);
		ResponseEntity<Boolean> delete = modeloController.delete(MODELO_PRODUTO, DOCUMENT_ID);
		verify(collectionManager).use(MODELO_PRODUTO);
		verify(modeloProdutoHelper).delete(DOCUMENT_ID);
		assertThat(delete.getBody(), is(false));
		assertThat(delete.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	}

}

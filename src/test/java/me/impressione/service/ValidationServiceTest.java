package me.impressione.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.IOException;

import javax.validation.ValidationException;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import me.impressione.util.TestsUtil;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceTest {

	private ValidationService validationService;

	@Before
	public void setUp() throws IOException {
		Document modelo = TestsUtil.loadAsDocument("/produto/modelo.json");
		validationService = new ValidationService(modelo);
	}

	@Test
	public void testValidationServiceOk() throws IOException {
		Document produtoOk = TestsUtil.loadAsDocument("/produto/ok/produtoComplete.json");
		validationService.validate(produtoOk);
	}

	@Test
	public void testValidationServiceWithoutNoRequiredField() throws IOException {
		Document produtoOk = TestsUtil.loadAsDocument("/produto/ok/produtoWithoutNoRequiredField.json");
		validationService.validate(produtoOk);
	}

	@Test
	public void testValidationServiceMissingRequiredField() throws IOException {
		Document produto = TestsUtil.loadAsDocument("/produto/fail/produtoMissingRequiredField.json");
		try {
			validationService.validate(produto);
			fail();
		} catch (ValidationException ex) {
			assertThat(ex.getMessage(), is("Campo 'nome' é obrigatorio"));
		}
	}

	@Test
	public void testValidationServiceFieldNotInModel() throws IOException {
		Document produto = TestsUtil.loadAsDocument("/produto/fail/produtoFieldNotInModel.json");
		try {
			validationService.validate(produto);
			fail();
		} catch (ValidationException ex) {
			assertThat(ex.getMessage(), is("Campo 'injectField' não existe no modelo"));
		}
	}

	@Test
	public void testValidationServiceWrongType() throws IOException {
		Document produto = TestsUtil.loadAsDocument("/produto/fail/produtoWrongType.json");
		try {
			validationService.validate(produto);
			fail();
		} catch (ValidationException ex) {
			assertThat(ex.getMessage(), is("Campo 'preco' deveria ser do tipo Double"));
		}
	}

}

package com.example.projetotdd.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.projetotdd.model.Produto;
import com.example.projetotdd.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class ProdutoControllerTest {
	
	@Autowired
	private ProdutoController produtoController;
	
	@MockBean
	private ProdutoService produtoService;

	@Autowired
	private MockMvc mockMvc;
	

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
	}
	
	@Test
	public void deve_retornar_status_200_ao_chamar_o_metodo_obter_todos() throws Exception {
		
		List<Produto> produtos = new ArrayList<Produto>();
		var requestBuilder = MockMvcRequestBuilders.get("/api/produtos");
		
		when(this.produtoService.obterTodos()).thenReturn(produtos);
		
		this.mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void deve_retornar_o_produto_por_id() throws Exception {
		
		Produto produto = new Produto();
		produto.setId(2L);
		produto.setNome("Martelo");
		produto.setQuantidade(10);
		
		Optional<Produto> optProduto = Optional.of(produto);
		var requestBuilder = MockMvcRequestBuilders.get("/api/produtos/2");
		
		when(this.produtoService.obterPorId(2L)).thenReturn(optProduto);
		
		this.mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2L));
		
	}
	
	@Test
	public void deve_adicionar_o_produto() throws Exception {
		
		Produto produto = new Produto();
		produto.setNome("Martelo");
		produto.setQuantidade(10);
		
		String json = new ObjectMapper().writeValueAsString(produto);
		var requestBuilder = MockMvcRequestBuilders.post("/api/produtos/")
				.content(json).contentType(MediaType.APPLICATION_JSON);
		
		produto.setId(1L);
		when(this.produtoService.adicionar(produto)).thenReturn(produto);
		
		this.mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isCreated());
		
	}

}

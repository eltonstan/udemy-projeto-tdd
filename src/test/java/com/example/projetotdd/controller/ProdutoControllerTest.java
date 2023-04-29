package com.example.projetotdd.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest
public class ProdutoControllerTest {
	
	@Autowired
	private ProdutoController produtoController;
	
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
	}
	
	@Test
	public void deve_retornar_status_200_ao_chamar_o_metodo_obter_todos() throws Exception {
		
		// List<Produto> produtos = new ArrayList<Produto>();
		var requestBuilder = MockMvcRequestBuilders.get("/api/produtos"); 
		
		this.mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
		
	}

}

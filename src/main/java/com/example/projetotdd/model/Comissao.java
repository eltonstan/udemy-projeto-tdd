package com.example.projetotdd.model;

public class Comissao {
	
	public Double calcular(Double valorVenda) {
		return valorVenda > 1000 ? valorVenda * 0.15 : valorVenda * 0.1;
	}

}

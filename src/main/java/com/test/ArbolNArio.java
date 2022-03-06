package com.test;

public class ArbolNArio<T> {
	private T valor;
	
	private ArbolNArio<T>[] hijos;

	public ArbolNArio(int tam) {
		super();
		hijos = new ArbolNArio[tam];
	}
	
	public void construir(T val) {
		setValor(val);
	}
	
	public void construir(T val, ArbolNArio[] hijos) {
		setValor(val);
		setHijos(hijos);
	}

	public T getValor() {
		return valor;
	}

	public void setValor(T valor) {
		this.valor = valor;
	}

	public ArbolNArio<T>[] getHijos() {
		return hijos;
	}

	public void setHijos(ArbolNArio<T>[] hijos) {
		
	}
	
	//public String toString() {
		
	//}
	
}
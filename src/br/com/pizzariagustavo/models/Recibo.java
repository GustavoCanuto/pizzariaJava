package br.com.pizzariagustavo.models;

import java.util.ArrayList;

public class Recibo {

    private static int contadorId = 1;
    private int idCompra;
    private Cliente cliente;
    private ArrayList<Pizza> listaPizzasEscolhidas = new ArrayList<>();; 
    private ArrayList<Acompanhamento> listaAcompanhamentoEscolhidas= new ArrayList<>();

    public Recibo() {
        this.idCompra = contadorId++;
    }


    public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public void setListaPizzasEscolhidas(Pizza pizza) {
		this.listaPizzasEscolhidas.add(pizza);
	}


	public void setListaAcompanhamentoEscolhidas(Acompanhamento acompanhamento) {
		this.listaAcompanhamentoEscolhidas.add(acompanhamento);
	}

	 public void removerPizza(Pizza pizza) {
	        this.listaPizzasEscolhidas.remove(pizza);
	    }

	 public void removerAcompanhamento(Acompanhamento acompanhamento) {
	        this.listaAcompanhamentoEscolhidas.remove(acompanhamento);
	  }

	public ArrayList<Pizza> getListaPizzasEscolhidas() {
		return listaPizzasEscolhidas;
	}


	public ArrayList<Acompanhamento> getListaAcompanhamentoEscolhidas() {
		return listaAcompanhamentoEscolhidas;
	}


	@Override
	public String toString() {
		return "Recibo [idCompra=" + idCompra + ", cliente=" + cliente + ", listaPizzasEscolhidas="
				+ listaPizzasEscolhidas + ", listaAcompanhamentoEscolhidas=" + listaAcompanhamentoEscolhidas + "]";
	}



}

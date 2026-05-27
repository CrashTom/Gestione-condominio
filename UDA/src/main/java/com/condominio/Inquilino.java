package com.condominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Inquilino implements Serializable {
    public int id;
    public String nome;
    public String cognome;
    public String appartamento;
    public String telefono;
    public List<Contatore> contatori = new ArrayList<>();

    public Inquilino() {}

    public Inquilino(int id, String nome, String cognome, String appartamento, String telefono) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.appartamento = appartamento;
        this.telefono = telefono;
    }

    public void stampa() {
        System.out.println("ID: " + id + "  Nome: " + nome + " " + cognome +
                "  App: " + appartamento + "  Tel: " + telefono);
        for (int i = 0; i < contatori.size(); i++)
            contatori.get(i).stampa();
    }

    public void stampaContatori() {
        for (int i = 0; i < contatori.size(); i++)
            contatori.get(i).stampa();
    }

    public Contatore trovaContatore(int idCont) {
        for (int i = 0; i < contatori.size(); i++)
            if (contatori.get(i).id == idCont)
                return contatori.get(i);
        return null;
    }

    public static Inquilino trovaPerId(List<Inquilino> lista, int id) {
        for (int i = 0; i < lista.size(); i++)
            if (lista.get(i).id == id)
                return lista.get(i);
        return null;
    }

    public static int prossimoId(List<Inquilino> lista) {
        int max = 0;
        for (int i = 0; i < lista.size(); i++)
            if (lista.get(i).id > max)
                max = lista.get(i).id;
        return max + 1;
    }
}

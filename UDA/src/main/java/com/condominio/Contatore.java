package com.condominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Contatore implements Serializable {
    public int id;
    public String tipo;
    public String matricola;
    public String unitaMisura;
    public List<Lettura> letture = new ArrayList<>();

    public Contatore() {}

    public Contatore(int id, String tipo, String matricola, String unitaMisura) {
        this.id = id;
        this.tipo = tipo;
        this.matricola = matricola;
        this.unitaMisura = unitaMisura;
    }

    public Lettura ultimaLettura() {
        if (letture == null || letture.isEmpty()) return null;
        return letture.get(letture.size() - 1);
    }

    public void stampa() {
        String ultima;
        if (ultimaLettura() == null) {
            ultima = "nessuna";
        } else {
            ultima = ultimaLettura().valore + " " + unitaMisura + " (" + ultimaLettura().data + ")";
        }
        System.out.println("  ID: " + id + "  Tipo: " + tipo + "  Matricola: " + matricola + "  Unita': " + unitaMisura + "  Ultima: " + ultima);
    }

    public void stampaStorico() {
        System.out.println();
        System.out.println("Contatore : " + tipo);
        System.out.println("Matricola : " + matricola);
        System.out.println("Unita'    : " + unitaMisura);
        System.out.println("---------------------------------------");

        if (letture.isEmpty()) {
            System.out.println("Nessuna lettura registrata.");
            return;
        }

        System.out.println("#    Data          Lettura     Consumo");
        System.out.println("---------------------------------------");

        double totale = 0;
        for (int i = 0; i < letture.size(); i++) {
            Lettura l = letture.get(i);
            String consumoStr;
            if (l.consumo < 0) {
                consumoStr = "--- (prima lettura)";
            } else {
                consumoStr = "+" + l.consumo + " " + unitaMisura;
                totale += l.consumo;
            }
            System.out.println((i + 1) + "    " + l.data + "    " + l.valore + "    " + consumoStr + "    " + l.note);
        }

        System.out.println("---------------------------------------");
        System.out.println("Consumo totale: " + totale + " " + unitaMisura);
    }

    public static int prossimoId(List<Inquilino> lista) {
        int max = 0;
        for (int i = 0; i < lista.size(); i++) {
            List<Contatore> contatori = lista.get(i).contatori;
            for (int j = 0; j < contatori.size(); j++) {
                if (contatori.get(j).id > max)
                    max = contatori.get(j).id;
            }
        }
        return max + 1;
    }
}

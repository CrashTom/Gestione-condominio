package com.condominio;

import java.io.Serializable;
import java.util.List;

public class Lettura implements Serializable {
    public int id;
    public String data;
    public double valore;
    public double consumo;
    public String note;

    public Lettura() {}

    public Lettura(int id, String data, double valore, double consumo, String note) {
        this.id = id;
        this.data = data;
        this.valore = valore;
        this.consumo = consumo;
        this.note = note;
    }

    public static int prossimoId(List<Inquilino> lista) {
        int max = 0;
        for (int i = 0; i < lista.size(); i++) {
            List<Contatore> contatori = lista.get(i).contatori;
            for (int j = 0; j < contatori.size(); j++) {
                List<Lettura> letture = contatori.get(j).letture;
                for (int k = 0; k < letture.size(); k++) {
                    if (letture.get(k).id > max) max = letture.get(k).id;
                }
            }
        }
        return max + 1;
    }
}

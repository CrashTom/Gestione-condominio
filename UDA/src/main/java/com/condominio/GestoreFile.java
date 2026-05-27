package com.condominio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestoreFile {

    private static final String FILE = "inquilini.dat";

    public static List<Inquilino> carica() {
        File f = new File(FILE);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
            return (List<Inquilino>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Errore lettura file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void salva(List<Inquilino> lista) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Errore salvataggio file: " + e.getMessage());
        }
    }
}

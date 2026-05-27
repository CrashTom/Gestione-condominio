package com.condominio;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== GESTIONE CONTATORI CONDOMINIO ===");
        int scelta;
        do {
            System.out.println("\n1.Nuovo  2.Visualizza  3.Lista  4.Cancella  5.+Contatore  6.-Contatore  7.Lettura  8.Storico  0.Esci");
            scelta = leggiInt("Scelta: ");
            if      (scelta == 1) nuovoInquilino();
            else if (scelta == 2) visualizzaInquilino();
            else if (scelta == 3) listaInquilini();
            else if (scelta == 4) cancellaInquilino();
            else if (scelta == 5) aggiungiContatore();
            else if (scelta == 6) rimuoviContatore();
            else if (scelta == 7) inserisciLettura();
            else if (scelta == 8) storicoLetture();
            else if (scelta == 0) System.out.println("Arrivederci!");
            else System.out.println("Scelta non valida.");
        } while (scelta != 0);
    }

    static void nuovoInquilino() {
        List<Inquilino> lista = GestoreFile.carica();
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Cognome: ");
        String cognome = sc.nextLine();
        System.out.print("Appartamento: ");
        String app = sc.nextLine();
        System.out.print("Telefono: ");
        String tel = sc.nextLine();
        lista.add(new Inquilino(Inquilino.prossimoId(lista), nome, cognome, app, tel));
        GestoreFile.salva(lista);
        System.out.println("Salvato.");
    }

    static void visualizzaInquilino() {
        List<Inquilino> lista = GestoreFile.carica();
        Inquilino inq = Inquilino.trovaPerId(lista, leggiInt("ID: "));
        if (inq == null) {
            System.out.println("Non trovato.");
            return;
        }
        inq.stampa();
    }

    static void listaInquilini() {
        List<Inquilino> lista = GestoreFile.carica();
        if (lista.isEmpty()) {
            System.out.println("Nessun inquilino.");
            return;
        }
        for (int i = 0; i < lista.size(); i++)
            lista.get(i).stampa();
    }

    static void cancellaInquilino() {
        List<Inquilino> lista = GestoreFile.carica();
        Inquilino inq = Inquilino.trovaPerId(lista, leggiInt("ID: "));
        if (inq == null) {
            System.out.println("Non trovato.");
            return;
        }
        inq.stampa();
        System.out.print("Confermi? (s/n): ");
        if (!sc.nextLine().equalsIgnoreCase("s")) {
            System.out.println("Annullato.");
            return;
        }
        lista.remove(inq);
        GestoreFile.salva(lista);
        System.out.println("Eliminato.");
    }

    static void aggiungiContatore() {
        List<Inquilino> lista = GestoreFile.carica();
        Inquilino inq = Inquilino.trovaPerId(lista, leggiInt("ID inquilino: "));
        if (inq == null) { System.out.println("Non trovato."); return; }
        System.out.print("Tipo: ");
        String tipo  = sc.nextLine();
        System.out.print("Matricola: ");
        String matr  = sc.nextLine();
        System.out.print("Unita': ");
        String unita = sc.nextLine();
        inq.contatori.add(new Contatore(Contatore.prossimoId(lista), tipo, matr, unita));
        GestoreFile.salva(lista);
        System.out.println("Contatore aggiunto.");
    }

    static void rimuoviContatore() {
        List<Inquilino> lista = GestoreFile.carica();
        Inquilino inq = Inquilino.trovaPerId(lista, leggiInt("ID inquilino: "));
        if (inq == null) {
            System.out.println("Non trovato.");
            return;
        }
        inq.stampaContatori();
        Contatore cont = inq.trovaContatore(leggiInt("ID contatore: "));
        if (cont == null) {
            System.out.println("Non trovato.");
            return;
        }
        inq.contatori.remove(cont);
        GestoreFile.salva(lista);
        System.out.println("Rimosso.");
    }

    static void inserisciLettura() {
        List<Inquilino> lista = GestoreFile.carica();
        Inquilino inq = Inquilino.trovaPerId(lista, leggiInt("ID inquilino: "));
        if (inq == null) {
            System.out.println("Non trovato.");
            return;
        }
        inq.stampaContatori();

        Contatore cont = inq.trovaContatore(leggiInt("ID contatore: "));
        if (cont == null) {
            System.out.println("Non trovato.");
            return;
        }

        Lettura ultima = cont.ultimaLettura();
        if (ultima != null) System.out.println("Ultima: " + ultima.valore + " " + cont.unitaMisura + " del " + ultima.data);

        System.out.print("Data (yyyy-MM-dd) [Invio=oggi]: ");
        String data = sc.nextLine().trim();
        if (data.isEmpty())
            data = LocalDate.now().toString();

        double valore  = leggiDouble("Valore (" + cont.unitaMisura + "): ");
        double consumo;

        if (ultima == null) {
            consumo = -1;
        } else {
            consumo = Math.max(valore - ultima.valore, 0);
        }
        System.out.print("Note: ");
        cont.letture.add(new Lettura(Lettura.prossimoId(lista), data, valore, consumo, sc.nextLine()));
        GestoreFile.salva(lista);
        if (consumo < 0) {
            System.out.println("Prima lettura salvata.");
        } else {
            System.out.println("Consumo: " + consumo + cont.unitaMisura);
        }

    }

    static void storicoLetture() {
        List<Inquilino> lista = GestoreFile.carica();
        Inquilino inq = Inquilino.trovaPerId(lista, leggiInt("ID inquilino: "));
        if (inq == null) {
            System.out.println("Non trovato.");
            return;
        }
        inq.stampaContatori();
        Contatore cont = inq.trovaContatore(leggiInt("ID contatore: "));
        if (cont == null) {
            System.out.println("Non trovato.");
            return;
        }
        cont.stampaStorico();
    }

    static int leggiInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            }
            catch (NumberFormatException e) {
                System.out.println("Numero intero richiesto.");
            }
        }
    }

    static double leggiDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
            }
            catch (NumberFormatException e) {
                System.out.println("Valore numerico richiesto.");
            }
        }
    }
}

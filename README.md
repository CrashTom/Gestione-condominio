# Spiegazione del codice

Il progetto e' composto da 5 classi.

---

## Lettura

Rappresenta una singola lettura del contatore. Contiene la data, il valore
letto, il consumo calcolato rispetto alla lettura precedente e una nota
opzionale. Il metodo statico `prossimoId` scorre tutta la lista per trovare
l'ID piu' alto e restituisce il successivo.

---

## Contatore

Rappresenta un contatore fisico (luce, gas, acqua). Contiene la matricola,
l'unita' di misura e la lista delle letture. Il metodo `ultimaLettura`
restituisce l'ultima lettura inserita, `stampa` la mostra a schermo e
`stampaStorico` stampa la tabella completa con il consumo totale.

---

## Inquilino

Rappresenta un inquilino del condominio con i suoi dati anagrafici e la lista
dei contatori associati. Contiene i metodi statici `trovaPerId` e `prossimoId`
per cercare un inquilino nella lista e generare un nuovo ID. Il metodo
`trovaContatore` cerca un contatore per ID tra quelli dell'inquilino, evitando
di ripetere lo stesso ciclo nel Main.

---

## GestoreFile

Si occupa esclusivamente di leggere e scrivere il file `inquilini.dat`. Usa la
serializzazione nativa di Java: `ObjectOutputStream` per salvare e
`ObjectInputStream` per caricare. Non serve nessuna libreria esterna perche'
Java sa gia' come convertire oggetti in byte e viceversa, a patto che le classi
implementino `Serializable`.

---

## Main

Contiene solo il menu e i metodi di ogni voce. Ogni metodo segue sempre lo
stesso schema: carica la lista dal file, chiede i dati all'utente, chiama i
metodi delle classi, salva. I due metodi `leggiInt` e `leggiDouble` gestiscono
l'input in modo che il programma non vada in crash se l'utente scrive qualcosa
di sbagliato.

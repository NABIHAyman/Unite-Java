/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POO;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell-info
 */
public class Tache {
    private int id;
    private String titre;
    private String datedebut;
    private String datefin;
    private String etat;
    private int id_etudiant;
    private List<Note> notes;

    // Constructeur
    public Tache(int id, String titre, String datedebut, String datefin, String etat, int id_etudiant) {
        this.id = id;
        this.titre = titre;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.etat = etat;
        this.id_etudiant = id_etudiant;
        this.notes = new ArrayList<>();
    }

    // Méthodes pour ajouter une note
    public void ajouterNote(Note note) {
        this.notes.add(note);
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDateDebut() {
        return datedebut;
    }
    
    public void seDateDebut(String date) {
        this.datedebut = date;
    }
    
    public String getDateFin() {
        return datefin;
    }
    
    public void seDateFin(String date) {
        this.datefin = date;
    }
    
    public int getIdEtudiant() {
        return id_etudiant;
    }
    
    public List<Note> getNotes() {
        return notes;
    }
}


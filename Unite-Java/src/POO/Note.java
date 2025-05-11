/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POO;

/**
 *
 * @author dell-info
 */
public class Note {
    private int id;
    private String titre;
    private String contenu;
    private int id_tache;

    // Constructeur
    public Note(int id, String titre, String contenu, int id_tache) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.id_tache = id_tache;
    }

    // Getters et Setters
    public int getID() {
        return id;
    }
    
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getIDTache() {
        return id_tache;
    }

    public void setIDTache(int id_tache) {
        this.id_tache = id_tache;
    }
}


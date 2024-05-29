/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conferencemanager;

/**
 *
 * @author nourm
 */
public class comite {
    private int id_comite;
    private String nom;
    private String type;

    public int getId_comite() {
        return id_comite;
    }

    public void setId_comite(int id_comite) {
        this.id_comite = id_comite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public comite(int id_comite, String nom, String type) {
        this.id_comite = id_comite;
        this.nom = nom;
        this.type = type;
    }
     public comite() {
        this.id_comite = 0;
        this.nom = "";
        this.type = "";
    }
    
    
}

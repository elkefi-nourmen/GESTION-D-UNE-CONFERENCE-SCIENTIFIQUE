/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conferencemanager;

/**
 *
 * @author nourm
 */
public class memberinfo {
    private int id_membrecomite;
    private String nom;

    public int getId_membrecomite() {
        return id_membrecomite;
    }

    public void setId_membrecomite(int id_membrecomite) {
        this.id_membrecomite = id_membrecomite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public memberinfo(int id_membrecomite, String nom) {
        this.id_membrecomite = id_membrecomite;
        this.nom = nom;
    }
}

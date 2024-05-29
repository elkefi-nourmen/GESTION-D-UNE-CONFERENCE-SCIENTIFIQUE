/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conferencemanager;

/**
 *
 * @author nourm
 */
public class keynotespeaker {
      private int id_keynotespeaker;
    private String nom;
    private String institution;
    private String pays_origine;
    private String titre_presentation;
    private int id_conference;

    public keynotespeaker(int id_keynotespeaker, String nom, String institution, String pays_origine, String titre_presentation, int id_conference) {
        this.id_keynotespeaker = id_keynotespeaker;
        this.nom = nom;
        this.institution = institution;
        this.pays_origine = pays_origine;
        this.titre_presentation = titre_presentation;
        this.id_conference = id_conference;
    }

     public keynotespeaker() {
        this.id_keynotespeaker =0;
        this.nom = "";
        this.institution = "";
        this.pays_origine = "";
        this.titre_presentation = "";
        this.id_conference = 0;
    }

  

    public int getId_keynotespeaker() {
        return id_keynotespeaker;
    }

    public void setId_keynotespeaker(int id_keynotespeaker) {
        this.id_keynotespeaker = id_keynotespeaker;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getPays_origine() {
        return pays_origine;
    }

    public void setPays_origine(String pays_origine) {
        this.pays_origine = pays_origine;
    }

    public String getTitre_presentation() {
        return titre_presentation;
    }

    public void setTitre_presentation(String titre_presentation) {
        this.titre_presentation = titre_presentation;
    }

    public int getId_conference() {
        return id_conference;
    }

    public void setId_conference(int id_conference) {
        this.id_conference = id_conference;
    }
}

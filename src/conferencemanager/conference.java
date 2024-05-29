/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conferencemanager;

import java.util.Date;

/**
 *
 * @author nourm
 */
public class conference {
    
    public static conference confr;
    
    private int id_conference;
    private int president;
    private String titre;
    private String institution;
    private String lieu;
    private String duree;
    private String themes;
    private Float frais;
    private int comite_sci;
    private int comite_org;
    private Date date_soumission;
    private Date date_inscription;

    public conference(int id_conference, String titre, String institution, String lieu, String duree, float frais, String themes, java.sql.Date date_soumission, java.sql.Date date_inscription) {
    this.id_conference = id_conference;
    this.titre = titre;
    this.institution = institution;
    this.lieu = lieu;
    this.duree = duree;
    this.frais = frais;
    this.themes = themes;
    this.date_soumission = date_soumission;
    this.date_inscription = date_inscription;
}

     public conference() {
        this.id_conference = 0;
    this.titre = "";
    this.institution = "";
    this.lieu = "";
    this.duree = "";
    this.frais = null;
    this.themes = "";
    this.date_soumission = null;
    this.date_inscription = null;
    this.president=0;
    this.comite_org=0;
    this.comite_sci=0;
    }

   
    public int getId_conference() {
        return id_conference;
    }

    public void setId_conference(int id_conference) {
        this.id_conference = id_conference;
    }
     public int getPresident() {
        return president;
    }

    public void setPresident(int president) {
        this.president = president;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getThemes() {
        return themes;
    }

    public void setThemes(String themes) {
        this.themes = themes;
    }

    public int getComite_sci() {
        return comite_sci;
    }

    public void setComite_sci(int comite_sci) {
        this.comite_sci = comite_sci;
    }

    public int getComite_org() {
        return comite_org;
    }

    public void setComite_org(int comite_org) {
        this.comite_org = comite_org;
    }

    public float getFrais() {
        return frais;
    }

    public void setFrais(float frais) {
        this.frais = frais;
    }

    public Date getDate_soumission() {
        return date_soumission;
    }

    public void setDate_soumission(Date date_soumission) {
        this.date_soumission = date_soumission;
    }

    public Date getDate_inscription() {
        return date_inscription;
    }

    public void setDate_inscription(Date date_inscription) {
        this.date_inscription = date_inscription;
    }
    
public static void setConfr(conference conference) {
        confr = conference;
    }

    public static conference getConfr() {
        return confr;
    }
    
}

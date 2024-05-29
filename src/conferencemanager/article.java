/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conferencemanager;
import java.awt.desktop.*;
/**
 *
 * @author nourm
 */
public class article {
 private int id_article;
 private String titre;
 private  byte[] fichier;
 private String status;
 private int member;
 private int id_conference;

  public article() {
        this.id_article = 0;
        this.titre ="";
        this.fichier = null;
        this.status = "";
        this.member = 0;
        this.id_conference=0;
    }
    public article(int id_article, String titre, byte[] fichier, String status, int member,int id_conference) {
        this.id_article = id_article;
        this.titre = titre;
        this.fichier = fichier;
        this.status = status;
        this.member = member;
        this.id_conference=id_conference;
    }

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }
    
    
     public int getId_conference() {
        return id_conference;
    }

    public void setId_conference(int id_conference) {
        this.id_article = id_conference;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public byte[] getFichier() {
        return fichier;
    }

    public void setFichier(byte[] fichier) {
        this.fichier = fichier;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMember() {
        return member;
    }

    public void setMember(int member) {
        this.member = member;
    }
 
}

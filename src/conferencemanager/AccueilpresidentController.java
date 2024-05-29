/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package conferencemanager;

import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import java.time.LocalDate;


/**
 * FXML Controller class
 *
 * @author nourm
 */
public class AccueilpresidentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    TableView <conference> tabconf;
    @FXML
    TableColumn <conference,Integer> idconf;
    @FXML
    TableColumn <conference,String> titreconf;
    @FXML
    TableColumn <conference,String> instconf;
    @FXML
    TableColumn <conference,String> lieuconf;
    @FXML
    TableColumn <conference,String> dureeconf;
    @FXML
    TableColumn <conference,Float> fraisconf;
    @FXML
    TableColumn <conference,String> themesconf;
     @FXML
    TableColumn <conference,Date> datesoumconf;
    @FXML
    TableColumn <conference,Date> dateinscritconf;
    @FXML
     TableView <comite>tabcomsci;
    @FXML
      TableColumn <comite,Integer> idcoms;
     @FXML
      TableColumn <comite,String> nomcoms; 
              @FXML
     TableView <comite>tabcomorg;
    @FXML
      TableColumn <comite,Integer> idcomo;
     @FXML
      TableColumn <comite,String> nomcomo; 
ObservableList<comite> listcoms;
ObservableList<comite> listcomo;

@FXML 
  Pane paneconf;
@FXML 
  Pane panenewconf;

ObservableList<conference> listc;
@FXML
TextField titrec;
@FXML
TextField institutionc;
@FXML
TextField lieuc;
@FXML
TextField dureec;
@FXML
TextField fraisc;
@FXML
TextArea themesc;
@FXML
DatePicker datesoum;
@FXML
DatePicker dateinscrit;
@FXML
Label erreur;
@FXML
Label err;
@FXML
ComboBox idcomsc;
@FXML
ComboBox idcomoc;
@FXML 
TextField nomn;
@FXML 
TextField titren;
@FXML 
TextField paysn;
@FXML 
TextField institutionn;
@FXML 
TableView tabnote;
@FXML 
TableColumn <keynotespeaker,Integer> id;

@FXML 
TableColumn <keynotespeaker,String> nomnote;
@FXML 
TableColumn <keynotespeaker,String> paysnote;
@FXML 
TableColumn <keynotespeaker,String> titrenote;
@FXML 
TableColumn <keynotespeaker,String> institutionnote;
ObservableList<keynotespeaker> listn;
@FXML
Pane keynotepane;
@FXML
Pane paneart;


private conference conf=new conference();

public void visible1(Event e){
paneconf.setVisible(true);
panenewconf.setVisible(false);
keynotepane.setVisible(false);
paneart.setVisible(false);
}
public void visible2(Event e){
panenewconf.setVisible(true);
paneconf.setVisible(false);
keynotepane.setVisible(false);
paneart.setVisible(false);

}
public void ajouter(Event e) throws SQLException {
   
if (idcomsc.getSelectionModel().getSelectedItem()==null ||idcomoc.getSelectionModel().getSelectedItem()==null)
{        erreur.setText("vous devez remplir tous les champs!");
return;
}
    if (titrec.getText().isEmpty() || institutionc.getText().isEmpty() || lieuc.getText().isEmpty() || dureec.getText().isEmpty() ||  fraisc.getText().isEmpty() ||  themesc.getText().isEmpty() ||  datesoum.getValue() == null ||  dateinscrit.getValue() == null ) {
        erreur.setText("vous devez remplir tous les champs!");
        return;
    }
 int selectedOptions = (int) idcomsc.getSelectionModel().getSelectedItem();
    int selectedOptiono = (int) idcomoc.getSelectionModel().getSelectedItem();
    conference newConf = new conference();
    newConf.setTitre(titrec.getText()); 
    newConf.setInstitution(institutionc.getText());
    newConf.setDuree(dureec.getText());
    newConf.setLieu(lieuc.getText());
    newConf.setComite_sci(selectedOptions);
    newConf.setComite_org(selectedOptiono);
    newConf.setDate_inscription(java.util.Date.from(dateinscrit.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
    newConf.setDate_soumission(java.util.Date.from(datesoum.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
    newConf.setPresident(utilisateur.user.getId_utilisateur());
    String fraiscText = fraisc.getText();
    float frais = 0.0f; // Default value in case parsing fails

    try {
        frais = Float.parseFloat(fraiscText);
    } catch (NumberFormatException ex) {
        ex.printStackTrace();
    }

    newConf.setFrais(frais);
    newConf.setThemes(themesc.getText());

    if (db.ajoutconf(newConf)) {
        listc = db.afficher_conf();
        tabconf.setItems(listc); 
        paneconf.setVisible(true);
panenewconf.setVisible(false);
    } else {
        JOptionPane.showMessageDialog(null, "conference existante!");
    }
}

public void modif_conf1(Event e){
    conference selectedConf = tabconf.getSelectionModel().getSelectedItem();
    conference c=new conference();
if (selectedConf != null) {
        try {
            int id=selectedConf.getId_conference();
            System.out.println("modif1 "+id);
            c=db.selectconf(id);
            titrec.setText(c.getTitre());
            institutionc.setText(c.getInstitution());
            dureec.setText(c.getDuree());
            themesc.setText(c.getThemes());
            lieuc.setText(c.getLieu());
fraisc.setText(String.valueOf(c.getFrais()));
            idcomsc.setValue(c.getComite_sci());
            idcomoc.setValue(c.getComite_org());
  datesoum.setValue(Instant.ofEpochMilli(c.getDate_soumission().getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());

            dateinscrit.setValue(Instant.ofEpochMilli(c.getDate_inscription().getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
c.setId_conference(id);
conference.setConfr(c);
           panenewconf.setVisible(true);
paneconf.setVisible(false);    
            
        } catch (SQLException ex) {
            Logger.getLogger(AccueilpresidentController.class.getName()).log(Level.SEVERE, null, ex);
            paneconf.setVisible(true);
panenewconf.setVisible(false);
        }
}

   
}

public void modif_conf2(Event e) throws SQLException {
    Integer selectedOptions = (Integer) idcomsc.getValue();
    Integer selectedOptiono = (Integer) idcomoc.getValue();

    conference newConf = new conference();
    newConf.setTitre(titrec.getText()); 
    newConf.setInstitution(institutionc.getText());
    newConf.setDuree(dureec.getText());
    newConf.setLieu(lieuc.getText());

    newConf.setComite_sci(selectedOptions);
    newConf.setComite_org(selectedOptiono);
    newConf.setDate_inscription(java.util.Date.from(dateinscrit.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
    newConf.setDate_soumission(java.util.Date.from(datesoum.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
    newConf.setPresident(utilisateur.user.getId_utilisateur());
    String fraiscText = fraisc.getText();
    float frais = 0.0f; // Default value in case parsing fails

    try {
        frais = Float.parseFloat(fraiscText);
    } catch (NumberFormatException ex) {
        ex.printStackTrace();
    }

    newConf.setFrais(frais);
    newConf.setThemes(themesc.getText());
    newConf.setId_conference(conference.getConfr().getId_conference());
    
    if(db.updateconf(newConf)) {
        JOptionPane.showMessageDialog(null, "Modification terminée avec succès!");
        
        // Get the updated list of conferences from the database
        listc = db.afficher_conf();
        
        // Set the updated list to the TableView
        tabconf.setItems(listc);
        
        // Refresh the TableView to reflect the changes
        tabconf.refresh();
        
        paneconf.setVisible(true);
        panenewconf.setVisible(false);
    } else {
        JOptionPane.showMessageDialog(null, "Modification impossible!");
    }
}


private int selectedConfId = -1; 

    
    public void supprimerconf(Event e) throws SQLException {
    conference selectedConf = tabconf.getSelectionModel().getSelectedItem();
    if (selectedConf != null) {
        selectedConfId = selectedConf.getId_conference();
        if (db.deleteconf(selectedConfId)) {
            listc.remove(selectedConf); // Remove the selected conference from the ObservableList
            tabconf.setItems(listc); // Update the TableView
            JOptionPane.showMessageDialog(null, "suppression avec succes!");
        }
    }
}
    
    
    
    public void aff_keynotespeakers(Event e)
    {
        conference selectedConf = tabconf.getSelectionModel().getSelectedItem();

    if (selectedConf != null) {
            selectedConfId = selectedConf.getId_conference();
            conference.confr=new conference();
    conference.confr.setId_conference(selectedConfId);
    id.setCellValueFactory(new PropertyValueFactory<keynotespeaker,Integer>("id_keynotespeaker"));
    nomnote.setCellValueFactory(new PropertyValueFactory<keynotespeaker,String>("nom"));
    titrenote.setCellValueFactory(new PropertyValueFactory<keynotespeaker,String>("titre_presentation"));
    institutionnote.setCellValueFactory(new PropertyValueFactory<keynotespeaker,String>("institution"));
    paysnote.setCellValueFactory(new PropertyValueFactory<keynotespeaker,String>("pays_origine"));
            try {
                listn=db.afficher_notespeakers(selectedConfId);
            } catch (SQLException ex) {
                Logger.getLogger(AccueilpresidentController.class.getName()).log(Level.SEVERE, null, ex);
            }

tabnote.setItems(listn);
    paneconf.setVisible(false);
panenewconf.setVisible(false);
keynotepane.setVisible(true);
    
    }
    
    
   
    }
      public void supprimerkeynote(Event e) throws SQLException {
    keynotespeaker selectedkeynote =(keynotespeaker) tabnote.getSelectionModel().getSelectedItem();
    if (selectedkeynote != null) {
        int selectedkeynoteid = selectedkeynote.getId_keynotespeaker();
        if (db.deletekeynote(selectedkeynoteid)) {
            listn.remove(selectedkeynote); 
            tabnote.setItems(listn); // Update the TableView
        }
        else         err.setText("erreur de suppression!");

    }
}
      
      
      public void ajouter_keynotespeaker(Event e) throws SQLException{
      keynotespeaker k=new keynotespeaker();
      k.setId_conference(conference.getConfr().getId_conference());
      k.setNom(nomn.getText());
      k.setInstitution(institutionn.getText());
      k.setPays_origine(paysn.getText());
      k.setTitre_presentation(titren.getText());
      
       if (titren.getText().isEmpty() || institutionn.getText().isEmpty() || paysn.getText().isEmpty() || nomn.getText().isEmpty()) {
        err.setText("vous devez remplir tous les champs!");
        return;
    }
      
      if(db.insert_keynotespeaker(k))
      {
       listn = db.afficher_notespeakers(k.getId_conference());
        tabnote.setItems(listn); // Update the TableView
      }
      else 
      {            JOptionPane.showMessageDialog(null, "keynotespeaker existant!");
}
      
      }
      
      
      public void modif_keynote1(Event e)
      {
      keynotespeaker selectedkeynote =(keynotespeaker) tabnote.getSelectionModel().getSelectedItem();
    if (selectedkeynote != null) {
      nomn.setText(selectedkeynote.getNom());
      titren.setText(selectedkeynote.getTitre_presentation());
      paysn.setText(selectedkeynote.getPays_origine());
      institutionn.setText(selectedkeynote.getInstitution());
      }
      }
      public void modif_keynote2(Event e) {
    keynotespeaker selectedkeynote = (keynotespeaker) tabnote.getSelectionModel().getSelectedItem();
    if (selectedkeynote != null) {
        keynotespeaker k = new keynotespeaker();
        k.setId_keynotespeaker(selectedkeynote.getId_keynotespeaker());
        k.setNom(nomn.getText());
        k.setTitre_presentation(titren.getText());
        k.setPays_origine(paysn.getText());
        k.setInstitution(institutionn.getText());
        
        try {
            if (db.update_keynotespeaker(k)) {
                // Update the selected item in the ObservableList
                int selectedIndex = tabnote.getSelectionModel().getSelectedIndex();
                listn.set(selectedIndex, k);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccueilpresidentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

      
     @FXML
     TableView tabart;
     @FXML
     TableColumn <article,Integer> idar;
@FXML
     TableColumn <article,String> nomar;
@FXML
     TableColumn <article,String> statart;
    

  @FXML
     TableView tabmem;
     @FXML
     TableColumn <article,Integer> idmem;
@FXML
     TableColumn <article,String> nommem;

  public void aficher_article(Event e){
      conference c=new conference();
    try {
         conference selectedConf = tabconf.getSelectionModel().getSelectedItem();

    if (selectedConf != null) {
    conference.setConfr(selectedConf);
             c=db.selectconf(conference.confr.getId_conference());
             

    }
       
        ObservableList<article> articles = db.select_tous_art(conference.confr.getId_conference()); 
                ObservableList<memberinfo> members = db.getMembersInfo(c.getComite_sci()); 

        tabart.setItems(articles);
        tabmem.setItems(members);

        idmem.setCellValueFactory(new PropertyValueFactory<>("id_membrecomite"));
                nommem.setCellValueFactory(new PropertyValueFactory<>("nom"));

        idar.setCellValueFactory(new PropertyValueFactory<>("id_article"));
        nomar.setCellValueFactory(new PropertyValueFactory<>("titre"));
        statart.setCellValueFactory(new PropertyValueFactory<>("status"));
         panenewconf.setVisible(false);
paneconf.setVisible(false);
keynotepane.setVisible(false);
paneart.setVisible(true);
        
    } catch (SQLException ex) {
        Logger.getLogger(AccueilpresidentController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
    
    
} 
 
  public void affecter_art(Event e) throws SQLException {
    article selectedArticle = (article) tabart.getSelectionModel().getSelectedItem();
    memberinfo selectedMember = (memberinfo) tabmem.getSelectionModel().getSelectedItem();

    if (selectedArticle != null && selectedMember != null && selectedArticle.getStatus().equals("NA")) {
        db.affecter_art(selectedArticle, selectedMember);
        selectedArticle.setStatus("UE");
        tabart.refresh();
    }
} 
      
      
    public void sortir(){
   Platform.exit();
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            idconf.setCellValueFactory(new PropertyValueFactory<conference,Integer>("id_conference"));
            titreconf.setCellValueFactory(new PropertyValueFactory<conference,String>("titre"));
            instconf.setCellValueFactory(new PropertyValueFactory<conference,String>("institution"));
            lieuconf.setCellValueFactory(new PropertyValueFactory<conference,String>("lieu"));
            dureeconf.setCellValueFactory(new PropertyValueFactory<conference,String>("duree"));
            fraisconf.setCellValueFactory(new PropertyValueFactory<conference,Float>("frais"));
            themesconf.setCellValueFactory(new PropertyValueFactory<conference,String>("themes"));
            datesoumconf.setCellValueFactory(new PropertyValueFactory<conference,Date>("date_soumission"));
            dateinscritconf.setCellValueFactory(new PropertyValueFactory<conference,Date>("date_inscription"));
            listc=db.afficher_conf();
            tabconf.setItems(listc);
            
            
            
            idcoms.setCellValueFactory(new PropertyValueFactory<comite,Integer>("id_comite"));
            nomcoms.setCellValueFactory(new PropertyValueFactory<comite,String>("nom"));
            listcoms=db.afficher_coms();
            tabcomsci.setItems(listcoms);
            
             idcomo.setCellValueFactory(new PropertyValueFactory<comite,Integer>("id_comite"));
            nomcomo.setCellValueFactory(new PropertyValueFactory<comite,String>("nom"));
            listcomo=db.afficher_coms();
            tabcomorg.setItems(listcomo);
            
             ObservableList<Integer> idComiteListSci = FXCollections.observableArrayList();
        ObservableList<Integer> idComiteListOrg = FXCollections.observableArrayList();

        for (comite c : tabcomsci.getItems()) {
            idComiteListSci.add(c.getId_comite());
        }

        for (comite c : tabcomorg.getItems()) {
            idComiteListOrg.add(c.getId_comite());
        }

        idcomsc.setItems(idComiteListSci);
        idcomoc.setItems(idComiteListOrg);
        } catch (SQLException ex) {
            Logger.getLogger(AccueilpresidentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }    
    
}

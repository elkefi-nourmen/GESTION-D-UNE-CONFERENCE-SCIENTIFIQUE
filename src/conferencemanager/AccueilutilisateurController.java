/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package conferencemanager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author nourm
 */
public class AccueilutilisateurController implements Initializable {
private conference conf=new conference();

   @FXML
    TableView <conference> tabconf2;
    @FXML
    TableColumn <conference,Integer> idconf2;
    @FXML
    TableColumn <conference,String> titreconf2;
    @FXML
    TableColumn <conference,String> instconf2;
    @FXML
    TableColumn <conference,String> lieuconf2;
    @FXML
    TableColumn <conference,String> dureeconf2;
    @FXML
    TableColumn <conference,Float> fraisconf2;
    @FXML
    TableColumn <conference,String> themesconf2;
     @FXML
    TableColumn <conference,Date> datesoumconf2;
    @FXML
    TableColumn <conference,Date> dateinscritconf2;
   ObservableList<conference> listconf;
@FXML
   ComboBox <String>methp;
@FXML 
Label err;
public void inscription_participants(Event e) throws SQLException{
String modep=(String) methp.getValue();
         conference selectedConf = tabconf2.getSelectionModel().getSelectedItem();
          Date aujourdhui = new Date();
         if (aujourdhui.compareTo(selectedConf.getDate_inscription()) >0)
         {err.setText(" delai d'inscription expiré");}
if(db.inscription(selectedConf, modep))
{
   err.setText("inscription terminé");
}
else err.setText("vous avez deja insrit ");
}


 public void sortir(){
   Platform.exit();
   }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //initializer le mode de paiement 
         ObservableList<String> methodepaiement = FXCollections.observableArrayList("paypal", "e-dinar");
    methp.setItems(methodepaiement);
    // remplir le tableau de conferences 
         try {
            idconf2.setCellValueFactory(new PropertyValueFactory<conference,Integer>("id_conference"));
            titreconf2.setCellValueFactory(new PropertyValueFactory<conference,String>("titre"));
            instconf2.setCellValueFactory(new PropertyValueFactory<conference,String>("institution"));
            lieuconf2.setCellValueFactory(new PropertyValueFactory<conference,String>("lieu"));
            dureeconf2.setCellValueFactory(new PropertyValueFactory<conference,String>("duree"));
            fraisconf2.setCellValueFactory(new PropertyValueFactory<conference,Float>("frais"));
            themesconf2.setCellValueFactory(new PropertyValueFactory<conference,String>("themes"));
            datesoumconf2.setCellValueFactory(new PropertyValueFactory<conference,Date>("date_soumission"));
            dateinscritconf2.setCellValueFactory(new PropertyValueFactory<conference,Date>("date_inscription"));
            listconf=db.afficher_conf();
            tabconf2.setItems(listconf);
             } catch (SQLException ex) {
            Logger.getLogger(AccueilpresidentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}

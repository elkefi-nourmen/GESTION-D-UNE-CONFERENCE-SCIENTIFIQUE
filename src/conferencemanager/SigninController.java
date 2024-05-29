/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package conferencemanager;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class SigninController implements Initializable {// signup controller 
     private Parent root;
private Scene scene;
private Stage stage;
@FXML
ComboBox <String>type;
 @FXML
 TextField nom;
 @FXML
 TextField prenom;
 @FXML
 TextField email;
 @FXML
 PasswordField password;
 @FXML
 Label error;
 public utilisateur util=new utilisateur();
 
public void signup(Event e) throws SQLException, IOException{
String selectedOption = type.getSelectionModel().getSelectedItem();
 if (nom.getText().isEmpty() || prenom.getText().isEmpty() || email.getText().isEmpty() || password.getText().isEmpty() || selectedOption == null) {
error.setText("vous devez remplir tous les champs!");   
return;
    }
util.setNom(nom.getText());
util.setEmail(email.getText());
util.setType(selectedOption);
util.setPassword(password.getText());
util.setPrenom(prenom.getText());

if(db.insert(util))
{        utilisateur.setUser(db.infos(util.getEmail(), util.getPassword()));
         JOptionPane.showMessageDialog(null,"inscription reussite!");
if("president".equals(utilisateur.user.getType()))
         { root =FXMLLoader.load(getClass().getResource("Accueilpresident.fxml")); 
  stage=(Stage)((Node)e.getSource()).getScene().getWindow();
scene=new Scene(root);
stage.setScene(scene);
 stage.setTitle("Accueil");
stage.show();}
}
else
error.setText("Compte déja existe! ");

}

public void switchscene(Event e) throws IOException{
 root =FXMLLoader.load(getClass().getResource("login.fxml")); 
  stage=(Stage)((Node)e.getSource()).getScene().getWindow();
scene=new Scene(root);
stage.setScene(scene);
stage.show();
}
public void sortir(){
   Platform.exit();
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        type.setItems(FXCollections.observableArrayList("president","utilisateur simple","comite_scientifique","auteur","comite_organisation"));
    }    
    
}

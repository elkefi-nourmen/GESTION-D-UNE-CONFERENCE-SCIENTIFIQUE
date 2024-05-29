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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LoginController implements Initializable {
    private Parent root;
private Scene scene;
private Stage stage;
    @FXML
     TextField mail;
     @FXML
     PasswordField password;
     @FXML
     Label error;
     
     
public void login(Event e) throws SQLException, IOException{
    String email=mail.getText();
    String pass=password.getText();
    
    if(db.islogin(email, pass)){
        utilisateur.setUser(db.infos(email,pass));
         if("president".equals(utilisateur.user.getType()))
         { root =FXMLLoader.load(getClass().getResource("Accueilpresident.fxml")); 
  stage=(Stage)((Node)e.getSource()).getScene().getWindow();
scene=new Scene(root);
stage.setScene(scene);
stage.setTitle("Accueil");
stage.show();}else  if("utilisateur simple".equals(utilisateur.user.getType()))
         { root =FXMLLoader.load(getClass().getResource("accueilutilisateur.fxml")); 
  stage=(Stage)((Node)e.getSource()).getScene().getWindow();
scene=new Scene(root);
stage.setScene(scene);
stage.setTitle("Accueil");
stage.show();}
    }
    
    else
error.setText("Compte inexistante!");     }
     
public void switchscene(Event e) throws IOException{
 root =FXMLLoader.load(getClass().getResource("signup.fxml")); 
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
       
    }    
    
}

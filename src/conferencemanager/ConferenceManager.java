/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package conferencemanager;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
// 
/**
 *
 * @author nourm
 */
public class ConferenceManager extends Application {

    @Override
    public void start(Stage stage) throws IOException {
       
     Parent root =FXMLLoader.load(getClass().getResource("login.fxml")); 
     Scene scene=new Scene(root);
     stage.setScene(scene);
     stage.setTitle("login");
     stage.show();  
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
            

    }
    
}

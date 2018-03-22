/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Dao.Database;
import UI.Login;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author ilmar
 */
public class KysymysGeneraattoriGUI extends Application{

    
    @Override
    public void start(Stage ikkuna) {
        
        Button nappi = new Button("Tämä on nappi");

        BorderPane komponenttiryhma = new BorderPane();
        komponenttiryhma.setLeft(nappi);
        //komponenttiryhma.setRight(taulu);

        Scene nakyma = new Scene(komponenttiryhma, 640, 480);
        
        ikkuna.setScene(nakyma);
        ikkuna.show();
        
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        launch(KysymysGeneraattoriGUI.class);
    }
    
}
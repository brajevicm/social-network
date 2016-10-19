/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author milos
 */
public class BrowserHelpFXMLController implements Initializable {

    @FXML
    private WebView webView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Task t = new Task() {
            @Override
            protected Object call() throws Exception {
                WebEngine webEngine = webView.getEngine();
                webEngine.load("http://www.metropolitan.ac.rs");
                return null;
            }
        };        
        Platform.runLater(t);
    }

}

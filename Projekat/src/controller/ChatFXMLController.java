/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author milos
 */
public class ChatFXMLController implements Initializable {

    BufferedReader in;
    PrintWriter out;
    
    @FXML
    private TextField textField;
    
    @FXML
    private TextArea messageArea;
    
    @FXML
    private String getServerAddress() {
        return "localhost";
    }
    
    @FXML
    private void run() {
        try {
            String serverAddress = getServerAddress();
            Socket socket = new Socket(serverAddress, 9000);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String line = in.readLine();
                messageArea.appendText(line.substring(8) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();;
        }
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ChatFXMLController client = new ChatFXMLController();
        client.run();
        
    }    
    
}

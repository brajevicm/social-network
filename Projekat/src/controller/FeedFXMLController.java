/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Post;
import model.SessionSingleton;
import model.User;

/**
 * FXML Controller class
 *
 * @author milos
 */
public class FeedFXMLController implements Initializable {

    User user = SessionSingleton.getInstance().getCurrentUser();
    
    private ArrayList<Post> posts;
    
    @FXML
    private VBox root;
    
    @FXML
    private TextArea textArea;

    public FeedFXMLController() {
        posts = new ArrayList<Post>();
    }
    
    @FXML
    public void addPost() {
//        NewsFeed feed = new NewsFeed();
//        feed.setPost(textArea.getText());
//        posts.add(feed);
//        textArea.setText("");
    }
    
    @FXML
    private void showPosts() {
        if (!posts.isEmpty()){

            for (Post p : posts) {
                Label label1 = new Label(user.getFirstName() +
                        " " + user.getLastName());
                label1.setStyle("-fx-font: 14 system;");
                label1.setTextFill(Color.WHITE);
                root.getChildren().add(label1);

                Label label2 = new Label("\t" + p.toString());
                label2.setTextFill(Color.WHITE);
                root.getChildren().add(label2);

                HBox hbox =  new HBox();
                hbox.setAlignment(Pos.BASELINE_RIGHT);
                
                Label label3 = new Label("Like");
                label3.setTextFill(Color.WHITE);
                hbox.getChildren().add(label3);
                
                Label label5 = new Label("  " + "11");
                label5.setTextFill(Color.WHITE);
                hbox.getChildren().add(label5);
                
                root.getChildren().add(hbox);
                
                Label label4 = new Label("\n");
                label4.setTextFill(Color.WHITE);
                root.getChildren().add(label4);

                posts.remove(p);
            }
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showPosts();
    }    
    
}

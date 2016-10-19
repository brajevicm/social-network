/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import model.Animations;
import service.MySQL;
import model.SessionSingleton;
import model.User;

/**
 * FXML Controller class
 *
 * @author milos
 */
public class ProfleFXMLController implements Initializable {

    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    User user = SessionSingleton.getInstance().getCurrentUser();
    
    private final Animations animation;

    public ProfleFXMLController() {
        animation = new Animations();
    }
    
    @FXML
    private MenuButton btnProfile;
    
    @FXML
    private StackPane stackPane;
    
    @FXML
    private Label lblTitleName;
    
    @FXML
    private ImageView imageProfile;
    
    @FXML
    private ImageView imageSocialFacebook;
    
    @FXML
    private ImageView imageSocialTwitter;
    
    @FXML
    private ImageView imageSO;
    
    @FXML
    private ImageView image;
    
    @FXML
    public void showName() {
        lblTitleName.setText(user.getFirstName() + " " + user.getLastName());
    }
    
    @FXML
    private void showImage() {
        if(user.getImage() != null){
            Image image = new Image(user.getImage());
            imageProfile.setImage(image);
        }
    }
    
    @FXML
    private void showSocial(){
        if(!user.getFacebook().equalsIgnoreCase("") || user.getFacebook() != null)
            imageSocialFacebook.setImage(new Image("/icons/facebook.png"));
        else
            imageSocialFacebook.setImage(null);
    }
    
    @FXML
    private void selectGeneral() {
        try {
            btnProfile.setText("General");
            Parent mainMenu = FXMLLoader.load(getClass().getResource("/view/ProfileGeneralFXML.fxml"));
            stackPane.getChildren().remove(0, stackPane.getChildren().size());
            stackPane.getChildren().add(mainMenu);
            animation.applyFadeAnimationOn(stackPane, 500, 0f, 1.0f, null);
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    @FXML
    private void selectFeed() {
        try {
            btnProfile.setText("Feed");
            Parent mainMenu = FXMLLoader.load(getClass().getResource("/view/ProfileNewsFXML.fxml"));
            stackPane.getChildren().remove(0, stackPane.getChildren().size());
            stackPane.getChildren().add(mainMenu);
            animation.applyFadeAnimationOn(stackPane, 500, 0f, 1.0f, null);
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    @FXML
    private void selectPhotos() {
        try {
            btnProfile.setText("Photos");
            Parent mainMenu = FXMLLoader.load(getClass().getResource("/view/ProfilePhotosFXML.fxml"));
            stackPane.getChildren().remove(0, stackPane.getChildren().size());
            stackPane.getChildren().add(mainMenu);
            animation.applyFadeAnimationOn(stackPane, 500, 0f, 1.0f, null);
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = MySQL.Connect();
        showName();
        showImage();
//        showSocial();
        selectGeneral();
    }    
    
}

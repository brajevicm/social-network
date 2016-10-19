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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Animations;
import view.ISUM;
import view.LAMS;
import view.Mail;
import service.MySQL;
import model.Projekat;
import model.SessionSingleton;
import model.User;
import view.Browser;

/**
 * FXML Controller class
 *
 * @author milos
 */
public class DockFXMLController implements Initializable {
    
    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
   
    User user = SessionSingleton.getInstance().getCurrentUser();
    
    private boolean isOpened = false;
    
    private static final int DEFAULT_STARTING_X_POSITION = 0;
    private static final int DEFAULT_ENDING_X_POSITION = -140;
    
    private final Animations animation;
    private LAMS lams;
    private ISUM isum;
    private Mail mail;
    private Browser browser;

    public DockFXMLController() {
        animation = new Animations();
    }
    
    @FXML
    private Label lblMenu5;
    @FXML
    private StackPane imagePaneTop;
    
    @FXML
    private StackPane imagePaneBottom;
    
    @FXML
    private GridPane Dock;
    
    @FXML
    private ImageView imageEditUsers;
    
    @FXML
    private ImageView imageInfo;
    
    @FXML
    private TextField tfSearch;
    
    @FXML
    private Label lblName;
    
    @FXML
    private Label lblTitle;
    
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private StackPane stackPane;
    
    @FXML
    private VBox menuRoot;
   
    @FXML
    protected ImageView imagePortrait, imageMenuButton;
    
    private Separator[] separators = new Separator[6];
    
    @FXML
    private Separator sep_1, sep_2, sep_3, sep_4, sep_5, sep_6;
    
    @FXML
    public void initSeparators(){
        separators[0] = sep_1;
        separators[1] = sep_2;
        separators[2] = sep_3;
        separators[3] = sep_4;
        separators[4] = sep_5;
        separators[5] = sep_6;
    }
    
    @FXML
    private void selectCRUD() {
        try {
            closeAnimation();
            lblTitle.setText("Users");
            Parent mainMenu = FXMLLoader.load(getClass().getResource("/view/DAOFXML.fxml"));
            stackPane.getChildren().remove(0, stackPane.getChildren().size());
            stackPane.getChildren().add(mainMenu);
            animation.applyFadeAnimationOn(stackPane, 500, 0f, 1.0f, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void selectProfile() {
        try {
            closeAnimation();
            lblTitle.setText("Profile");
            Parent mainMenu = FXMLLoader.load(getClass().getResource("/view/ProfileFXML.fxml"));
            stackPane.getChildren().remove(0, stackPane.getChildren().size());
            stackPane.getChildren().add(mainMenu);
            animation.applyFadeAnimationOn(stackPane, 500, 0f, 1.0f, null);
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    @FXML
    private void selectMenu1() {
        closeAnimation();
        lblTitle.setText("Overview");
            if(stackPane.getChildren().contains(browser)) {
                stackPane.getChildren().remove(browser);
                animation.applyFadeAnimationOn(stackPane, 500, 1.0f, 0f, null);
            }
            else{
                browser = new Browser();
                stackPane.getChildren().add(browser);
                animation.applyFadeAnimationOn(stackPane, 500, 0f, 1.0f, null);
            }
    }
    
//    @FXML
//    private void selectMenu1() {
//        try {
//            closeAnimation();
//            lblTitle.setText("Overview");
//            Parent mainMenu = FXMLLoader.load(getClass().getResource("/view/FeedFXML.fxml"));
//            stackPane.getChildren().remove(0, stackPane.getChildren().size());
//            stackPane.getChildren().add(mainMenu);
//            animation.applyFadeAnimationOn(stackPane, 500, 0f, 1.0f, null);
//        }catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//    }
    
    @FXML
    private void selectMenu2() {
        closeAnimation();
        lblTitle.setText("ISUM");
            if(stackPane.getChildren().contains(isum)) {
                stackPane.getChildren().remove(isum);
                animation.applyFadeAnimationOn(stackPane, 500, 1.0f, 0f, null);
            }
            else{
                isum = new ISUM();
                stackPane.getChildren().add(isum);
                animation.applyFadeAnimationOn(stackPane, 500, 0f, 1.0f, null);
            }
    }
    
    @FXML
    private void selectMenu3() {
        closeAnimation();
        lblTitle.setText("LAMS");
            if(stackPane.getChildren().contains(lams)) {
                stackPane.getChildren().remove(lams);
                animation.applyFadeAnimationOn(stackPane, 500, 1.0f, 0f, null);
            }
            else{
                lams = new LAMS();
                stackPane.getChildren().add(lams);
                animation.applyFadeAnimationOn(stackPane, 500, 0f, 1.0f, null);
            }
    }
    
    @FXML
    private void selectMenu4() {
        closeAnimation();
        lblTitle.setText("Mail");
            if(stackPane.getChildren().contains(mail)) {
                stackPane.getChildren().remove(mail);
                animation.applyFadeAnimationOn(stackPane, 500, 1.0f, 0f, null);
            }
            else{
                mail = new Mail();
                stackPane.getChildren().add(mail);
                animation.applyFadeAnimationOn(stackPane, 500, 0f, 1.0f, null);
            }
    }
    
    @FXML
    private void selectMenu5() {
        try {
            closeAnimation();
            lblTitle.setText("Chat");
            Parent mainMenu = FXMLLoader.load(getClass().getResource("/view/ChatFXML.fxml"));
            stackPane.getChildren().remove(0, stackPane.getChildren().size());
            stackPane.getChildren().add(mainMenu);
            animation.applyFadeAnimationOn(stackPane, 500, 0f, 1.0f, null);
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
    }
    
    @FXML
    private void selectLogout() throws IOException{
        Parent loginView = FXMLLoader.load(getClass().getResource("/view/LoginFXML.fxml"));
        animation.applyFadeAnimationOn(borderPane, 2000, 1.0, 0, event -> {
            model.Projekat.stage.setScene(new Scene(loginView, 650, 400));
            model.Projekat.stage.centerOnScreen();
            model.Projekat.stage.setMaximized(false);
        });
    }
    
    @FXML
    private void showDock(){
        animation.applyFadeAnimationOn(imageEditUsers, 300, 0.1, 1.0, null);
        animation.applyFadeAnimationOn(imageInfo, 300, 0.1, 1.0, null);
        animation.applyFadeAnimationOn(tfSearch, 300, 0.1, 1.0, null);
    }
    
    @FXML
    private void hideDock(){
        animation.applyFadeAnimationOn(imageEditUsers, 300, 1.0, 0.1, null);
        animation.applyFadeAnimationOn(imageInfo, 300, 1.0, 0.1, null);
        animation.applyFadeAnimationOn(tfSearch, 300, 1.0, 0.1, null);
    }
    
    @FXML
    private void rotateMenuIcon(){
        animation.applyRotationOn(imageMenuButton, 1000, 360, 1);
    }
    
    @FXML
    private void animateSeparators(double from, double to){
        for(Separator separator : separators)
            animation.applyFadeAnimationOn(separator, 500, from, to, null);
    }
    
    @FXML
    private void openAnimation(){
        animation.applyTranslateAnimationOn(menuRoot, 500, DEFAULT_ENDING_X_POSITION, DEFAULT_STARTING_X_POSITION);
        animation.applyTranslateAnimationOn(stackPane, 500, DEFAULT_ENDING_X_POSITION, DEFAULT_STARTING_X_POSITION);
        animation.applyFadeAnimationOn(imagePortrait, 500, 0, 1.0, null);
        animation.applyFadeAnimationOn(imagePaneTop, 500, 0, 1.0, null);
        animation.applyFadeAnimationOn(imagePaneBottom, 500, 0, 1.0, null);
        animateSeparators(0, 1.0);
        rotateMenuIcon();
        isOpened = true;
    }
    
    @FXML
    private void closeAnimation(){
        animation.applyTranslateAnimationOn(menuRoot, 500, DEFAULT_STARTING_X_POSITION, DEFAULT_ENDING_X_POSITION);
        animation.applyTranslateAnimationOn(stackPane, 500, DEFAULT_STARTING_X_POSITION, DEFAULT_ENDING_X_POSITION);
        animation.applyFadeAnimationOn(imagePortrait, 500, 1.0, 0, null);
        animation.applyFadeAnimationOn(imagePaneTop, 500, 1.0, 0, null);
        animation.applyFadeAnimationOn(imagePaneBottom, 500, 1.0, 0, null);
        animateSeparators(1.0, 0);  
        rotateMenuIcon();
        isOpened = false;
    }
    
    @FXML
    private void onMenuClick(){
        if(isOpened)
            closeAnimation();
        else
            openAnimation();
    }
    
    @FXML
    public void showName() {
        lblName.setText(user.getFirstName() + " " + user.getLastName());
    }
    
    @FXML
    private void showImage() {
        if(user.getImage() != null){
            Image image = new Image(user.getImage());
            imagePortrait.setImage(image);
        }
    }
    
    @FXML
    private void searchMouseOver() {
        tfSearch.setText("This feature is in progress");
    }
    
    @FXML
    private void isAdmin(){
        if(user.getUsername().matches(".*\\d+.*")){
           Dock.setDisable(true);
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectMenu1();
        connection = MySQL.Connect();
        initSeparators();
        closeAnimation();
        hideDock();
        showName();
        showImage();
        isAdmin();
    }    
    
}

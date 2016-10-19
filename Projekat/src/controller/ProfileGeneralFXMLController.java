/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import service.MySQL;
import model.SessionSingleton;
import model.User;

/**
 * FXML Controller class
 *
 * @author milos
 */
public class ProfileGeneralFXMLController implements Initializable {

    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    User user = SessionSingleton.getInstance().getCurrentUser();
    
    @FXML
    private ImageView imageGender;
    
    @FXML
    private ImageView imageSign;
    
    @FXML
    private Label lblBigIndex;
    
    @FXML
    private Label lblHeaderIndex;
    
    @FXML
    private Label lblBigClass;
    
    @FXML
    private Label lblHeaderClass;
    
    @FXML
    private Label lblSign;
    
    @FXML
    private Label lblGender;
    
    @FXML
    private Label lblFirstName;
    
    @FXML
    private Label lblLastName;
    
    @FXML
    private Label lblClass;
    
    @FXML
    private Label lblDateOfBirth;
    
    @FXML
    private Label lblTelephone;
    
    @FXML
    private Label lblMail;
    
    @FXML
    private Label lblPlaceOfLiving;
    
    @FXML
    private Label lblPlaceOfBirth;
    
    @FXML
    private Label lblCountry;
    
    @FXML
    private Label lblMemberSince;
    
    @FXML
    private void showFirstName() {
        lblFirstName.setText(user.getFirstName());
    }
    
    @FXML
    private void showLastName() {
        lblLastName.setText(user.getLastName());
    }
    
    @FXML
    private void showClass() {
        String className = user.getClassName();
        
        lblClass.setText(user.getClassName());
    }
    
    @FXML
    private void showDateOfBirth() {
        Date dob = (Date) user.getDateOfBirth();
        lblDateOfBirth.setText(dob.toString());
    }
    
    @FXML
    private void showReltionship() {
        int status = user.getRelationship();
        if(status == 0)
            lblTelephone.setText("Not in a relationship");
        else if(status == 1)
            lblTelephone.setText("In a relationship");
        else 
            lblTelephone.setText("Unknown");
    }
    
    private void showMail() {
        lblMail.setText(user.getMail());
    }
    
    private void showPlaceOfLiving() {
        lblPlaceOfLiving.setText(user.getCityCurrent());
    }
    
    private void showPlaceOfBirth() {
        lblPlaceOfBirth.setText(user.getCityBorn());
    }
    
    private void showCountry() {
        lblCountry.setText(user.getCountry());
    }
    
    private void showMemberSince() {        
        Date since = (Date) user.getMemberSince();
        lblMemberSince.setText(since.toString());
    }
    
    @FXML
    private void showHeaderClass() {
        lblHeaderClass.setText("class");
        if(user.getClassName().contains("Softversko"))
            lblBigClass.setText("SI");
        else if (user.getClassName().equalsIgnoreCase("informacione tehnologije"))
            lblBigClass.setText("IT");
        else if (user.getClassName().equalsIgnoreCase("razvoj igara"))
            lblBigClass.setText("RI");
        else
            lblBigClass.setText("?");
    }
    
    @FXML
    private void showHeaderIndex() {
        int indeksInt = user.getIndeks();
        String indeks = Integer.toString(indeksInt);
        lblBigIndex.setText(indeks);
        
        lblHeaderIndex.setText("index");
    }
    
    @FXML
    private void showSign() {
                Date day = (Date) user.getDateOfBirth();
                StringBuilder sb = new StringBuilder(day.toString());
                String string = sb.toString();
                String[] parts = string.split("-");
                String part1 = parts[0]; // 1996
                String part2 = parts[1]; // 05
                String part3 = parts[2]; // 07
                
                
                int M = Integer.parseInt(part2);
                int D = Integer.parseInt(part3);
                
                if ((M == 12 && D >= 22 && D <= 31) || (M ==  1 && D >= 1 && D <= 19)){
                    lblSign.setText("Capricorn");
                    imageSign.setImage(new Image("icons/capricorn.png"));
                }
                else if ((M ==  1 && D >= 20 && D <= 31) || (M ==  2 && D >= 1 && D <= 17)){
                    lblSign.setText("Aquarius");
                    imageSign.setImage(new Image("/icons/aquarius.png"));
                }
                else if ((M ==  2 && D >= 18 && D <= 29) || (M ==  3 && D >= 1 && D <= 19)){
                    lblSign.setText("Pisces");
                    imageSign.setImage(new Image("/icons/pisces.png"));
                }
                else if ((M ==  3 && D >= 20 && D <= 31) || (M ==  4 && D >= 1 && D <= 19)){
                    lblSign.setText("Aries");
                    imageSign.setImage(new Image("/icons/aries.png"));
                }
                else if ((M ==  4 && D >= 20 && D <= 30) || (M ==  5 && D >= 1 && D <= 20)){
                    lblSign.setText("Taurus");
                    imageSign.setImage(new Image("/icons/taurus.png"));
                }
                else if ((M ==  5 && D >= 21 && D <= 31) || (M ==  6 && D >= 1 && D <= 20)){
                    lblSign.setText("Gemini");
                    imageSign.setImage(new Image("/icons/gemini.png"));
                }
                else if ((M ==  6 && D >= 21 && D <= 30) || (M ==  7 && D >= 1 && D <= 22)){
                    lblSign.setText("Cancer");
                    imageSign.setImage(new Image("/icons/cancer.png"));
                }
                else if ((M ==  7 && D >= 23 && D <= 31) || (M ==  8 && D >= 1 && D <= 22)){
                    lblSign.setText("Leo");
                    imageSign.setImage(new Image("/icons/leo.png"));
                }
                else if ((M ==  8 && D >= 23 && D <= 31) || (M ==  9 && D >= 1 && D <= 22)){
                    lblSign.setText("Virgo");
                    imageSign.setImage(new Image("/icons/virgo.png"));
                }
                else if ((M ==  9 && D >= 23 && D <= 30) || (M == 10 && D >= 1 && D <= 22)){
                    lblSign.setText("Libra");
                    imageSign.setImage(new Image("/icons/libra.pngarari"));
                }
                else if ((M == 10 && D >= 23 && D <= 31) || (M == 11 && D >= 1 && D <= 21)){
                    lblSign.setText("Scorpio");
                    imageSign.setImage(new Image("/icons/scorpio.png"));
                }
                else if ((M == 11 && D >= 22 && D <= 30) || (M == 12 && D >= 1 && D <= 21)){
                    lblSign.setText("Sagittarius");
                    imageSign.setImage(new Image("/icons/sagittarius.png"));
                }
                else {
                    imageSign.setImage(new Image("/icons/round-help-button-white.png"));
                    lblSign.setText("Not set");
                }
    }
    
    @FXML
    private void showGender() {
        if(user.getGender().equalsIgnoreCase("male")){
            imageGender.setImage(new Image("/icons/male-symbol-white.png"));
            lblGender.setText("Male");
        }
        else if (user.getGender().equalsIgnoreCase("female")){
            imageGender.setImage(new Image("/icons/male-symbol-white.png"));
            lblGender.setText("Female");
        }
        else {
            imageGender.setImage(new Image("/icons/round-help-button-white.png"));
            lblGender.setText("Not set");
        }
            
    }
    
    @FXML
    private void showIcons(){
        showGender();
        showSign();
        showHeaderIndex();
        showHeaderClass();
    }
    
    @FXML
    private void showInfo(){
        showFirstName();
        showLastName();
        showClass();
        showMail();
        showReltionship();
        showPlaceOfLiving();
        showPlaceOfBirth();
        showCountry();
        showDateOfBirth();
        showMemberSince();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = MySQL.Connect();
        showInfo();
        showIcons();
    }    
    
}

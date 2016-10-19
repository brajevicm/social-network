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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Animations;
import service.MySQL;
import model.SessionSingleton;
import model.User;

/**
 *
 * @author milos
 */
public class LoginFXMLController implements Initializable {
    private final Animations animation;
    
    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public LoginFXMLController() {
        animation = new Animations();
    }

    @FXML
    private GridPane root;

    @FXML
    private VBox vboxLogin;

    @FXML
    private Label lblVersion;

    @FXML
    private TextField tfUsername;

    @FXML
    private TextField tfPassword;

    @FXML
    private void getHelp(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/BrowserHelpFXML.fxml"));
            Parent webView = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(webView, 800, 600));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void login() throws SQLException {
        if (loginSuccessful()) {
            animateWhenLoginSuccess();
        } else {
            animateWhenLoginFail();
        }
    }

    @FXML
    private void setOnKeyPressed() {
        vboxLogin.setOnKeyPressed(event -> {
            try {
                if (event.getCode().equals(KeyCode.ENTER) && loginSuccessful()) {
                    animateWhenLoginSuccess();
                } else if (event.getCode().equals(KeyCode.ENTER) && !loginSuccessful()) {
                    animateWhenLoginFail();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    @FXML
    private boolean loginSuccessful() throws SQLException {
        connection = MySQL.Connect();
        String sql = "SELECT * FROM users WHERE username = ? and PASSWORD = ?";

        pst = connection.prepareStatement(sql);
        pst.setString(1, tfUsername.getText());
        pst.setString(2, tfPassword.getText());
        rs = pst.executeQuery();
        boolean valid = rs.next();
        if (valid) {
            SessionSingleton.getInstance().setCurrentUser(
                    new User(rs.getString("username"), rs.getString("PASSWORD"),
                            rs.getString("first_name"), rs.getString("last_name"),
                            rs.getDate("date_of_birth"), rs.getDate("member_since"),
                            rs.getString("country"), rs.getString("city_born"),
                            rs.getString("city_current"), rs.getString("gender"),
                            rs.getString("class"), rs.getInt("indeks"),
                            rs.getString("image"), rs.getString("facebook"),
                            rs.getInt("relationship"), rs.getString("email"))
                    );
        }

        return valid;
    }

    @FXML
    private void animateWhenLoginFail() {
        try {
            StackPane temp = new StackPane();
            Parent root = FXMLLoader.load(getClass().getResource("/view/LoginFXML.fxml"));
            temp.getChildren().add(new ImageView(new Image("/icons/bad.png")));
            animation.applyFadeAnimationOn(root, 1000, 1.0, 0, event -> {
                temp.setOpacity(0);
                model.Projekat.stage.setScene(new Scene(temp, 650, 400));
                animation.applyFadeAnimationOn(temp, 1000, 0, 1.0, event1 -> {
                    root.setOpacity(0f);
                    model.Projekat.stage.setScene(new Scene(root, 650, 400));
                    animation.applyFadeAnimationOn(root, 1000, 0, 1.0, null);
                });
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void animateWhenLoginSuccess() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/DockFXML.fxml"));
            Parent main = fxmlLoader.load();
            StackPane temp = new StackPane();
            temp.getChildren().add(new ImageView(new Image("/icons/good.png")));
            animation.applyFadeAnimationOn(root, 1000, 1.0, 0, event -> {
                temp.setOpacity(0);
                model.Projekat.stage.setScene(new Scene(temp, 650, 400));
                animation.applyFadeAnimationOn(temp, 1000, 0, 1.0, event1 -> {
                    animation.applyFadeAnimationOn(temp, 1000, 1.0, 0, event2 -> {
                        model.Projekat.stage.setScene(new Scene(main, 800, 600));
//                        model.Projekat.stage.centerOnScreen();
                        model.Projekat.stage.setMaximized(true);
                        animation.applyFadeAnimationOn(main, 1000, 0, 1.0, null);
                    });
                });
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        animation.applyFadeAnimationOn(root, 2000, 0, 1.0, null);
        lblVersion.setText("0.8.5");
        connection = MySQL.Connect();
    }

}

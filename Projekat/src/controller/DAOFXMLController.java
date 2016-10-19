/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.User;
import service.MyService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author milos
 */
public class DAOFXMLController implements Initializable {

    @FXML
    private ChoiceBox<String> txtClass;
    
    @FXML
    private TextField txtIndeks;
    
    @FXML
    private TextField txtFirstName;
    
    @FXML
    private TextField txtLastName;
    
    @FXML
    private TextField txtMail;
    
    @FXML
    private TextField txtImage;
    
    @FXML
    private Label lblIndeks;
    
    @FXML
    private Label lblAlert1;
    
    @FXML
    private Label lblAlert2;
    
    @FXML
    private TableView<User> tableView;
    
    @FXML
    private TableColumn<User, String> columnIndeks;  
    
    @FXML
    private TableColumn<User, String> columnFirstName;
    
    @FXML
    private TableColumn<User, String> columnLastName;
    
    @FXML
    private TableColumn<User, String> columnMail;
    
    @FXML
    private TableColumn<User, String> columnClass;
    
    @FXML
    private TableColumn<User, String> columnImage;
    
    @FXML
    private Button btnSave;

    private MyService service;
    
    private String header1 = "Double click on a row to change (*)";
    private String header2 = "Select index to delete (*)";

    private void clearFields() {
        txtFirstName.clear();
        txtIndeks.clear();
        txtLastName.clear();
        txtMail.clear();
        txtImage.clear();
        txtClass.getSelectionModel().clearSelection();
    }
    
    private void initCombobox() {
        txtClass.getItems().add("Softversko Inzenjerstvo");
        txtClass.getItems().add("Racunarske Igre");
        txtClass.getItems().add("Informacione Tehnologije");
    }
        
    public void setButton() {
        if (txtClass != null && txtFirstName != null
                && txtImage != null && txtIndeks != null
                && txtLastName != null && txtMail != null)
            btnSave.setDisable(false);
    }
    
    public void loadData() {
        try {
            tableView.getItems().clear();
            tableView.getItems().addAll(service.showData());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void doSave() {
        try {
            User m = new User();
            m.setClassName(txtClass.getSelectionModel().getSelectedItem());
            m.setIndeks(Integer.parseInt(txtIndeks.getText()));
            m.setFirstName(txtFirstName.getText());
            m.setLastName(txtLastName.getText());
            m.setMail(txtMail.getText());
            m.setImage(txtImage.getText());
            clearFields();
            service.insertData(m);
            loadData();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void getEditInfo() {
        User m = tableView.getSelectionModel().getSelectedItem();
        txtClass.getSelectionModel().select(m.getClassName());
        txtIndeks.setText(Integer.toString(m.getIndeks()));
        txtFirstName.setText(m.getFirstName());
        txtLastName.setText(m.getLastName());
        txtMail.setText(m.getMail());
        txtImage.setText(m.getImage());
        
    }

    @FXML
    private void getAlert() {
        txtIndeks.focusedProperty().addListener(new ChangeListener<Boolean>() {
        @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    lblIndeks.setText("Warning!");
                    lblAlert1.setText("You can't change index later");
                    lblAlert2.setText("Input carefully");
                }
                else {
                    lblIndeks.setText("");
                    lblAlert1.setText(header1);
                    lblAlert2.setText(header2);
                }
            }
        });
    }
    
    @FXML
    private void doEdit() {
        try {
            User m = new User();
            m.setClassName(txtClass.getSelectionModel().getSelectedItem());
            m.setIndeks(Integer.parseInt(txtIndeks.getText()));
            m.setFirstName(txtFirstName.getText());
            m.setLastName(txtLastName.getText());
            m.setMail(txtMail.getText());
            m.setImage(txtImage.getText());
            clearFields();
            service.updateData(m);
            loadData();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public class TableColumnActionDelete extends TableCell<User, String> {

        private Hyperlink link;
        
        @Override
        protected void updateItem(String t, boolean isEmpty) {
            super.updateItem(t, isEmpty);
            if (!isEmpty) {
                link = new Hyperlink(t);
                link.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            service.deleteData(t);
                            loadData();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                setGraphic(link);
            } else {
                setGraphic(null);
            }
        }
    }
    
    @FXML
    private void doubleClick() {
        tableView.setRowFactory( tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    getEditInfo();
                }
            });
        return row ;
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnSave.setDisable(true);
        setButton();
        
        this.service = new MyService();

        loadData();
        initCombobox();
        doubleClick();
        getAlert();
        
        columnIndeks.setCellValueFactory(new PropertyValueFactory<User, String>("Indeks"));
//        columnIndeks.setCellFactory(new Callback<TableColumn<User, String>, TableCell<User, String>>() {
//            @Override
//            public TableCell<User, String> call(TableColumn<User, String> p) {
//                return new TableColumnActionDelete();
//            }
//        });
        columnFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("FirstName"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<User, String>("LastName"));
        columnMail.setCellValueFactory(new PropertyValueFactory<User, String>("Mail"));
        columnMail.setCellFactory(new Callback<TableColumn<User, String>, TableCell<User, String>>() {
            @Override
            public TableCell<User, String> call(TableColumn<User, String> p) {
                return new TableColumnActionDelete();
            }
        });
        columnClass.setCellValueFactory(new PropertyValueFactory<User, String>("ClassName"));
        columnImage.setCellValueFactory(new PropertyValueFactory<User, String>("Image"));
    }
}

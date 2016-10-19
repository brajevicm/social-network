/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathFactory;
import model.SessionSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author milos
 */
public class LAMS extends Region{
    final WebView webView = new WebView();
    final WebEngine webEngine = webView.getEngine();
    private boolean fieldsHaveBeenSet;
    final WebDriver driver = new WebDriver() {
        @Override
        public void get(String url) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getCurrentUrl() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getTitle() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<WebElement> findElements(By by) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public WebElement findElement(By by) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getPageSource() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void close() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void quit() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Set<String> getWindowHandles() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getWindowHandle() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public WebDriver.TargetLocator switchTo() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public WebDriver.Navigation navigate() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public WebDriver.Options manage() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    public LAMS() {
        webEngine.load("http://lams.metropolitan.ac.rs:8080/lams/");
        webView.getEngine().getLoadWorker().progressProperty().addListener(
            (o, old, progress) -> updateFields());
        getChildren().add(webView);
    }
    
    private void updateFields()
    {
        Document doc = webView.getEngine().getDocument();
        if (doc != null && !fieldsHaveBeenSet) {
            try {
                Element usernameField = (Element)
                    XPathFactory.newInstance().newXPath().evaluate(
                        "//*[@id=\"loginForm\"]/input[1]",
                        doc, XPathConstants.NODE);
                if (usernameField != null) {
                    usernameField.setAttribute("value", SessionSingleton.getInstance().getCurrentUser().getUsername());
                    fieldsHaveBeenSet = true;
                }
                
                Element passwordField = (Element)
                    XPathFactory.newInstance().newXPath().evaluate(
                        "//*[@id=\"loginForm\"]/input[2]",
                        doc, XPathConstants.NODE);
                if (passwordField != null) {
                    passwordField.setAttribute("value", SessionSingleton.getInstance().getCurrentUser().getPassword());
                    fieldsHaveBeenSet = true;
                    Robot r = new Robot();
//                    r.keyPress(KeyEvent.VK_ENTER);
//                    r.keyRelease(KeyEvent.VK_ENTER);
                }
            } catch (XPathException e) {
                e.printStackTrace();
            } catch (AWTException ex) {
                Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(webView, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
    }
}

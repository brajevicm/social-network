/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author milos
 */
public class Browser extends Region{
    
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    
    public Browser(){
        webEngine.load("http://www.metropolitan.edu.rs/");
        getChildren().add(browser);
        browser.setPrefSize(600, 400);
    }
    
    @Override
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
    }
}

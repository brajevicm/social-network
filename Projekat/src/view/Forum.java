package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author milos
 */
public class Forum extends Region{
    final WebView forum = new WebView();
    final WebEngine webEngine = forum.getEngine();

    public Forum() {
        webEngine.load("https://forum.metropolitan.ac.rs/index.php");
        getChildren().add(forum);
    }
    @Override
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(forum, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
    }
}
package com.example.lab7;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class HelloApplication extends Application {
    List<String> liste;
    List<String> listeMois;
    List<String> listeTempMois;
    @Override
    public void start(Stage stage) throws IOException {
        stage.setHeight(700);
        stage.setWidth(700);
        stage.setTitle("Graphiques");
        stage.setFullScreen(true);
        Scene scenePrincipale = new Scene(new Group());
        BorderPane borderPane = new BorderPane();

        //Creation de l'alerte choix de fichier
        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.setTitle("Information importante");
        alerte.setHeaderText("Important!");
        alerte.setContentText("Vous devez choisir un fichie de format .dat");

        //Creation de l'alerte enregistrement
        Alert alerteEnregistrement = new Alert(Alert.AlertType.INFORMATION);
        alerteEnregistrement.setTitle("Information importante");
        alerteEnregistrement.setHeaderText("Désolé");
        alerteEnregistrement.setContentText("En cours de développement!");

        //Creation du menu
        MenuItem lignes = new MenuItem("Lignes");
        MenuItem régions = new MenuItem("Régions");
        MenuItem barres = new MenuItem("Barres");
        MenuItem jpg = new MenuItem("Jpg");
        MenuItem png = new MenuItem("Png");
        Menu exporter = new Menu("Exporter");
        Menu importer = new Menu("Importer");
        importer.getItems().addAll(lignes,régions,barres);
        exporter.getItems().addAll(jpg,png);
        MenuBar menuBar = new MenuBar(importer,exporter);

        //setOnAction de lignes
        lignes.setOnAction(actionEvent -> {
            alerte.showAndWait();
            setListe(stage);
            if (liste != null) {
                listeMois = List.of(liste.get(0).split(","));
                listeTempMois = List.of(liste.get(1).split(","));
                CategoryAxis xAxis = new CategoryAxis();
                NumberAxis yAxis = new NumberAxis();
                LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
                lineChart.setTitle("Températures moyennes");
                xAxis.setLabel("Mois");
                yAxis.setLabel("Température");
                XYChart.Series series = new XYChart.Series();
                series.setName("2004");
                for (int i = 0; i < listeMois.size(); i++) {
                    series.getData().add(new XYChart.Data(listeMois.get(i), Integer.parseInt(listeTempMois.get(i))));
                }
                lineChart.getData().addAll(series);
                borderPane.setCenter(lineChart);
            }

        });
        //setOnAction de régions
        régions.setOnAction(actionEvent -> {
            alerte.showAndWait();
            setListe(stage);
            if (liste != null) {
                listeMois = List.of(liste.get(0).split(","));
                listeTempMois = List.of(liste.get(1).split(","));
                CategoryAxis xAxis = new CategoryAxis();
                NumberAxis yAxis = new NumberAxis();
                AreaChart<String, Number> areaChart = new AreaChart<String, Number>(xAxis, yAxis);
                areaChart.setTitle("Températures moyennes");
                xAxis.setLabel("Mois");
                yAxis.setLabel("Température");
                XYChart.Series series = new XYChart.Series();
                series.setName("2004");
                for (int i = 0; i < listeMois.size(); i++) {
                    series.getData().add(new XYChart.Data(listeMois.get(i), Integer.parseInt(listeTempMois.get(i))));
                }
                areaChart.getData().addAll(series);
                borderPane.setCenter(areaChart);
            }
        });

        //setOnAction de barres
        barres.setOnAction(actionEvent -> {
            alerte.showAndWait();
            setListe(stage);
            if (liste != null) {
                listeMois = List.of(liste.get(0).split(","));
                listeTempMois = List.of(liste.get(1).split(","));
                CategoryAxis xAxis = new CategoryAxis();
                NumberAxis yAxis = new NumberAxis();
                BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
                barChart.setTitle("Températures moyennes");
                xAxis.setLabel("Mois");
                yAxis.setLabel("Température");
                XYChart.Series series = new XYChart.Series();
                series.setName("2004");
                for (int i = 0; i < listeMois.size(); i++) {
                    series.getData().add(new XYChart.Data(listeMois.get(i), Integer.parseInt(listeTempMois.get(i))));
                }
                barChart.getData().addAll(series);
                borderPane.setCenter(barChart);
            }
        });

        //Creation de l'interface
        borderPane.setTop(menuBar);
        scenePrincipale.setRoot(borderPane);
        stage.setScene(scenePrincipale);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    public void setListe(Stage stage){
        FileChooser fc = new FileChooser();
        fc.setTitle("Veuillez sélectionner un fichier");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier dat","*.dat"));
        File fichier = fc.showOpenDialog(stage);
        if(fichier != null) {
            try {
                liste = Files.readAllLines(Paths.get(String.valueOf(fichier)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
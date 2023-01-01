package com.example.semester_project;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.StringCompression;

import java.io.*;

public class MainController {
    private Stage stage;
    @FXML
    private Label filePath;
    @FXML
    private TextArea textArea;

    private StringCompression sc = new StringCompression();

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    protected void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(stage);
        filePath.setText(file.getAbsolutePath());
        System.out.println(file);
        try {
            FileReader reader = new FileReader(file.getAbsoluteFile());
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            String text = "";
            while (line != null) {
                text += line + "\n";
                line = br.readLine();
            }
            textArea.setText(text);
            sc.compress(text);
            br.close();
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setContentText("An error occured during file reading");
            a.show();
        }
    }

    @FXML
    protected void compressAndSaveFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("ZIPP files (*.zipp)", "*.zipp");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(stage);
        System.out.println(file);

        try {
            sc.compressToFile(file.getAbsolutePath(), textArea.getText());
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setContentText("An error occured during file writing");
            a.show();
        }
    }

    @FXML
    protected void openZippFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("ZIPP files (*.zipp)", "*.zipp");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(stage);
        filePath.setText(file.getAbsolutePath());
        System.out.println(file);
        try {
            FileReader reader = new FileReader(file.getAbsoluteFile());
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            String text = "";
            while (line != null) {
                text += line + "\n";
                line = br.readLine();
            }
            textArea.setText(text);
            sc.decompressFile(text);
            br.close();
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setContentText("An error occured during file reading");
            a.show();
        }
    }
}
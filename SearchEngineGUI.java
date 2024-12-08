package GUIs;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import Jwiki.Jwiki;
import java.nio.file.Paths;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SearchEngineGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        BorderPane pane = new BorderPane();
        
        // NODES
        // Title
        Label title = new Label("Search For a Image");
        title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 25));
        
        // Search Bar
        TextField searchTF = new TextField();
        
        
        // Button 
        Button searchButton = new Button("SEARCH");
        searchButton.setPrefWidth(75);
        
        // More information Button
        Button infoButton = new Button("More Info");
        infoButton.setOnAction((e) -> {
            getInfo(title.getText(), stage);
        });
        
        // Functionality of the button
        searchButton.setOnAction((e) -> {
            System.out.println("Searching for " + searchTF.getText());
            setTitle(searchTF, title);
            pane.setCenter(getImage(searchTF.getText(), infoButton));
            searchTF.clear();
            searchTF.requestFocus();
        });
        
        // VBox to go on the top of our pane
        VBox topBox = new VBox(10);
        topBox.getChildren().addAll(title, searchTF, searchButton, infoButton);
        topBox.setAlignment(Pos.CENTER);
        
        // Add items to the border pane
        pane.setTop(topBox);
        
        // Create scene & stage
        Scene scene = new Scene(pane, 500, 500);
        stage.setTitle("First Search Engine");
        stage.setScene(scene);
        stage.setResizable(false);
        
        // Show Stage
        stage.show();
    }
    
    public static void setTitle(TextField tf, Label title) {
        tf.setText(tf.getText().trim());
        if(tf.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setContentText("Search Bar cannot be empty");
            alert.show();
        } else {
            title.setText(tf.getText());
        }
    }
    
    public static ImageView getImage(String search, Button infoButton) {
        String imgURL = "";
        Color[] colors = {Color.TOMATO, Color.BLUE, Color.AQUA, Color.LAVENDER, Color.CHOCOLATE};
        int random = (int) (Math.random() * colors.length);
        DropShadow ds = new DropShadow(500, colors[random]);
    
        try{
            Jwiki wiki = new Jwiki(search);
            imgURL = wiki.getImageURL();
            infoButton.setVisible(true);
            
            if(search.equalsIgnoreCase("cat")) {
                Media m = new Media(Paths.get("cat-meow-6226").toUri().toString());
                new MediaPlayer(m).play();
            }
        } catch(Exception e) {
            infoButton.setVisible(false);
            System.out.println("Cannot find an image of " + search + ". So heres a kitten"); 
        }
        
        Image image = new Image(imgURL, 250, 250, false, false);
        ImageView imageView = new ImageView(image);
        imageView.setEffect(ds);
        return imageView;
}
    public static void getInfo(String search, Stage stage) {
        Stage infoStage = new Stage();
        infoStage.setTitle(search + " information");
        
        TextArea text = new TextArea();
        text.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 16));
        text.setWrapText(true);
        Jwiki wiki = new Jwiki(search);
        text.appendText(wiki.getExtractText());
        
        Scene infoScene = new Scene(text, 550, 200);
        infoStage.setScene(infoScene);
        
        infoStage.setX(stage.getX());
        infoStage.setY(stage.getY() + 550);
        infoStage.show();
    }
}

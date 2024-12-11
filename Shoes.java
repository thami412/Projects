package GUIs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Shoes extends Application {

    private final List<Shoe> shoes = new ArrayList<>();
    private int currentIndex = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        BorderPane pane = new BorderPane();
        
        // Change the color of the pane
        pane.setStyle("-fx-background-color: FFFDD0;");

        // Title
        Label title = new Label("Shoe Release GUI");
        title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 25));
        pane.setTop(title);
        
        // Center the title in a StackPane
        StackPane titlePane = new StackPane();
        titlePane.getChildren().add(title);
        titlePane.setAlignment(title, javafx.geometry.Pos.CENTER);
        pane.setTop(titlePane);

        // Shoe image and description
        ImageView shoeImageView = new ImageView();
        Label shoeDescription = new Label();
        shoeDescription.setFont(Font.font("Arial", 16));
        pane.setCenter(shoeImageView);
        pane.setBottom(shoeDescription);
        
        // Enable text wrapping for the description
        shoeDescription.setWrapText(true);
        shoeDescription.setMaxWidth(1000);
        shoeDescription.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        shoeDescription.setTextFill(Color.DARKRED);

        // Load the shoe data
        loadShoes();

        // Show the first shoe
        updateShoe(shoeImageView, shoeDescription);

        // Next Button & Lambda
        Button nextButton = new Button("NEXT");
        nextButton.setPrefSize(75, 75);
        nextButton.setOnAction((e) -> {
            currentIndex = (currentIndex + 1) % shoes.size();
            updateShoe(shoeImageView, shoeDescription);
        });
        
        // Apply drop shadow effect
        DropShadow ds = new DropShadow(15, Color.DARKRED);
        nextButton.setEffect(ds);
        title.setEffect(ds);
        
        pane.setRight(nextButton);
        title.setStyle("-fx-text-fill: blue;"); // Title text color
        nextButton.setStyle("-fx-background-color: skyblue;"); // Button background color
        
        // Previous Button & Lambda
        Button backButton = new Button("BACK");
        backButton.setPrefSize(75, 75);
        backButton.setOnAction((e) -> {
            // Decrease the index and wrap around if necessary (if at the beginning of the list)
            currentIndex = (currentIndex - 1 + shoes.size()) % shoes.size();
            updateShoe(shoeImageView, shoeDescription);
        });
        pane.setLeft(backButton);
        backButton.setStyle("-fx-background-color: skyblue;");
        
        // Apply drop shadow effect
        backButton.setEffect(ds);

        // Create scene & stage
        Scene scene = new Scene(pane, 600, 500);
        stage.setTitle("Shoe Release GUI");
        stage.setScene(scene);
        stage.setResizable(false);

        // Show Stage
        stage.show();
    }

    private void loadShoes() {
        try {
            Document doc = Jsoup.connect("https://www.nicekicks.com/sneaker-release-dates/").get();

            Elements divs = doc.select("div[class=release-date__wrapper]");
            
            String releaseInfo, pictureURL;
            for (Element div : divs) {
                releaseInfo = div.getElementsByClass("block-release-info").text();
                pictureURL = div.getElementsByTag("img").attr("src");

                // Create Shoe object and add it to the list
                shoes.add(new Shoe(releaseInfo, pictureURL));
            }
        } catch (IOException e) {
        }
    }

    private void updateShoe(ImageView shoeImageView, Label shoeDescription) {
        if (shoes.isEmpty()) {
            shoeDescription.setText("No shoes available.");
            return;
        }

        Shoe shoe = shoes.get(currentIndex);
        shoeDescription.setText(shoe.getDescription());

        // Load the image
        Image image = new Image(shoe.getImageURL(), 250, 250, false, false);
        shoeImageView.setImage(image);

        // Apply drop shadow effect
        DropShadow ds = new DropShadow(15, Color.DARKRED);
        shoeImageView.setEffect(ds);
    }

   
}

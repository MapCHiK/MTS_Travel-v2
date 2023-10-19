import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Yandex Maps Location");

        // Создаем элементы интерфейса
        Label label = new Label("Введите название объекта:");
        TextField textField = new TextField();
        Button openButton = new Button("Открыть на Яндекс.Картах");
        Button nearbyButton = new Button("Достопримечательности и отели рядом");

        // Обработчик события нажатия на кнопку "Открыть на Яндекс.Картах"
        openButton.setOnAction(event -> {
            String location = textField.getText();
            String locationNoIllegalSybols = location.replaceAll(" ", "%20");
            String query = "https://maps.yandex.ru/?text=" + locationNoIllegalSybols;

            try {
                Desktop.getDesktop().browse(new URI(query));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });

        // Обработчик события нажатия на кнопку "Достопримечательности и отели рядом"
        nearbyButton.setOnAction(event -> {
            String location = textField.getText();
            String locationNoIllegalSybols = location.replaceAll(" ", "%20");
            String nearby = "https://maps.yandex.ru/?text=достопримечательности%20и%20отели%20рядом%20с%20" + locationNoIllegalSybols;

            try {
                Desktop.getDesktop().browse(new URI(nearby));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });

        // Создаем контейнеры для элементов интерфейса
        HBox hbox = new HBox(label, textField);
        hbox.setSpacing(10);

        VBox vbox = new VBox(hbox, openButton, nearbyButton);
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(20));

        // Создаем сцену и устанавливаем контейнер на сцену
        Scene scene = new Scene(vbox, 400, 200);

        // Устанавливаем сцену на окно
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
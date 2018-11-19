import com.sun.deploy.uitoolkit.impl.fx.ui.FXAboutDialog;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Pogoda extends Application {

    public static void main(String args[]) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        VBox vBox = new VBox();
        HBox hBox = new HBox();
        //  Parent pane = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(new Group());

//        Button update = new Button("Обновить прогноз");
//        Label status = new Label();

        TableView<Weather> table = new TableView<Weather>();
        TableColumn colDate = new TableColumn("Дата");
        TableColumn colPartOfDay = new TableColumn("Часть дня");
        TableColumn colPropertyWeather = new TableColumn("Погодные явления");
        TableColumn colTemp = new TableColumn("Температура");
        TableColumn colPressure = new TableColumn("Давление");
        TableColumn colMoisture = new TableColumn("Влажность");
        TableColumn colWind = new TableColumn("Ветер");
        colDate.setCellValueFactory(new PropertyValueFactory<Weather, String>("date"));
        colPartOfDay.setCellValueFactory(new PropertyValueFactory<Weather, String>("partOfDay"));
        colPropertyWeather.setCellValueFactory(new PropertyValueFactory<Weather, String>("propertyWeather"));
        colTemp.setCellValueFactory(new PropertyValueFactory<Weather, String>("temp"));
        colPressure.setCellValueFactory(new PropertyValueFactory<Weather, String>("pressure"));
        colMoisture.setCellValueFactory(new PropertyValueFactory<Weather, String>("moisture"));
        colWind.setCellValueFactory(new PropertyValueFactory<Weather, String>("wind"));


        Controllers controllers = new Controllers();
        Updater updater = new Updater("http://pogoda.spb.ru/",controllers);
        controllers.setUpdater(updater);

        table.getColumns().addAll(colDate, colPartOfDay, colPropertyWeather, colTemp,
                colPressure, colMoisture, colWind);
        table.setPlaceholder(new Label("Нет данных"));
        table.setItems(updater.getObservableList());
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(10, 0, 0, 5));
        hBox.getChildren().addAll(controllers.getControllers());
        hBox.setSpacing(10);
        vBox.getChildren().addAll(hBox, table);
        scene.setRoot(vBox);
        primaryStage.setWidth(900);
        primaryStage.setHeight(700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Погода с сайта " + updater.getURL());
        primaryStage.show();

    }
}
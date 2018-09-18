package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane mainPane = new BorderPane();
            Group root = new Group();

            TabPane tp = new TabPane();

            GuiApplication tb1 = new GuiApplication();
            tb1.start();
            tp.getTabs().add(tb1);

            TvClass tb2 = new TvClass();
            tb2.start();
            tp.getTabs().add(tb2);
            TestClass t = new TestClass();
            t.start();
            mainPane.setCenter(tp);

            Scene scene = new Scene(root, 400, 500);
            mainPane.prefHeightProperty().bind(scene.heightProperty());
            mainPane.prefWidthProperty().bind(scene.widthProperty());
            root.getChildren().add(mainPane);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Ed Electronics Shop");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene root = new Scene(FXMLLoader.load(getClass().getResource("loginwindow/Scene.fxml")));
        primaryStage.setScene(root);
        primaryStage.setTitle("Analyses of stocks");
        primaryStage.getMaxHeight();
        primaryStage.getMaxWidth();
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
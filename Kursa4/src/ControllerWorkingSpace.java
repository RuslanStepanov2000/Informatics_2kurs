import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class ControllerWorkingSpace {

    @FXML
    private LineChart<?, ?> Graph1;

    @FXML
    private LineChart<?,?> Graph2;

    @FXML
    private Label labelInstrmentName;

    @FXML
    private Button OpenFile;

    @FXML
    private Button graph1Settings;

    @FXML
    private Button graph2Settings;

    @FXML
    private Button SaveButton;

    @FXML
    private Pane paneSettingsGraph1;

    @FXML
    private Button graph1AVG1;

    @FXML
    private Button graph1Open;

    @FXML
    private Button graph1Close;

    @FXML
    private Button graph1High;

    @FXML
    private Button graph1AVG;

    @FXML
    private SplitMenuButton SplitMenuTimeGraph21;

    @FXML
    private Pane paneSettingsGraph11;

    @FXML
    private Button graph2Open;

    @FXML
    private Button graph2High;

    @FXML
    private Button graph2AVG;

    @FXML
    private Button graph1AVG11;

    @FXML
    private SplitMenuButton SplitMenuTimeGraph2;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonChangeUser;

    @FXML
    void OpenFile(MouseEvent event) throws IOException {
        /**
         * Метод отвечает за открытие диалогового окна при выборе загружаемого с компьютера файла
         *
         */
        FileChooser fc = new FileChooser();
        final Label fileLabel = new Label();
        fc.setTitle("My File Chooser");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("(*.txt) (*.csv)","*.csv", "*.txt");
        fc.getExtensionFilters().add(extFilter);
        File file = fc.showOpenDialog(this.OpenFile.getScene().getWindow());
        if (file != null) {
            fileLabel.setText(file.getPath());
        }
        DataManager.setFile(file);
        DataManager.readInput();

        labelInstrmentName.setText(DataManager.getInstrument());
    }

    @FXML
    void graph1avg(MouseEvent event) {

    }

    @FXML
    void graph1close(MouseEvent event) {

    }

    @FXML
    void graph1high(MouseEvent event) {

    }

    @FXML
    void graph1open(MouseEvent event) {

    }

    @FXML
    void graph1volume(MouseEvent event) {

    }

}

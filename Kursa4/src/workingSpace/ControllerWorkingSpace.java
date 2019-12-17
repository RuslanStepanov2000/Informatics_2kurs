import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ControllerWorkingSpace {

    @FXML
    private LineChart<String, Number> graph1;

    @FXML
    private Label labelInstrumentName;

    @FXML
    private Button OpenFile;

    @FXML
    private Button SaveButton;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonChangeUser;

    @FXML
    private ColorPicker colorPicker;

    public ControllerWorkingSpace() throws IOException {
    }

    @FXML
    void DeleteGraph1(MouseEvent event) {
        graph1.getData().clear();

    }

    @FXML
    private ComboBox<String> comboType;
    private ObservableList<String> type = FXCollections.observableArrayList(
            "open",
            "close",
            "high",
            "low"
    );
    @FXML
    private ComboBox<String> comboName;
    private ObservableList<String> name=FXCollections.observableList(DataManager.comboNameArrList());

    @FXML
    private ComboBox<String> comboBuildType;
    private ObservableList<String> buildType = FXCollections.observableArrayList(
            "linear",
            "square",
            "exp",
            "hyper"
    );

    @FXML
    private SplitMenuButton graphType;

    @FXML
    void initialize() throws IOException {
        comboType.setItems(type);
        comboBuildType.setItems(buildType);
        graph1.setAnimated(false);
        comboName.setItems(name);
        List list=DataManager.comboNameArrList();

    }

    @FXML
    void OpenFile(MouseEvent event) throws IOException {
        /**
         * Метод отвечает за открытие диалогового окна при выборе загружаемого с компьютера файла
         *
         */
        FileChooser fc = new FileChooser();
        fc.setTitle("My File Chooser");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("(*.txt) (*.csv)","*.csv", "*.txt");
        fc.getExtensionFilters().add(extFilter);
        File file = fc.showOpenDialog(this.OpenFile.getScene().getWindow());
        DataManager.setFile(file);
        if (file != null) {
            labelInstrumentName.setText(DataManager.getInstrument());
        }

    }

    @FXML
    void graphDraw(MouseEvent event) throws IOException, ParseException {
        String color = colorPicker.getValue().toString();

        String method =" ";
        double[] tmp = null;
        switch (comboType.getValue()) {
            case "open":
                tmp = DataManager.getdata(1);
                method="open";
                break;
            case "high":
                tmp = DataManager.getdata(2);
                method="high";
                break;
            case "low":
                tmp = DataManager.getdata(3);
                method="low";
                break;
            case "close":
                tmp = DataManager.getdata(4);
                method="close";
                break;
            case "volume":
                tmp = DataManager.getdata(5);
                method="volume";
                break;
        }

        LineChart chart=graph1;
        chart.setTitle("Series");
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();

        //Подпись к графику
        series1.setName(DataManager.getInstrument()+"  "+method);

        //array подгружает даты
        String array1[]=DataManager.getdate();
        //Подгружаем данные
        double array2[]=DataManager.getdata(3);


        for (int i = 0; i < array1.length; i++) {
            series1.getData().add(new XYChart.Data(new SimpleDateFormat("yyyymmdd").parse(array1[i]).toString(),array2[i]));

        }

        graph1.getData().add(series1);
        paint(color,series1,false);

    }

    @FXML
    void downloadFile(MouseEvent event){

    }


    public static void paint(String color, XYChart.Series<String, Number> series, boolean isMnk) {
        Node line = series.getNode().lookup(".chart-series-line");
        if (isMnk) {
            for (int index = 0; index < series.getData().size(); index++) {
                XYChart.Data dataPoint = series.getData().get(index);
                Node lineSymbol = dataPoint.getNode().lookup(".chart-line-symbol");
                lineSymbol.setStyle("-fx-background-color:rgba(" + color + ", 1.0); /*-fx-background-radius: 0;*/ -fx-padding: 0px ;");
            }
            line.setStyle("-fx-stroke: rgba(" + color + ", 1.0); -fx-stroke-width: 2px;");
        } else {
            line.setStyle("-fx-stroke: rgba(" + color + ", 1.0); -fx-stroke: transparent;");
            //красим каждую точку в серии
            for (int index = 0; index < series.getData().size(); index++) {
                XYChart.Data dataPoint = series.getData().get(index);
                Node lineSymbol = dataPoint.getNode().lookup(".chart-line-symbol");
                lineSymbol.setStyle("-fx-background-color:rgba(" + color + ", 1.0); /*-fx-background-radius: 0;*/ -fx-padding: 2px ;");
                //lineSymbol.setStyle("-fx-background-color: rgba(" + rgb + ", 1.0);");
            }

        }
    }

}

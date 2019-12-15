import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;


import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ControllerWorkingSpace {

    @FXML
    private LineChart<?, ?> graph1;

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

    @FXML
    private ComboBox<String> comboType;
    private ObservableList<String> type = FXCollections.observableArrayList(
            "open",
            "close",
            "high",
            "low"
    );

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
    void initialize() {
        comboType.setItems(type);
        comboBuildType.setItems(buildType);
        graph1.setAnimated(false);
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
    void graphDraw() throws IOException {
        String color = colorPicker.getValue().toString();
        System.out.println(color + " YA COLOR");
        double[] tmp = null;
        switch (comboType.getValue()) {
            case "open":
                tmp = DataManager.getdata(1);
                System.out.println(tmp);
                break;
            case "high":
                tmp = DataManager.getdata(2);
                break;
            case "low":
                tmp = DataManager.getdata(3);
                break;
            case "close":
                tmp = DataManager.getdata(4);
                break;
            case "volume":
                tmp = DataManager.getdata(5);
                break;
        }
        LineChart chart = graph1;
        /*// buildGraph(chart, tmp, date, color);
        XYChart.Series<String, Number> approx = buildUniversal(tmp);
        chart.getData().add(approx);
        paint(DataManager.colorToString(Color.valueOf(color.toString())), approx, true);*/
    }

/*    public XYChart.Series<String, Number> buildUniversal(double[] y){
        int inst = 0;
        XYChart.Series<String, Number> series_mnk = new XYChart.Series<>();
        double[] method = null;
        switch (comboInstrument.getValue()){
            case "linear":
                method = dataValues.mnk(y);
                series_mnk.getData().add(new XYChart.Data<>(date[0].toString(), (-1) * method[0] + method[1]));
                series_mnk.getData().add(new XYChart.Data<>(date[date.length-1].toString(), (y.length) * method[0] + method[1]));
                inst = 1;
                break;
            case "square":
                method = dataValues.sqrtStat(y);
                for (int i = 0; i < y.length; i++) {
                    series_mnk.getData().add(new XYChart.Data<>(date[i].toString(), (i*i) * method[0] +i * method[1] + method[2]));
                }
                inst = 2;
                break;
            case "exp":
                method = dataValues.expStat(y);
                double a = Math.exp(method[0]);
                double b = method[1];
                for (int i = 0; i < y.length; i++) {
                    series_mnk.getData().add(new XYChart.Data<>(date[i].toString(), a * Math.exp(b * i)));
                }
                inst = 3;
                break;
            case "hyper":
                method = dataValues.hyperStat(y);
                for (int i = 1; i < y.length; i++) {
                    series_mnk.getData().add(new XYChart.Data<>(date[i].toString(), method[0] + method[1]/i));
                }
                inst = 4;
                break;
        }
        printAns(method);
        return series_mnk;
    }*/
    public void buildGraph(LineChart chart, double[] data, Date[] date, String color){
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        //доавление точек
        for (int j = 0; j < data.length; j++) {
            series1.getData().add(new XYChart.Data<>(date[j].toString(), data[j]));
        }
        chart.getData().add(series1);
        paint(DataManager.colorToString(Color.valueOf(color.toString())), series1, false);
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

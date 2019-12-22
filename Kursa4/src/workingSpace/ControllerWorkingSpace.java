import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ControllerWorkingSpace {
    private ObservableList<DataClass> daysData = FXCollections.observableArrayList();
    private boolean isThreadsStarted=false;

    @FXML
    private LineChart<String, Number> graph1;

    @FXML
    private TableView<DataClass> tableView = new TableView<>();
    @FXML
    private TableColumn<DataClass, String> dateColumn;
    @FXML
    private TableColumn<DataClass, Double> openColumn;
    @FXML
    private TableColumn<DataClass, Double> closeColumn;
    @FXML
    private TableColumn<DataClass, Double> highColumn;
    @FXML
    private TableColumn<DataClass, Double> lowColumn;
    @FXML
    private TableColumn<DataClass, Double> volumeColumn;


    @FXML
    private DatePicker datePIckerFromInstrument;

    @FXML
    private DatePicker getDatePIckerToInstrument;

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
    NumberAxis yAxis;
    @FXML
    CategoryAxis xAxis;

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
    private ObservableList<String> name = FXCollections.observableList(DataManager.comboNameArrList());

    @FXML
    private ComboBox<String> comboBuildType;
    private ObservableList<String> buildType = FXCollections.observableArrayList(
            "linear",
            "square",
            "exp",
            "simple"
    );

    @FXML
    private SplitMenuButton graphType;

    @FXML
    void switchTable(MouseEvent event) {
        if (tableView.isVisible()) {
            tableView.setVisible(false);
        } else tableView.setVisible(true);
    }

    @FXML
    void initialize() throws IOException {
        comboType.setItems(type);
        comboBuildType.setItems(buildType);
        graph1.setAnimated(false);
        comboName.setItems(name);
        List list = DataManager.comboNameArrList();


    }

    @FXML
    void OpenFile(MouseEvent event) throws IOException, InterruptedException {
        /**
         * Метод отвечает за открытие диалогового окна при выборе загружаемого с компьютера файла
         *
         */
        isThreadsStarted=false;
        FileChooser fc = new FileChooser();
        fc.setTitle("My File Chooser");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("(*.txt) (*.csv)", "*.csv", "*.txt");
        fc.getExtensionFilters().add(extFilter);
        File file = fc.showOpenDialog(this.OpenFile.getScene().getWindow());
        DataManager.setFile(file);
        if (file != null) {
            labelInstrumentName.setText(DataManager.getInstrument());
        }

    }


    @FXML
    void graphDraw(MouseEvent event) throws IOException, ParseException, InterruptedException {
        String method = " ";
        double[] tmp = null;

        yAxis.setPrefWidth(0.5);
        xAxis.setPrefWidth(0.5);

        switch (comboType.getValue()) {
            case "open":
                tmp = DataManager.getdata(1);
                method = "open";
                break;
            case "high":
                tmp = DataManager.getdata(2);
                method = "high";
                break;
            case "low":
                tmp = DataManager.getdata(3);
                method = "low";
                break;
            case "close":
                tmp = DataManager.getdata(4);
                method = "close";
                break;
            case "volume":
                tmp = DataManager.getdata(5);
                method = "volume";
                break;
        }

        double x[]=new double[tmp.length];
        for (int i=0;i<tmp.length;i++){
            x[i]=i+1;
        }

        //После получения файла запускаем потоки, которые генерируют массивы данных для графика
            double[] finalTmp = tmp;
            Thread firstThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    MathMethods.mnk(x, finalTmp);
                }
            });
            firstThread.start();
            System.out.println("Поток1");
            Thread secondThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    MathMethods.square(x, finalTmp);
                }
            });
            secondThread.start();
            System.out.println("Поток2");
            Thread thirdThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    MathMethods.exp(x, finalTmp);
                }
            });
            thirdThread.start();
            System.out.println("Поток3");
            firstThread.join();
            secondThread.join();
            thirdThread.join();



        LineChart chart = graph1;
        chart.setTitle("Series");
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();

        //Подпись к графику


        //array подгружает даты
        String array1[] = DataManager.getdate();
        //Подгружаем данные
        double array2[] = tmp;


        switch (comboBuildType.getValue()){
            case  "linear":{
                    series1.getData().add(new XYChart.Data<>(new SimpleDateFormat("yyyymmdd").parse(array1[0]).toString(), MathMethods.linearFun(-1)));
                    series1.getData().add(new XYChart.Data<>(new SimpleDateFormat("yyyymmdd").parse(array1[array1.length-1]).toString(), MathMethods.linearFun(array1.length)));
                series1.setName(DataManager.getInstrument() + "  " + comboType.getValue()+" "+comboBuildType.getValue());
                graph1.getData().add(series1);
                break;
            }
            case "square":{
                for (int i = 0; i < array1.length; i++) {
                    series1.getData().add(new XYChart.Data(new SimpleDateFormat("yyyymmdd").parse(array1[i]).toString(), MathMethods.square(i)));

                }
                series1.setName(DataManager.getInstrument() + "  " + comboType.getValue()+" "+comboBuildType.getValue());
                graph1.getData().add(series1);
                break;
            }
            case "exp":{
                for (int i = 0; i < array1.length; i++) {
                    series1.getData().add(new XYChart.Data(new SimpleDateFormat("yyyymmdd").parse(array1[i]).toString(), MathMethods.expFun(array2[i])));
                }
                series1.setName(DataManager.getInstrument() + "  " + comboType.getValue()+" "+comboBuildType.getValue());
                graph1.getData().add(series1);
                break;
            }
            case "simple":{
                for (int i = 0; i < array1.length; i++) {
                    series1.getData().add(new XYChart.Data(new SimpleDateFormat("yyyymmdd").parse(array1[i]).toString(), array2[i]));
                }
                series1.setName(DataManager.getInstrument() + "  " + comboType.getValue()+" "+comboBuildType.getValue());
                graph1.getData().add(series1);
                break;
            }
        }

        buildTable();
    }

    @FXML
    void downloadFile(MouseEvent event) throws IOException {
        isThreadsStarted=false;
        //дата выводится так гггг-мм-дд
        String dateFrom[] = datePIckerFromInstrument.getValue().toString().split("-");
        String dateTo[] = getDatePIckerToInstrument.getValue().toString().split("-");
        String em;
        switch (comboName.getValue()) {
            case "GAZP": {
                em = "16842";
                break;
            }
            case "TATN": {
                em = "825";
                break;
            }
            case "CHMF": {
                em = "16136";
                break;
            }
            case "SNGS": {
                em = "4";
                break;
            }
            case "GMKN": {
                em = "795";
                break;
            }
            default:
                em = "";
        }
        System.out.println("Привет из download в контроллере");
        System.out.println(comboName.getValue() + "  " + em);
        String url = DownloadFromWeb.getUrl(comboName.getValue(), dateFrom, dateTo, em);
        DataManager.setFile(DownloadFromWeb.getfile(url));
    }

    void buildTable() throws IOException, ParseException {
        double open[] = DataManager.getdata(1);
        double high[] = DataManager.getdata(2);
        double low[] = DataManager.getdata(3);
        double close[] = DataManager.getdata(4);
        double volume[] = DataManager.getdata(5);
        String date[] = DataManager.getdate();

        ArrayList<DataClass> arrlist = new ArrayList<DataClass>();

        for (int i = 0; i < open.length; i++) {
            arrlist.add(new DataClass(date[i], open[i], high[i], low[i], close[i], volume[i]));
        }

        daysData = FXCollections.observableArrayList(arrlist);


        dateColumn.setCellValueFactory(new PropertyValueFactory<DataClass, String>("date"));
        openColumn.setCellValueFactory(new PropertyValueFactory<DataClass, Double>("open"));
        closeColumn.setCellValueFactory(new PropertyValueFactory<DataClass, Double>("close"));
        highColumn.setCellValueFactory(new PropertyValueFactory<DataClass, Double>("high"));
        lowColumn.setCellValueFactory(new PropertyValueFactory<DataClass, Double>("low"));
        volumeColumn.setCellValueFactory(new PropertyValueFactory<DataClass, Double>("volume"));

        tableView.setItems(daysData);


    }
}

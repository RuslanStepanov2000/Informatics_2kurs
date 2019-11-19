import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class Controller {

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Pane registerWindow;

    @FXML
    private Pane loginWindow;

    @FXML
    public Button buttonBackRegistration;

    @FXML
    public Button buttonBackLogin;

    @FXML
    private Button buttonRegisterComplete;

    @FXML
    private TextField registerName;

    @FXML
    private TextField registerSurname;

    @FXML
    private PasswordField registerPassword;

    @FXML
    private TextField registerLogin;


    @FXML
    private Button RegisterButton;

    @FXML
    private Button LoginButton;

    @FXML
    void actionLoginButton(ActionEvent event) {
        loginWindow.setVisible(true);
    }

    @FXML
    void actionRegisterButton(ActionEvent event) {
        registerWindow.setVisible(true);
    }

    @FXML
    void buttonRegisterComplete(MouseEvent  event) {
        registerWindow.setVisible(false);
    }
    @FXML
    void buttonLoginComplete(MouseEvent  event) {
        registerWindow.setVisible(false);
    }

    @FXML
    void buttonBackRegistration(MouseEvent mouseEvent) {
        buttonBackRegistration.getParent().setVisible(false);

    }

    @FXML
    void buttonBackLogin(MouseEvent mouseEvent) {
        buttonBackLogin.getParent().setVisible(false);
    }

    @FXML
    void initialize(MouseEvent event) {

    }

    @FXML
    void loginButton(ActionEvent event) {

    }

}

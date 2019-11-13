import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Pane registerWindow;

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

    }

    @FXML
    void actionRegisterButton(ActionEvent event) {
        RegisterButton.setVisible(false);
        LoginButton.setVisible(false);
        registerWindow.setVisible(true);
    }

    @FXML
    void buttonRegisterComplete(MouseEvent  event) {
        registerWindow.setVisible(false);
        RegisterButton.setVisible(true);
        LoginButton.setVisible(true);

    }

    @FXML
    void initialize(MouseEvent event) {

    }

    @FXML
    void loginButton(ActionEvent event) {

    }

}

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Controller {

    @FXML
    private Label zapolnite_polya;

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
    public static Button buttonRegisterComplete;

    @FXML
    private TextField loginLogin;

    @FXML
    private PasswordField loginPassword ;

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
    private DialogPane dialogPane_errorRegister;

    @FXML
    void actionLoginButton(ActionEvent event) {
        loginWindow.setVisible(true);
    }

    @FXML
    void actionRegisterButton(ActionEvent event) {
        registerWindow.setVisible(true);
    }

    @FXML
    void buttonRegisterComplete(MouseEvent  event) throws SQLException {
        if (registerName.getText().isEmpty()|registerSurname.getText().isEmpty()|registerLogin.getText().isEmpty()|registerPassword.getText().isEmpty()){
            zapolnite_polya.setVisible(true);
        }
        else {
            zapolnite_polya.setVisible(false);
            User user = new User();
            user.setName(registerName.getText());
            user.setSurname(registerSurname.getText());
            user.setLogin(registerLogin.getText());
            user.setPassword(registerPassword.getText());
            new DBManager().newuserPush(user);
            registerWindow.setVisible(false);
        }


    }

    @FXML
    void buttonLoginComplete(MouseEvent  event) throws SQLException {
        User user=new User();
        DBManager dbm=new DBManager();
        user.setLogin(loginLogin.getText());
        user.setPassword(loginPassword.getText());

        if (dbm.userLogin(user)) {
            user.setId(DBManager.getUserId(user));
            DBManager.logUserLogin(user);
            Node source = (Node) event.getSource();
            Stage stageThis = (Stage) source.getScene().getWindow();
            stageThis.close();

            Stage stage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("workingSpace/WorkingSpace.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(new Scene(root));
            stage.setResizable(true);
            stage.setTitle("Analyses of stocks");
            stage.show();

            buttonBackLogin.getParent().setVisible(false);
            registerWindow.setVisible(false);
        }
    }

    @FXML
    void buttonBackRegistration(MouseEvent mouseEvent) {
        buttonBackRegistration.getParent().setVisible(false);
        zapolnite_polya.setVisible(false);

    }

    @FXML
    void buttonBackLogin(MouseEvent mouseEvent) {
        loginWindow.setVisible(false);

    }

    @FXML
    void initialize(MouseEvent event) {

    }

    @FXML
    void loginButton(ActionEvent event) {
    }

}

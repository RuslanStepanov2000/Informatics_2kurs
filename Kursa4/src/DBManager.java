//"jdbc:MySQL://localhost:3306", "root", "Ruslan2411"

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class DBManager extends Application {
    /**
     * Класс взаимодействия с базой даных
     */
    private static Connection conn;

    public Connection getConn() {
        /**
         * Возвращает подключение к БД
         */
        return conn;
    }

    private static void dbConnection() {
        /**
         * Непосредственно подключение к БД
         */
        System.out.println("nn***** MySQL JDBC Connection Testing *****");
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "Ruslan2411");
            System.out.println("Database Connection Established...");
        } catch (Exception ex) {
            System.err.println("Cannot connect to database server");
            ex.printStackTrace();
        }
    }


    void newuserPush(User user) throws SQLException {
        /**
         * Добавление нового пользователя в БД
         * @user берется толкьо логин и пароль
         */
        dbConnection();
        String sql = "INSERT INTO kursa4.user (user_login, user_password) Values (?, ?)";
        PreparedStatement statement = getConn().prepareStatement(sql);
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.executeUpdate();
        conn.close();
    }

    public boolean userLogin(User user) throws SQLException {
        /**
         * Метод авторизации пользователей. Соедеиняется с БД, сравнивает поля user'a с данными БД
         * @param user объект класса юзер, с полями логин и пароль
         * @return Если пользователь не найден в базе, возвращает ошибку
         *         Если пользователь найден в базе, открывается рабочее пространство WorkingSpace.fxml и в объект user передаются настройки
         *         пользователя и местонахождение его локальных данных
         */
        dbConnection();
        String sql = "select * " +
                "FROM kursa4.user " +
                "where user_login=? and user_password=?";
        PreparedStatement statement = getConn().prepareStatement(sql);

        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());

        ResultSet rs = statement.executeQuery();
        //В случае ошибки
        if (rs.next()) {
            user.setUserid(rs.getInt(1));
            System.out.println("ДОБРО ПОЖАЛОВАТЬ");
            conn.close();
            return true;
        } else {
            //какая-то ошибка и чего то там сделать
            System.out.println("ОНА ТЕБЯ СОЖРЕТ ПАРОЛЬ НЕВЕРНЫЙ");
            conn.close();
            return false;
        }

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene root2 = new Scene(FXMLLoader.load(getClass().getResource("WorkingSpace.fxml")));
        primaryStage.setScene(root2);
        primaryStage.setTitle("Analyses of stocks");
        primaryStage.show();
    }
}



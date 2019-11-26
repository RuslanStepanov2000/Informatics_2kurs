//"jdbc:MySQL://localhost:3306", "root", "Ruslan2411"

import java.sql.*;

public class DBManager {
    /**
     * Класс взаимодействия с базой даных
     *
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
            conn=DriverManager.getConnection("jdbc:mysql://localhost/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "Ruslan2411");
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

    void userLogin(User user) throws SQLException {
        /**
         * Метод авторизации пользователей. Соедеиняется с БД, сравнивает поля user'a с данными БД
         * @param user объект класса юзер, с полями логин и пароль
         * @return Если пользователь не найден в базе, возвращает ошибку
         *         Если пользователь найден в базе, открывается рабочее пространство WorkingSpace.fxml и в объект user передаются настройки
         *         пользователя и местонахождение его локальных данных
         */
        String login=user.getLogin();
        String pass=user.getPassword();
        dbConnection();
        String sql = "SELECT * FROM kursa4.user (user_login, user_password) Values (?, ?)";
        PreparedStatement statement=getConn().prepareStatement(sql);
        statement.setString(1, login);
        statement.setString(2, pass);
        statement.executeUpdate();

    }


}



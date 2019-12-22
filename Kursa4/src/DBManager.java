//"jdbc:MySQL://localhost:3306", "root", "Ruslan2411"

import java.sql.*;
import java.util.Date;

public class DBManager  {
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
        System.out.println("***** MySQL JDBC Connection Testing *****");
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

        //Заполнил id в таблице с логами
        user.setId(getUserId(user));
        String sql2="INSERT INTO kursa4.user_activity (user_id) VALUES (?)";
        PreparedStatement statement2 = getConn().prepareStatement(sql2);
        statement2.setString(1,user.getId());
        statement2.executeUpdate();
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

    public static String getUserId(User user) throws SQLException {
        String id;
        dbConnection();
        String sqlGetId = "select * " +
                "FROM kursa4.user " +
                "where user_login=?";
        PreparedStatement statement = conn.prepareStatement(sqlGetId);
        statement.setString(1, user.getLogin());
            ResultSet rs = statement.executeQuery();
        rs.next();
        id=rs.getString(1);

        conn.close();
        return id;
    }

    public static void  logUserLogin(User user) throws SQLException {
        System.out.println("logUserLogin()");
        String id=user.getId();
        Date date = new Date();
        dbConnection();
        String sql = "INSERT INTO kursa4.user_activity  (user_loginDate,user_id) VALUES(?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, date.toString());
        statement.setString(2, user.getId());
        statement.executeUpdate();
        conn.close();


    }


}



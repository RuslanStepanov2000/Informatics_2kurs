import java.sql.*;

public class testclass {
    public static void main(String[] args) throws SQLException {
        User user=new User();
        user.setLogin("qq");
        user.setPassword("qqq");
        userLogin(user);


    }
    private static Connection conn;
    public static Connection getConn() {
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
            conn= DriverManager.getConnection("jdbc:mysql://localhost/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "Ruslan2411");
            System.out.println("Database Connection Established...");
        } catch (Exception ex) {
            System.err.println("Cannot connect to database server");
            ex.printStackTrace();
        }
    }
    public static void userLogin(User user) throws SQLException {
        /**
         * Метод авторизации пользователей. Соедеиняется с БД, сравнивает поля user'a с данными БД
         * @param user объект класса юзер, с полями логин и пароль
         * @return Если пользователь не найден в базе, возвращает ошибку
         *         Если пользователь найден в базе, открывается рабочее пространство WorkingSpace.fxml и в объект user передаются настройки
         *         пользователя и местонахождение его локальных данных
         * Поля в бд по-порядку user_id, user_login, user_password
         */
        dbConnection();
        String sql = "select* " +
                "FROM kursa4.user " +
                "where user_login=? and user_password=?";
        PreparedStatement statement = getConn().prepareStatement(sql);
        statement.setString(1,user.getLogin());
        statement.setString(2,user.getPassword());
        ResultSet rs = statement.executeQuery();
        //В случае ошибки
        if (rs.next()){
            user.setUserid(rs.getInt(1));
        }
        else {
            //какая-то ошибка и чего то там сделать
            System.out.println("ОНА ТЕБЯ СОЖРЕТ ПАРОЛЬ НЕВЕРНЫЙ");
        }
        conn.close();

    }
}



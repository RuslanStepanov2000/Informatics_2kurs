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
        return conn;
    }

    public static void dbConnection() {
        System.out.println("nn***** MySQL JDBC Connection Testing *****");
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "Ruslan2411");
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
         */
        dbConnection();

        String sql = "select user_login,user_password " +
                "FROM kursa4.user " +
                "where user_login=?";

        PreparedStatement statement = getConn().prepareStatement(sql);
        statement.setString(1,user.getLogin());
        ResultSet rs = statement.executeQuery();
        if (rs.next()){
            System.out.println(rs.getString(1)+"   "+rs.getString(2));
        }




        conn.close();
    }
}



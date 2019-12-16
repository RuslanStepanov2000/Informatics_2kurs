import java.sql.*;


public class test  {

    public static void main(String[] args) throws SQLException {
        Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "Ruslan2411");
            System.out.println("Database Connection Established...");
        String sql = "select * " +
                "FROM kursa4.user " +
                "where user_login=?";
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, "w");

        ResultSet rs = statement.executeQuery();
        rs.next();

        System.out.println(rs.getString(1));
    }
}
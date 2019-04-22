package model;

import org.apache.log4j.Logger;

public class App {
    private final static Logger LOGGER = Logger.getLogger(App.class.getSimpleName());
    public static void main(String[] args) {
        System.out.println("Hello!");
//
//        Connection connection =
//                null;
//        try {
//            connection = DriverManager.
//                    getConnection("jdbc:" +
//                                    "mysql:" +
//                                    "//localhost:3306/" +
//                                    "cashmachine" +
//                                    "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
//                            "root",
//                            "root");
//
//
//            Statement query = connection.createStatement();
//            ResultSet rs = query.executeQuery("SELECT * FROM worker");
//            while (rs.next()) {
//                System.out.println(rs.getString("name"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
}

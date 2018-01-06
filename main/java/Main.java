import controller.MainController;
import input.MyScanner;
import model.DatabaseConnection;
import view.MainView;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        MyScanner scanner = new MyScanner();
        MainView mainView = new MainView();
        DatabaseConnection dbConnection = DatabaseConnection.INSTANCE;
        try {
            Connection connection = dbConnection.getConnection();
            MainController controller = new MainController(mainView,scanner, connection);
            controller.run();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

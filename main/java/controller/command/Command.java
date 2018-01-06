package controller.command;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Command<R, Q> {
    R execute(Connection connection, Q request) throws SQLException;
}

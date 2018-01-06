package controller.command;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Command<K, V> {
    K execute(Connection connection, V request) throws SQLException;
}

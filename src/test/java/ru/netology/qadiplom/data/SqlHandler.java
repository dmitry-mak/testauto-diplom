package ru.netology.qadiplom.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlHandler {

    private static final QueryRunner runner = new QueryRunner();

    private static final String dbUrl = System.getProperty("db.url");
    private static final String dbUser = System.getProperty("db.username");
    private static final String dbPassword = System.getProperty("db.password");

    public SqlHandler() {
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @SneakyThrows
    public static String getLastTransactionId() {
        var sqlQuery = "SELECT transaction_id FROM payment_entity ORDER BY created DESC LIMIT 1;";
        var connection = getConnection();
        return runner.query(connection, sqlQuery, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getPaymentStatus(){
        var sqlQuery = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        var connection = getConnection();
        return runner.query(connection, sqlQuery, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getCreditStatus(){
        var sqlQuery = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        var connection = getConnection();
        return runner.query(connection, sqlQuery, new ScalarHandler<>());
    }

    @SneakyThrows
    public static void cleanAllTables() {
        var runner = new QueryRunner();
        var deletePaymentEntityTable = "DELETE FROM payment_entity;";
        var deleteCreditRequestEntityTable = "DELETE FROM credit_request_entity;";
        var deleteOrderEntityTable = "DELETE FROM order_entity;";

        try (var connection = getConnection()) {
            runner.update(connection, deletePaymentEntityTable);
            runner.update(connection, deleteCreditRequestEntityTable);
            runner.update(connection, deleteOrderEntityTable);
        }
    }

}

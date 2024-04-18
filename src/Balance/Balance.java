package Balance;

import java.sql.*;
import java.util.Scanner;
public class Balance {

    // Подключение к базе данных (замените на свои значения)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "12121212";

    // SQL-запрос
    private static final String SELECT_BALANCE_SQL = "SELECT balance FROM users WHERE account_number = ?";

    /**
     * Выводит balance из базы данных по accountNumber.
     *
     * @throws SQLException Ошибка при работе с базой данных
     */
    public static void getBalance() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int account = sc.nextInt();


        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BALANCE_SQL);
            statement.setInt(1, account);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                double balance = resultSet.getDouble("balance");
                System.out.println("Баланс счета " + account + ": " + balance);
            } else {
                System.out.println("Счет не найден: " + account);
            }

            }
        }
    }



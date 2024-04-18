package registration;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class UserRegistration {

    // Подключение к базе данных (замените на свои значения)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "12121212";

    // SQL-запросы
    private static final String INSERT_USER_SQL = "INSERT INTO users (username, password, balance, account_number) VALUES (?, ?, ?, ?)";
    private static final String RANDOM_ACCOUNT_NUMBER_SQL = "SELECT RAND()";

    public static void Main() throws SQLException {




        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            // Создание PreparedStatements
            PreparedStatement insertUserStatement = connection.prepareStatement(INSERT_USER_SQL);
            PreparedStatement randomAccountNumberStatement = connection.prepareStatement(RANDOM_ACCOUNT_NUMBER_SQL);

            // Генерация случайного номера счета
            int minAccountNumber = 1;
            int maxAccountNumber = 1000000;

            // Генератор случайных чисел
            Random random = new Random();

            // Генерация случайного accountNumber
            int accountNumber = random.nextInt(maxAccountNumber - minAccountNumber + 1) + minAccountNumber;

            System.out.println("Случайный accountNumber: " + accountNumber);
            // Установка значений параметров запроса INSERT
            Scanner sc = new Scanner(System.in);  System.out.println(" Вкдиьть имя пользователя"); String username = sc.nextLine();
            System.out.println(" Вкдиьть пароль ");
            String password = sc.nextLine();

            insertUserStatement.setString(1, username);

            insertUserStatement.setString(2, password);
            insertUserStatement.setDouble(3, 0.0); // Баланс по умолчанию
            insertUserStatement.setInt(4, accountNumber);

            // Выполнение запросов
            insertUserStatement.executeUpdate();
            System.out.println("Пользователь " + username + " успешно зарегистрирован!");
            System.out.println("Номер счета: " + accountNumber);
        }
    }


    private static boolean accountNumberExists(Connection connection, int accountNumber) throws SQLException {
        String sql = "SELECT 1 FROM users WHERE account_number = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, accountNumber);
        ResultSet resultSet = statement.executeQuery();


        return false;
    }
}

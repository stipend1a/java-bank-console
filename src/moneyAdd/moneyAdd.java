package moneyAdd;

   import java.sql.*;
import java.util.Scanner;
public class moneyAdd {


        // Подключение к базе данных (замените на свои значения)
        private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "12121212";

    // SQL-запросы
        private static final String UPDATE_BALANCE_ADD_SQL = "UPDATE users SET balance = balance + ? WHERE account_number = ?";
        private static final String UPDATE_BALANCE_SUBTRACT_SQL = "UPDATE users SET balance = balance - ? WHERE account_number = ?";

        /**
         * Добавляет или отнимает значение от balance в базе данных.
         *
         * @param accountNumber Номер счета
         * @throws SQLException Ошибка при работе с базой данных
         */
        public static void updateBalance(int accountNumber) throws SQLException {
            Scanner scanner = new Scanner(System.in);


            System.out.print("Введите сумму для добавления/отнимания (положительное/отрицательное): ");
            double amount = scanner.nextDouble();

            String sql = amount >= 0 ? UPDATE_BALANCE_ADD_SQL : UPDATE_BALANCE_SUBTRACT_SQL;

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setDouble(1, Math.abs(amount));
                statement.setInt(2, accountNumber);
                int affectedRows = statement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Баланс счета " + accountNumber + " обновлен. Новое значение: " + getBalance(accountNumber));
                } else {
                    System.out.println("Ошибка обновления balance: счет не найден или недостаточно средств.");
                }
            }
        }

        /**
         * Выводит balance из базы данных по accountNumber.
         *
         * @param accountNumber Номер счета
         * @return Balance
         * @throws SQLException Ошибка при работе с базой данных
         */
        private static double getBalance(int accountNumber) throws SQLException {
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
                PreparedStatement statement = connection.prepareStatement("SELECT balance FROM users WHERE account_number = ?");
                statement.setInt(1, accountNumber);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getDouble("balance");
                } else {
                    return 0.0; // Счет не найден
                }
            }
        }

        public static void main() {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите номер счета: ");
            int accountNumber = scanner.nextInt();

            try {
                updateBalance(accountNumber);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

}

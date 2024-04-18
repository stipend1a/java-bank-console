import java.sql.*;
import java.util.Scanner;

import Balance.*;
import registration.*;
import moneyAdd.moneyAdd;
public class Main
{



    public static void main(String[] args) throws SQLException {
        System.out.println("Меню:");
        System.out.println("1.регистрация");
        System.out.println("2. пополнение баланса");
        System.out.println("3. мой баланс");
        System.out.print("Введите ваш выбор (1-3): ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
          UserRegistration.Main();
                break;
            case 2:
                System.out.println("Выбран пункт 2. ");
                moneyAdd.main();
                break;
            case 3:
                System.out.println("Выбран пункт 3. ");
                Balance.getBalance();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Неверный выбор. Пожалуйста, выберите из 1, 2 или 3.");
        }

        //

//


    }



    }






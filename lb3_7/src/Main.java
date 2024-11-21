import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        String databasePath = "D:/java/3.accdb"; // Укажите путь к вашей базе данных Access
        String url = "jdbc:ucanaccess://" + databasePath;

        try (Connection connection = DriverManager.getConnection(url)) {
            System.out.println("Подключение к базе данных Access прошло успешно!");

            String query="WITH Магазины_в_Заречном AS (" +
                    "    SELECT ma.ID_mag" +
                    "    FROM Магазин ma" +
                    "    WHERE ma.raon = 'Заречный' " +
                    "), Товары_от_Молокозаводов AS (" +
                    "    SELECT tov.id" +
                    "    FROM Товар as tov" +
                    "    WHERE tov.post='Молокозавод №1'" +
                    "), " +
                    "SELECT t.count, t.Seles" +
                    "FROM 'Движение_товаров' as t" +
                    "JOIN Магазины_в_Заречном m ON t.'ID_mag' = m.ID_mag \n" +
                    "JOIN Товары_от_Молокозаводов n ON t.id = n.id \n" +
                    "WHERE t.'type_op' = 'Продажа'; \n";

            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("Артикулы:"));
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных Access: " + e.getMessage());
        }
    }
}
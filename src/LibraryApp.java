import static spark.Spark.*;
import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryApp {

    private static final Gson gson = new Gson();

    public static void main(String[] args) {

        port(8080);

        // Указание папки, где лежит сайт (public)
        staticFiles.externalLocation("C:/Users/evast/OneDrive/Рабочий стол/LibraryWebsite/public");

        // ===== API: получение всех книг =====
        get("/books", (req, res) -> {
            res.type("application/json");
            return gson.toJson(getAllBooks());
        });
    }

    // Метод чтения книг из БД
    public static List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();

        try (Connection con = Database.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT id, title, author, year, status, genre FROM books")) {

            while (rs.next()) {
                Book b = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getString("status"),
                        rs.getString("genre")
                );
                list.add(b);
            }

        } catch (Exception e) {
            System.out.println("Ошибка при загрузке книг:");
            e.printStackTrace();
        }
        return list;
    }

    // Модель книги
    public static class Book {
        int id;
        String title;
        String author;
        int year;
        String status;
        String genre;

        public Book(int id, String title, String author, int year, String status, String genre) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.year = year;
            this.status = status;
            this.genre = genre;
        }
    }
}

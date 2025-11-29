public class TestDB {
    public static void main(String[] args) {
        try (java.sql.Connection conn = Database.getConnection()) {
            System.out.println("✅ Подключение успешно!");
        } catch (Exception e) {
            System.out.println("❌ Ошибка подключения");
            e.printStackTrace();
        }
    }
}

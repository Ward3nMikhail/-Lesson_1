public class ParkDemo {
    public static void main(String[] args) {
        // Создаём парк на 3 аттракциона
        Park park = new Park(3);

        // Добавляем аттракционы
        park.addAttraction(0, "Американские горки", "10:00–20:00", 500);
        park.addAttraction(1, "Колесо обозрения", "11:00–19:00", 300);
        park.addAttraction(2, "Карусель", "12:00–18:00", 200);

        // Выводим информацию обо всех аттракционах
        park.printAllAttractions();
    }
}

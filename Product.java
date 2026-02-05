public class Product {
    // Поля класса
    private String name;
    private String productionDate;
    private String manufacturer;
    private String country;
    private double price;
    private boolean isReserved;

    // Конструктор
    public Product(String name, String productionDate, String manufacturer,
                   String country, double price, boolean isReserved) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.country = country;
        this.price = price;
        this.isReserved = isReserved;
    }

    // Метод для вывода информации
    public void printInfo() {
        System.out.println("Название: " + name);
        System.out.println("Дата производства: " + productionDate);
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Страна происхождения: " + country);
        System.out.println("Цена: " + price + " руб.");
        System.out.println("Зарезервирован: " + (isReserved ? "Да" : "Нет"));
        System.out.println("-".repeat(30));
    }
}

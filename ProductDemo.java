public class ProductDemo {
    public static void main(String[] args) {
        // Создаём массив объектов Product
        Product[] productsArray = new Product[5];

        // Заполняем массив объектами
        productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025",
                "Samsung Corp.", "Korea", 5599, true);
        productsArray[1] = new Product("iPhone 17 Pro", "15.03.2025",
                "Apple Inc.", "USA", 6999, false);
        productsArray[2] = new Product("Xiaomi 15", "20.01.2025",
                "Xiaomi Corp.", "China", 3499, true);
        productsArray[3] = new Product("Google Pixel 10", "10.04.2025",
                "Google LLC", "USA", 4899, false);
        productsArray[4] = new Product("OnePlus 13", "05.05.2025",
                "OnePlus Ltd.", "China", 4299, true);

        // Выводим информацию о всех товарах
        System.out.println("Список товаров:\n");
        for (Product product : productsArray) {
            product.printInfo();
        }
    }
}

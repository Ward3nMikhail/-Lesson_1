public class ShapeDemo {
    public static void main(String[] args) {
        // Создаём фигуры
        Circle circle = new Circle(5.0, "красный", "чёрный");
        Rectangle rectangle = new Rectangle(4.0, 6.0, "зелёный", "синий");
        Triangle triangle = new Triangle(3.0, 4.0, 5.0, "жёлтый", "фиолетовый");

        // Выводим характеристики каждой фигуры
        circle.printInfo();
        rectangle.printInfo();
        triangle.printInfo();
    }
}


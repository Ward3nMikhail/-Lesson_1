public interface Shape {
    String getFillColor();
    String getBorderColor();

    double getPerimeter();
    double getArea();

    // Дефолтный метод: выводит все характеристики в консоль
    default void printInfo() {
        System.out.printf(
                "Фигура: %s | Периметр: %.2f | Площадь: %.2f | Цвет фона: %s | Цвет границ: %s%n",
                this.getClass().getSimpleName(),
                getPerimeter(),
                getArea(),
                getFillColor(),
                getBorderColor()
        );
    }
}

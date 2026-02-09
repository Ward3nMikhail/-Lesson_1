public class TriangleAreaCalculator {
    public static double calculateArea(double base, double height) {
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Основание и высота должны быть положительными числами");
        }
        return (base * height) / 2;
    }

    public static double calculateAreaBySides(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Все стороны должны быть положительными числами");
        }
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Треугольник с такими сторонами не существует");
        }

        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
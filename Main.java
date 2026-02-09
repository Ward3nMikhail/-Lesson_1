import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ДЕМОНСТРАЦИЯ РАБОТЫ ПРОГРАММ (TestNG Ветка) ===");
        System.out.println();

        // Демонстрация вычисления факториала
        System.out.println("1. ВЫЧИСЛЕНИЕ ФАКТОРИАЛА");
        System.out.print("Введите число для вычисления факториала: ");
        int factorialInput = scanner.nextInt();
        try {
            long factorialResult = FactorialCalculator.factorial(factorialInput);
            System.out.println("Факториал числа " + factorialInput + " = " + factorialResult);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        System.out.println();

        // Демонстрация вычисления площади треугольника
        System.out.println("2. ВЫЧИСЛЕНИЕ ПЛОЩАДИ ТРЕУГОЛЬНИКА");
        System.out.println("   Метод 1: по основанию и высоте");
        System.out.print("   Введите основание треугольника: ");
        double base = scanner.nextDouble();
        System.out.print("   Введите высоту треугольника: ");
        double height = scanner.nextDouble();
        try {
            double area1 = TriangleAreaCalculator.calculateArea(base, height);
            System.out.println("   Площадь треугольника = " + area1);
        } catch (IllegalArgumentException e) {
            System.out.println("   Ошибка: " + e.getMessage());
        }

        System.out.println("   Метод 2: по трем сторонам");
        System.out.print("   Введите сторону A: ");
        double sideA = scanner.nextDouble();
        System.out.print("   Введите сторону B: ");
        double sideB = scanner.nextDouble();
        System.out.print("   Введите сторону C: ");
        double sideC = scanner.nextDouble();
        try {
            double area2 = TriangleAreaCalculator.calculateAreaBySides(sideA, sideB, sideC);
            System.out.println("   Площадь треугольника = " + area2);
        } catch (IllegalArgumentException e) {
            System.out.println("   Ошибка: " + e.getMessage());
        }
        System.out.println();

        // Демонстрация арифметических операций
        System.out.println("3. АРИФМЕТИЧЕСКИЕ ОПЕРАЦИИ");
        System.out.print("Введите первое число: ");
        int num1 = scanner.nextInt();
        System.out.print("Введите второе число: ");
        int num2 = scanner.nextInt();

        System.out.println("Результаты:");
        System.out.println(num1 + " + " + num2 + " = " + ArithmeticOperations.add(num1, num2));
        System.out.println(num1 + " - " + num2 + " = " + ArithmeticOperations.subtract(num1, num2));
        System.out.println(num1 + " * " + num2 + " = " + ArithmeticOperations.multiply(num1, num2));
        try {
            System.out.println(num1 + " / " + num2 + " = " + ArithmeticOperations.divide(num1, num2));
        } catch (ArithmeticException e) {
            System.out.println(num1 + " / " + num2 + " = Ошибка: " + e.getMessage());
        }
        System.out.println();

        // Демонстрация сравнения чисел
        System.out.println("4. СРАВНЕНИЕ ЧИСЕЛ");
        System.out.print("Введите первое число для сравнения: ");
        int compareNum1 = scanner.nextInt();
        System.out.print("Введите второе число для сравнения: ");
        int compareNum2 = scanner.nextInt();

        String comparisonResult = NumberComparator.compare(compareNum1, compareNum2);
        System.out.println("Результат сравнения: " + comparisonResult);
        System.out.println();

        // Примеры с фиксированными значениями
        System.out.println("5. ПРИМЕРЫ РАБОТЫ ПРОГРАММ:");
        System.out.println("   Факториал 5 = " + FactorialCalculator.factorial(5));
        System.out.println("   Площадь треугольника (основание=10, высота=5) = " +
                TriangleAreaCalculator.calculateArea(10, 5));
        System.out.println("   15 + 7 = " + ArithmeticOperations.add(15, 7));
        System.out.println("   Сравнение 10 и 20: " + NumberComparator.compare(10, 20));

        scanner.close();
    }
}

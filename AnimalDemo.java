public class AnimalDemo {
    public static void main(String[] args) {
        // Создаем животных
        Dog dog1 = new Dog("Бобик");
        Cat cat1 = new Cat("Мурзик");
        Cat cat2 = new Cat("Барсик");

        // Проверяем действия
        dog1.run(300);   // Бобик пробежал 300 м.
        dog1.swim(5);     // Бобик проплыл 5 м.
        cat1.run(150);   // Мурзик пробежал 150 м.
        cat1.swim(1);    // Мурзик не умеет плавать!

        // Миска с едой
        Bowl bowl = new Bowl(15);

        // Массив котов
        Cat[] cats = {cat1, cat2};

        // Коты едят
        for (Cat cat : cats) {
            cat.eat(bowl, 10);
        }

        // Проверяем сытость
        for (Cat cat : cats) {
            System.out.println(cat.name + " сыт: " + cat.isFull());
        }

        // Добавляем еду и пробуем снова
        bowl.addFood(10);
        cat2.eat(bowl, 5);

        // Статистика
        System.out.println("\nСтатистика:");
        System.out.println("Всего животных: " + Animal.getAnimalCount());
        System.out.println("Собак: " + Dog.getDogCount());
        System.out.println("Котов: " + Cat.getCatCount());
    }
}

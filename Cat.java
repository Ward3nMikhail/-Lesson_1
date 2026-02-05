public class Cat extends Animal {
    private static int catCount = 0;
    private boolean isFull; // сытость

    public Cat(String name) {
        super(name);
        this.isFull = false; // по умолчанию голоден
        catCount++;
    }

    @Override
    public void run(int distance) {
        if (distance <= 200) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м. (максимум 200 м.)");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать!");
    }

    // Метод для еды из миски
    public void eat(Bowl bowl, int amount) {
        if (!isFull && bowl.takeFood(amount)) {
            isFull = true;
            System.out.println(name + " поел и теперь сыт.");
        } else {
            System.out.println(name + " не поел (не хватило еды или уже сыт).");
        }
    }

    public boolean isFull() {
        return isFull;
    }

    public static int getCatCount() {
        return catCount;
    }
}

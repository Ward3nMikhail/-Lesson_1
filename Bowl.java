public class Bowl {
    private int foodAmount;

    public Bowl(int initialFood) {
        this.foodAmount = initialFood;
    }

    // Взять еду из миски (возвращает true, если удалось)
    public boolean takeFood(int amount) {
        if (foodAmount >= amount) {
            foodAmount -= amount;
            return true;
        }
        return false;
    }

    // Добавить еду в миску
    public void addFood(int amount) {
        foodAmount += amount;
        System.out.println("В миску добавлено " + amount + " ед. еды. Теперь в миске: " + foodAmount);
    }

    public int getFoodAmount() {
        return foodAmount;
    }
}

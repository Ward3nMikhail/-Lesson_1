public class Park {
    // Внутренний класс для представления аттракциона
    public class Attraction {
        private String name;
        private String workingHours;
        private double ticketPrice;

        // Конструктор внутреннего класса
        public Attraction(String name, String workingHours, double ticketPrice) {
            this.name = name;
            this.workingHours = workingHours;
            this.ticketPrice = ticketPrice;
        }

        // Метод для вывода информации об аттракционе
        public void printInfo() {
            System.out.println("Аттракцион: " + name);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость билета: " + ticketPrice + " руб.");
            System.out.println("-".repeat(30));
        }
    }

    // Поле для хранения списка аттракционов
    private Attraction[] attractions;

    // Конструктор класса Park
    public Park(int capacity) {
        this.attractions = new Attraction[capacity];
    }

    // Метод для добавления аттракциона
    public void addAttraction(int index, String name, String workingHours, double ticketPrice) {
        if (index >= 0 && index < attractions.length) {
            attractions[index] = new Attraction(name, workingHours, ticketPrice);
        } else {
            System.out.println("Ошибка: индекс выходит за пределы массива!");
        }
    }

    // Метод для вывода всех аттракционов
    public void printAllAttractions() {
        System.out.println("Аттракционы парка:\n");
        for (Attraction attraction : attractions) {
            if (attraction != null) {
                attraction.printInfo();
            }
        }
    }
}

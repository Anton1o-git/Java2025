import java.util.Arrays;

public class Lab5 {

    // Узагальнений клас тарифу
    static class Tariff {
        String name;        // Назва тарифу
        double monthlyFee;  // Абонентська плата
        int clients;        // Кількість клієнтів

        public Tariff(String name, double monthlyFee, int clients) {
            this.name = name;
            this.monthlyFee = monthlyFee;
            this.clients = clients;
        }

        @Override
        public String toString() {
            return "Tariff{" +
                    "name='" + name + '\'' +
                    ", monthlyFee=" + monthlyFee +
                    ", clients=" + clients +
                    '}';
        }

        // Перевірка рівності тарифів
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Tariff other = (Tariff) obj;
            return name.equals(other.name) &&
                    monthlyFee == other.monthlyFee &&
                    clients == other.clients;
        }
    }

    // Підкласи тарифів
    static class BasicTariff extends Tariff {
        public BasicTariff(String name, double monthlyFee, int clients) {
            super(name, monthlyFee, clients);
        }
    }

    static class PremiumTariff extends Tariff {
        public PremiumTariff(String name, double monthlyFee, int clients) {
            super(name, monthlyFee, clients);
        }
    }

    static class FamilyTariff extends Tariff {
        public FamilyTariff(String name, double monthlyFee, int clients) {
            super(name, monthlyFee, clients);
        }
    }

    public static void main(String[] args) {
        try {
            // Створення списку тарифів
            Tariff[] tariffs = new Tariff[]{
                    new BasicTariff("Basic", 150.0, 120),
                    new PremiumTariff("Premium", 250.0, 80),
                    new FamilyTariff("Family", 300.0, 200),
                    new BasicTariff("Starter", 100.0, 150)
            };

            System.out.println("Початковий список тарифів:");
            for (Tariff t : tariffs) {
                System.out.println(t);
            }

            // Сортування за абонентською платою
            Arrays.sort(tariffs, (t1, t2) -> Double.compare(t1.monthlyFee, t2.monthlyFee));

            System.out.println("\nВідсортований список тарифів за абонплатою:");
            for (Tariff t : tariffs) {
                System.out.println(t);
            }

            // Загальна кількість клієнтів
            int totalClients = 0;
            for (Tariff t : tariffs) {
                totalClients += t.clients;
            }
            System.out.println("\nЗагальна кількість клієнтів: " + totalClients);

            // Пошук тарифу у заданому діапазоні плати
            double minFee = 130.0;
            double maxFee = 280.0;
            System.out.println("\nТарифи у діапазоні " + minFee + " - " + maxFee + ":");
            for (Tariff t : tariffs) {
                if (t.monthlyFee >= minFee && t.monthlyFee <= maxFee) {
                    System.out.println(t);
                }
            }

        }
        catch (NullPointerException e) {
            System.out.println("Сталася помилка: масив тарифів не ініціалізовано.");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Сталася помилка: вихід за межі масиву.");
        }
        catch (Exception e) {
            System.out.println("Невідома помилка: " + e.getMessage());
        }
    }
}

import java.util.Collection;

public class Lab6 {

    // Узагальнений клас тарифу з ЛР5
    static class Tariff {
        String name;
        double monthlyFee;
        int clients;

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

    // Власна типізована колекція на основі однозв’язного списку
    static class TariffList {
        // Вузол списку
        private class Node {
            Tariff data;
            Node next;

            Node(Tariff data) {
                this.data = data;
            }
        }

        private Node head; // голова списку
        private int size;

        // Порожній конструктор
        public TariffList() {
            head = null;
            size = 0;
        }

        // Конструктор з одним елементом
        public TariffList(Tariff t) {
            head = new Node(t);
            size = 1;
        }

        // Конструктор зі стандартної колекції
        public TariffList(Collection<Tariff> tariffs) {
            for (Tariff t : tariffs) {
                add(t);
            }
        }

        // Додавання елемента в кінець списку
        public void add(Tariff t) {
            Node newNode = new Node(t);
            if (head == null) {
                head = newNode;
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
            size++;
        }

        // Отримання елемента за індексом
        public Tariff get(int index) {
            checkIndex(index);
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.data;
        }

        // Видалення елемента за індексом
        public Tariff remove(int index) {
            checkIndex(index);
            if (index == 0) {
                Tariff data = head.data;
                head = head.next;
                size--;
                return data;
            }
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            Tariff data = prev.next.data;
            prev.next = prev.next.next;
            size--;
            return data;
        }

        // Розмір списку
        public int size() {
            return size;
        }

        // Перевірка допустимого індексу
        private void checkIndex(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Індекс поза межами списку: " + index);
            }
        }

        // Вивід всіх тарифів
        public void printAll() {
            Node current = head;
            while (current != null) {
                System.out.println(current.data);
                current = current.next;
            }
        }
    }

    public static void main(String[] args) {
        try {
            // Початкові дані

            Tariff t1 = new Tariff("Basic", 150, 120);
            Tariff t2 = new Tariff("Premium", 250, 80);
            Tariff t3 = new Tariff("Family", 300.0, 200);
            Tariff t4 = new Tariff("Starter", 100, 150);

            // Створення колекції
            TariffList list = new TariffList();
            list.add(t1);
            list.add(t2);
            list.add(t3);
            list.add(t4);

            System.out.println("Початковий список тарифів:");
            list.printAll();

            // Доступ до елемента
            System.out.println("\nЕлемент на позиції 1:");
            System.out.println(list.get(1));

            // Видалення елемента
            list.remove(0);
            System.out.println("\nСписок після видалення елемента на позиції 0:");
            list.printAll();

            // Розмір списку
            System.out.println("\nРозмір списку: " + list.size());

        } catch (Exception e) {
            System.out.println("Виникла помилка: " + e.getMessage());
        }
    }
}

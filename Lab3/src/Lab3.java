import java.util.Arrays;
import java.util.Comparator;

public class Lab3 {

    // Клас будівельного блоку
    static class Block {
        String name;       // Назва блоку
        double hardness;   // Твердість блоку
        String material;   // Матеріал блоку
        int lightLevel;    // Рівень світла
        boolean isTransparent; // Прозорий чи ні
        String color;      // Колір блоку

        // Конструктор класу
        public Block(String name, double hardness, String material, int lightLevel, boolean isTransparent, String color) {
            this.name = name;
            this.hardness = hardness;
            this.material = material;
            this.lightLevel = lightLevel;
            this.isTransparent = isTransparent;
            this.color = color;
        }

        // Метод для виводу інформації про блок
        @Override
        public String toString() {
            return "Block{" +
                    "name='" + name + '\'' +
                    ", hardness=" + hardness +
                    ", material='" + material + '\'' +
                    ", lightLevel=" + lightLevel +
                    ", isTransparent=" + isTransparent +
                    ", color='" + color + '\'' +
                    '}';
        }

        // Метод для перевірки ідентичності блоків
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Block other = (Block) obj;
            return name.equals(other.name) &&
                    hardness == other.hardness &&
                    material.equals(other.material) &&
                    lightLevel == other.lightLevel &&
                    isTransparent == other.isTransparent &&
                    color.equals(other.color);
        }
    }

    public static void main(String[] args) {
        try {
            // Створення масиву блоків
            Block[] blocks = new Block[]{
                    new Block("Stone", 1.5, "rock", 0, false, "gray"),
                    new Block("Glass", 0.5, "glass", 0, true, "transparent"),
                    new Block("Glowstone", 0.5, "stone", 15, true, "yellow"),
                    new Block("Wood", 1.0, "wood", 0, false, "brown"),
                    new Block("DiamondBlock", 5.0, "gem", 0, false, "blue")
            };

            System.out.println("Початковий масив блоків:");
            for (Block b : blocks) {
                System.out.println(b);
            }

            // Сортування:
            // - за зростанням hardness
            // - при однаковій hardness — за спаданням lightLevel
            Arrays.sort(blocks, new Comparator<Block>() {
                @Override
                public int compare(Block b1, Block b2) {
                    int cmp = Double.compare(b1.hardness, b2.hardness);
                    if (cmp == 0) {
                        cmp = Integer.compare(b2.lightLevel, b1.lightLevel);
                    }
                    return cmp;
                }
            });

            System.out.println("\nВідсортований масив блоків:");
            for (Block b : blocks) {
                System.out.println(b);
            }

            // Пошук ідентичного блоку
            Block searchBlock = new Block("Glass", 0.5, "glass", 0, true, "transparent");
            boolean found = false;
            for (Block b : blocks) {
                if (b.equals(searchBlock)) {
                    found = true;
                    System.out.println("\nЗнайдено ідентичний блок:");
                    System.out.println(b);
                    break;
                }
            }
            if (!found) {
                System.out.println("\nІдентичний блок не знайдено.");
            }

        } catch (Exception e) {
            System.out.println("Виникла помилка: " + e.getMessage());
        }
    }
}

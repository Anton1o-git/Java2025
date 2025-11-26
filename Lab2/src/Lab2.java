public class Lab2 {

    public static void main(String[] args) {
        try {
            // Вхідні дані
            String text = "Це перше речення. Друге речення тут. Третє речення теж тут. Добрий день, це четверте речення.";
            char startChar = 'Д';
            char endChar = 'я';

            System.out.println("Початковий текст:\n" + text);

            // Розбиття на речення
            String[] sentences = text.split("(?<=[.!?])\\s*");

            // Обробка кожного речення
            for (int i = 0; i < sentences.length; i++) {
                sentences[i] = removeLongestSubstring(sentences[i], startChar, endChar);
            }

            // Вивід результату
            System.out.println("\nОброблений текст:");
            for (String s : sentences) {
                System.out.print(s + " ");
            }

        } catch (Exception e) {
            System.out.println("Виникла помилка: " + e.getMessage());
        }
    }

    // Видалення підрядка найбільшої довжини, що починається та закінчується заданими літерами
    private static String removeLongestSubstring(String sentence, char start, char end) {
        int maxLen = 0;
        int startIndex = -1;
        int endIndex = -1;

        // Перебір усіх символів речення
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) == start) {
                for (int j = i + 1; j < sentence.length(); j++) {
                    if (sentence.charAt(j) == end) {
                        int len = j - i + 1;
                        if (len > maxLen) {
                            maxLen = len;
                            startIndex = i;
                            endIndex = j;
                        }
                    }
                }
            }
        }

        if (startIndex != -1) {
            return sentence.substring(0, startIndex) + sentence.substring(endIndex + 1);
        }

        return sentence;
    }
}

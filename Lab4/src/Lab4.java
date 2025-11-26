public class Lab4 {

    // Клас Letter для окремої літери
    static class Letter {
        char value;

        public Letter(char value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    // Клас Punctuation для розділових знаків
    static class Punctuation {
        char value;

        public Punctuation(char value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    // Клас Word, який складається з масиву Letter
    static class Word {
        Letter[] letters;

        public Word(String str) {
            letters = new Letter[str.length()];
            for (int i = 0; i < str.length(); i++) {
                letters[i] = new Letter(str.charAt(i));
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Letter l : letters) {
                sb.append(l);
            }
            return sb.toString();
        }
    }

    // Клас Sentence, який складається з масиву Word та Punctuation
    static class Sentence {
        Object[] elements; // Word або Punctuation

        public Sentence(String str) {
            // Замінюємо всі послідовності пробілів/табуляцій одним пробілом
            str = str.replaceAll("\\s+", " ").trim();

            // Розділяємо на слова та розділові знаки
            String[] tokens = str.split("(?=[.!?])|\\s+");
            elements = new Object[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                if (tokens[i].matches("[.!?]")) {
                    elements[i] = new Punctuation(tokens[i].charAt(0));
                } else {
                    elements[i] = new Word(tokens[i]);
                }
            }
        }

        // Видалення підрядка з початку start до end
        public void removeLongestSubstring(char start, char end) {
            int maxLen = 0;
            int startIndex = -1;
            int endIndex = -1;

            // Створюємо рядок для пошуку
            String sentenceStr = this.toString();

            for (int i = 0; i < sentenceStr.length(); i++) {
                if (sentenceStr.charAt(i) == start) {
                    for (int j = i + 1; j < sentenceStr.length(); j++) {
                        if (sentenceStr.charAt(j) == end) {
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
                String newStr = sentenceStr.substring(0, startIndex) + sentenceStr.substring(endIndex + 1);
                // Перевизначаємо елементи
                Sentence updated = new Sentence(newStr);
                this.elements = updated.elements;
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < elements.length; i++) {
                Object o = elements[i];
                sb.append(o);
                // Додаємо пробіл тільки якщо наступний елемент - не розділовий знак
                if (o instanceof Word && i + 1 < elements.length && !(elements[i + 1] instanceof Punctuation)) {
                    sb.append(" ");
                }
            }
            return sb.toString();
        }
    }

    // Клас Text, який складається з масиву Sentence
    static class Text {
        Sentence[] sentences;

        public Text(String str) {
            String[] sents = str.split("(?<=[.!?])\\s*");
            sentences = new Sentence[sents.length];
            for (int i = 0; i < sents.length; i++) {
                sentences[i] = new Sentence(sents[i]);
            }
        }

        public void removeLongestSubstringInAllSentences(char start, char end) {
            for (Sentence s : sentences) {
                s.removeLongestSubstring(start, end);
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Sentence s : sentences) {
                sb.append(s).append(" ");
            }
            return sb.toString().trim();
        }
    }

    public static void main(String[] args) {
        try {
            String inputText = "Це перше речення. Друге речення тут. Третє речення теж тут. Добрий день, це четверте речення.";
            char startChar = 'Д';
            char endChar = 'я';

            System.out.println("Початковий текст:\n" + inputText);

            Text text = new Text(inputText);
            text.removeLongestSubstringInAllSentences(startChar, endChar);

            System.out.println("\nОброблений текст:");
            System.out.println(text);

        } catch (Exception e) {
            System.out.println("Виникла помилка: " + e.getMessage());
        }
    }
}

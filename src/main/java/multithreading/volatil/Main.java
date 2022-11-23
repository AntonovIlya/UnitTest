package multithreading.volatil;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {

    public static AtomicInteger[] counters = new AtomicInteger[]{
            new AtomicInteger(0),
            new AtomicInteger(0),
            new AtomicInteger(0)};

    public static Random random = new Random();
    public static String letters = "abc";

    public static void main(String[] args) {
        String[] texts = new String[100_000];

        for (int i = 3; i < 6; i++) {
            AtomicInteger sum = new AtomicInteger(0);
            for (int j = 0; j < texts.length; j++) {
                texts[j] = generateText(letters, i + random.nextInt(3));
            }

            Thread thread1 = new Thread(() -> {
                for (String s : texts) {
                    if (s.equals(new StringBuilder(s).reverse().toString())) counters[0].incrementAndGet();
                }

            });
            thread1.start();

            Thread thread2 = new Thread(() -> {
                for (String s : texts) {
                    String letter = String.valueOf(s.charAt(0));
                    if (s.replace(letter, "").equals("")) counters[1].incrementAndGet();
                }
            });
            thread2.start();

            Thread thread3 = new Thread(() -> {
                for (String s : texts) {
                    String[] strings = s.split("");
                    boolean b = true;
                    for (int k = 1; k < strings.length; k++) {
                        if (letters.indexOf(strings[k]) < letters.indexOf(strings[k - 1])) {
                            b = false;
                            break;
                        }
                    }
                    if (b) counters[2].incrementAndGet();
                }
            });
            thread3.start();

            try {
                thread1.join();
                thread2.join();
                thread3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (AtomicInteger counter : counters) {
                sum.getAndAdd(counter.get());
                counter.set(0);
            }
            System.out.printf("Красивых слов с длиной %d: %s \n", i, sum);
        }
    }

    public static String generateText(String letters, int length) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }


}


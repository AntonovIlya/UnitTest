package patterns.behavioral.iterator;

import java.util.*;

public class Randoms implements Iterable<Integer> {

    protected final Random random = new Random();
    private List<Integer> list;
    private final int LIMIT = 100;

    public Randoms(int min, int max) {
        list = getList(min, max + 1);
    }

    private List<Integer> getList(int min, int max) {
        List<Integer> list = new ArrayList<>(LIMIT);
        for (int i = 0; i < LIMIT; i++) {
            list.add(random.nextInt(max - min) + min);
        }
        return list;
    }


    @Override
    public Iterator<Integer> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<Integer> {

        int cursor;

        @Override
        public boolean hasNext() {
            return cursor != LIMIT;
        }

        @Override
        public Integer next() {
            int i = cursor;
            if (i >= LIMIT)
                throw new NoSuchElementException();
            cursor = i + 1;
            return list.get(i);
        }
    }
}

package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;


public class AsIntStream implements IntStream {
    private final int[] array;

    private AsIntStream(int... values) {
        this.array = values.clone();
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    @Override
    public Double average() {
        checkIfEmpty();
        return (double) sum() / count();
    }

    @Override
    public Integer max() {
        checkIfEmpty();
        return reduce(Integer.MIN_VALUE, (x, y) -> {
            if (x > y) return x;
            return y;
        });
    }

    @Override
    public Integer min() {
        checkIfEmpty();
        return reduce(Integer.MAX_VALUE, (x, y) -> {
            if (x < y) return x;
            return y;
        });
    }

    @Override
    public long count() {
        int res = 0;
        for (int obj : array) {
            res++;
        }
        return res;
    }

    @Override
    public Integer sum() {
        checkIfEmpty();
        return reduce(0, (sum, x) -> sum += x);
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        int numEls = 0;
        for (int value : array) {
            if (predicate.test(value)) {
                numEls += 1;
            }
        }
        int[] newResArr = new int[numEls];
        int index = 0;
        for (int value : array) {
            if (predicate.test(value)) {
                newResArr[index] = value;
                index++;
            }
        }
        return new AsIntStream(newResArr);
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int value : array) {
            action.accept(value);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        int[] resArr = new int[(int) count()];
        for (int i = 0; i < count(); i++) {
            resArr[i] = mapper.apply(array[i]);
        }
        return new AsIntStream(resArr);
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        checkIfEmpty();
        IntStream firstApplyRes = func.applyAsIntStream(array[0]);
        int newSize = (int) (firstApplyRes.count() * count());
        int[] resArr = new int[newSize];

        int index = 0;
        for (int i = 0; i < count(); i++) {
            IntStream ithApplyRes = func.applyAsIntStream(array[i]);
            for (int value : ithApplyRes.toArray()) {
                resArr[index] = value;
                index++;
            }
        }
        return new AsIntStream(resArr);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int result = identity;
        for (int i = 0; i < count(); i++) {
            result = op.apply(result, array[i]);
        }
        return result;
    }

    @Override
    public int[] toArray() {
        return array.clone();
    }

    private void checkIfEmpty() {
        if (count() <= 0) {
            throw new IllegalArgumentException("AsIntStream is empty");
        }
    }
}

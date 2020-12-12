package ua.edu.ucu;

import ua.edu.ucu.stream.*;
import org.junit.Test;

import static org.junit.Assert.*;


public class AsIntStreamTest {
    private final double DELTA = 0.00001;


    @Test(expected = IllegalArgumentException.class)
    public void testAverageEmpty() {
        int[] arr = {};
        IntStream stream = AsIntStream.of(arr);
        stream.average();
    }

    @Test
    public void testAverageOneEl() {
        int[] arr = {-10};
        IntStream stream = AsIntStream.of(arr);
        double actRes = stream.average();
        double expRes = -10;
        assertEquals(actRes, expRes, DELTA);
    }

    @Test
    public void testAverageFewEls() {
        int[] arr = {-10, 0, 10};
        IntStream stream = AsIntStream.of(arr);
        double actRes = stream.average();
        double expRes = 0;
        assertEquals(actRes, expRes, DELTA);
    }

    @Test
    public void testAverageFewElsAverageNotInt() {
        int[] arr = {3, 5, 2, 3, 0, 0};
        IntStream stream = AsIntStream.of(arr);
        double actRes = stream.average();
        double expRes = 2.16666667;
        assertEquals(actRes, expRes, DELTA);
    }

    @Test
    public void testAverageAllElsLowerZero() {
        int[] arr = {-10, -90, -1, -22};
        IntStream stream = AsIntStream.of(arr);
        double actRes = stream.average();
        double expRes = -30.75;
        assertEquals(actRes, expRes, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxEmpty() {
        int[] arr = {};
        IntStream stream = AsIntStream.of(arr);
        stream.max();
    }

    @Test
    public void testMaxOneEl() {
        int[] arr = {-10};
        IntStream stream = AsIntStream.of(arr);
        int actRes = stream.max();
        int expRes = -10;
        assertEquals(actRes, expRes);
    }

    @Test
    public void testMaxFewEls() {
        int[] arr = {-10, 1, 0, 12, 12, -5};
        IntStream stream = AsIntStream.of(arr);
        int actRes = stream.max();
        int expRes = 12;
        assertEquals(actRes, expRes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMInEmpty() {
        int[] arr = {};
        IntStream stream = AsIntStream.of(arr);
        stream.min();
    }

    @Test
    public void testMinFewEls() {
        int[] arr = {-10, 1, 0, 12, 12, -5};
        IntStream stream = AsIntStream.of(arr);
        int actRes = stream.min();
        int expRes = -10;
        assertEquals(actRes, expRes);
    }

    @Test
    public void testMinOneEl() {
        int[] arr = {55};
        IntStream stream = AsIntStream.of(arr);
        int actRes = stream.min();
        int expRes = 55;
        assertEquals(actRes, expRes);
    }

    @Test
    public void testCountNoEls() {
        int[] arr = {};
        IntStream stream = AsIntStream.of(arr);
        long actRes = stream.count();
        long expRes = 0;
        assertEquals(actRes, expRes, DELTA);
    }

    @Test
    public void testCountFewEls() {
        int[] arr = {1, 2, 3, 4, 6};
        IntStream stream = AsIntStream.of(arr);
        long actRes = stream.count();
        long expRes = 5;
        assertEquals(actRes, expRes, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSumEmpty() {
        int[] arr = {};
        IntStream stream = AsIntStream.of(arr);
        stream.sum();
    }

    @Test
    public void testSumOneEl() {
        int[] arr = {6};
        IntStream stream = AsIntStream.of(arr);
        int actRes = stream.sum();
        int expRes = 6;
        assertEquals(actRes, expRes);
    }

    @Test
    public void testSumFewEls() {
        int[] arr = {6, 10, -4, 22, -23};
        IntStream stream = AsIntStream.of(arr);
        int actRes = stream.sum();
        int expRes = 11;
        assertEquals(actRes, expRes);
    }

    @Test
    public void testToArrayNoEls() {
        int[] arr = {};
        IntStream stream = AsIntStream.of(arr);
        int[] actRes = stream.toArray();
        int[] expRes = {};
        assertArrayEquals(actRes, expRes);
    }

    @Test
    public void testToArrayFewEls() {
        int[] arr = {1, 2, 3, -1, -10};
        IntStream stream = AsIntStream.of(arr);
        int[] actRes = stream.toArray();
        int[] expRes = {1, 2, 3, -1, -10};
        assertArrayEquals(actRes, expRes);
    }

    @Test
    public void testToArrayChangeStartingArray() {
        int[] arr = {1, 2, 3};
        IntStream stream = AsIntStream.of(arr);
        arr[0] = 300;
        int[] actRes = stream.toArray();
        int[] expRes = {1, 2, 3};
        assertArrayEquals(actRes, expRes);
    }

    @Test
    public void testForEachEmpty() {
        int[] arr = {};
        IntStream stream = AsIntStream.of(arr);
        StringBuilder res = new StringBuilder();
        stream.forEach(x -> res.append(x));
        String expRes = "";
        String actRes = res.toString();
        assertEquals(actRes, expRes);

        int[] expResArr = {};
        int[] actResArr = stream.toArray();
        assertArrayEquals(expResArr, actResArr);
    }

    @Test
    public void testForEachAddingToStringNotEmpty() {
        int[] arr = {1, 2, 3, -1};
        IntStream stream = AsIntStream.of(arr);
        StringBuilder res = new StringBuilder();
        stream.forEach(x -> res.append(x));
        String expRes = "123-1";
        String actRes = res.toString();
        assertEquals(actRes, expRes);

        int[] expResArr = {1, 2, 3, -1};
        int[] actResArr = stream.toArray();
        assertArrayEquals(expResArr, actResArr);
    }

    @Test
    public void testFilterEmpty() {
        int[] arr = {};
        IntStream stream = AsIntStream.of(arr);
        IntStream newStream = stream.filter(x -> x > 0);

        int[] expResArr = {};
        int[] actResArr = newStream.toArray();
        assertArrayEquals(expResArr, actResArr);
        assertArrayEquals(expResArr, stream.toArray());
    }

    @Test
    public void testFilterEqualsValue() {
        int[] arr = {1, 2, 3, 2, 3, -2, -2};
        IntStream stream = AsIntStream.of(arr);
        IntStream newStream = stream.filter(x -> x == -2);

        int[] expResArr = {-2, -2};
        int[] actResArr = newStream.toArray();
        assertArrayEquals(expResArr, actResArr);
        assertArrayEquals(arr, stream.toArray());
    }

    @Test
    public void testMapEmpty() {
        int[] arr = {};
        IntStream stream = AsIntStream.of(arr);
        IntStream newStream = stream.map(x -> x * x);

        int[] expResArr = {};
        int[] actResArr = newStream.toArray();
        assertArrayEquals(expResArr, actResArr);
        assertArrayEquals(expResArr, stream.toArray());
    }

    @Test
    public void testMap() {
        int[] arr = {1, 3, -2, 0};
        IntStream stream = AsIntStream.of(arr);
        IntStream newStream = stream.map(x -> x * x + 1);

        int[] expResArr = {2, 10, 5, 1};
        int[] actResArr = newStream.toArray();
        assertArrayEquals(expResArr, actResArr);
        assertArrayEquals(arr, stream.toArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFlatMapEmpty() {
        int[] arr = {};
        IntStream stream = AsIntStream.of(arr);
        stream.flatMap(x -> AsIntStream.of(x - 1, x, x + 1));
    }

    @Test
    public void testReduceEmpty() {
        int[] arr = {};
        IntStream stream = AsIntStream.of(arr);
        int actRes = stream.reduce(0, (sum, x) -> sum += x);

        int expRes = 0;
        assertEquals(expRes, actRes);
        assertArrayEquals(arr, stream.toArray());
    }
}

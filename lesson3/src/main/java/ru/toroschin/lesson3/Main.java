package ru.toroschin.lesson3;

public class Main {
    public static void main(String[] args) {
        int[] array = IntegerArray.getArray(200000000);

        long startTime = System.currentTimeMillis();
        int element = SearchMissedNumber.searchLogN(array);
        endTime(startTime);
        System.out.println("Пропущено число: " + element);

        startTime = System.currentTimeMillis();
        element = SearchMissedNumber.searchN(array);
        endTime(startTime);
        System.out.println("Пропущено число: " + element);

//        System.out.println(Arrays.toString(array));
    }

    private static void endTime(long startTime) {
        long endTime = System.currentTimeMillis();
        System.out.printf("Время начала %d \nВремя окончания %d%n", startTime, endTime);
        System.out.println("Потрачено времени: " + (endTime - startTime));
    }
}

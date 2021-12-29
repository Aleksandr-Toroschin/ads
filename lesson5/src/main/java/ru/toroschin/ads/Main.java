package ru.toroschin.ads;

public class Main {
    public static void main(String[] args) {
        // Задание 1
        int number = 2;
        int degree = -4;
        System.out.println(Exponential.get(number, degree));

        // Задание 2
        BackPack backPack = new BackPack();
        backPack.addThing(new Thing("Часы", 100, 1000));
        backPack.addThing(new Thing("Телефон", 200, 10000));
        backPack.addThing(new Thing("Будильник", 500, 5000));
        backPack.addThing(new Thing("Шампунь", 400, 300));

        BackPack bestKit = new SearchBestKit().getBestKit(700, backPack);

        System.out.println(bestKit.toString());
    }

}

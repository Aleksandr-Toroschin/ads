package ru.toroschin.ads;

import java.util.ArrayList;
import java.util.List;

public class BackPack {
    private List<Thing> things;
    private int sumWeight;
    private int sumPrice;

    public BackPack() {
        things = new ArrayList<>();
        sumWeight = 0;
        sumPrice = 0;
    }

    public BackPack(List<Thing> things) {
        this.things = things;
        refresh();
    }

    public int getSumWeight() {
        return sumWeight;
    }

    public int getSumPrice() {
        return sumPrice;
    }

    public void addThing(Thing thing) {
        things.add(thing);
        refresh();
    }

    public void addAll(List<Thing> things) {
        this.things.addAll(things);
        refresh();
    }

    public List<Thing> getPart(int length) {
        List<Thing> result = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            result.add(things.get(i));
        }
        return result;
    }

    public List<Thing> getThings() {
        return things;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Набор: \nОбщий вес: ");
        sb.append(sumWeight);
        sb.append("\nОбщая цена:");
        sb.append(sumPrice);
        sb.append("\n[");
        for (Thing thing : things) {
            sb.append(thing.toString());
            sb.append("; ");
        }
        sb.append("]");
        return sb.toString();
    }

    private void refresh() {
        int sumWeight = 0;
        int sumPrice = 0;
        for (Thing thing : things) {
            sumWeight += thing.getWeight();
            sumPrice += thing.getPrice();
        }
        this.sumWeight = sumWeight;
        this.sumPrice = sumPrice;
    }

}

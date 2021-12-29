package ru.toroschin.ads;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SearchBestKit {
    private BackPack result = new BackPack();
    private Set<List<Thing>> kits = new LinkedHashSet<>();

    public BackPack getBestKit(int maxWeight, BackPack backPack) {
        if (backPack.getSumWeight() <= maxWeight) {
            return backPack;
        }

        findKits(backPack, backPack.getThings().size());
        result = new BackPack();
        for (List<Thing> kit : kits) {
            BackPack next = new BackPack(kit);
            if ((next.getSumWeight() <= maxWeight) && (next.getSumPrice() > result.getSumPrice())) {
                result = next;
            }
        }

        return result;
    }

    public void findKits(BackPack backPack, int size) {
        if (backPack.getThings().size() == 1) {
            return;
        }
        BackPack newBackPack;
        for (int i = 0; i < size; i++) {
            newBackPack = new BackPack(backPack.getPart(size - 1));
            kits.add(newBackPack.getThings());
            findKits(newBackPack, size - 1);
            backPack = rotate(backPack);
        }
    }

    private BackPack rotate(BackPack backPack) {
        List<Thing> newThings = new ArrayList<>();
        for (int i = 1; i < backPack.getThings().size(); i++) {
            newThings.add(backPack.getThings().get(i));
        }
        newThings.add(backPack.getThings().get(0));
        return new BackPack(newThings);
    }
}

package ru.toroschin.ads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Thing {
    private String name;
    private int weight;
    private int price;

    public String toString() {
        return String.format("%s (%d г) %d руб.", name, weight, price);
    }
}

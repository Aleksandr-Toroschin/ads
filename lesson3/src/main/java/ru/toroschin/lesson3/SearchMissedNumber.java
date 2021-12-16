package ru.toroschin.lesson3;

public class SearchMissedNumber {

    /**
     * Поиск со сложностью O(log n)
     * @param array - исходный отсортированный массив
     * @return пропущенный element
     */

    public static int searchLogN(int[] array) {
        if (array.length == 0) {
            return 1;
        }
        int startIndex = 0;
        int endIndex = array.length - 1;
        int index;
        while (startIndex < endIndex) {
            index = startIndex + (endIndex - startIndex) / 2;
            if (array[index] == (index + 1)) {
                startIndex = index + 1;
            } else {
                endIndex = index;
            }
            if (startIndex == endIndex) {
                return array[startIndex] - 1;
            }
        }
        return 0;
    }

    /**
     * Поиск со сложностью O(n)
     * @param array - исходный отсортированный массив
     * @return пропущенный element
     */
    public static int searchN(int[] array) {
        int element = 1;
        for (int i = 1; i < array.length; i++) {
            if (element + 1 != array[i]) {
                element ++;
                break;
            }
            element = array[i];
        }
        return element;
    }
}

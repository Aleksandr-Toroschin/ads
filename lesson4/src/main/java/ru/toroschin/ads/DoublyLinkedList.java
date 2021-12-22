package ru.toroschin.ads;

public interface DoublyLinkedList<E>  extends MyLinkedList<E> {

    void insertLast(E value);

    E getLast();
}

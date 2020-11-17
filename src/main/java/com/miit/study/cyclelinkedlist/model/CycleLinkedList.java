package com.miit.study.cyclelinkedlist.model;

import java.util.Collection;

/**
 * The structure interface which contains all operations,
 * that was be given by study's task.
 * @param <Value> - some generic value.
 */
public interface CycleLinkedList<Value> extends Iterable<Value>, Cloneable {

    String ELEMENT_NOT_FOUND_ERR_MSG = "Element not found.";
    String INCORRECT_INPUT_ERR_MSG = "Incorrect index output.";

    /**
     * Method for cleaning and removing all collection entirely.
     */
    void clear();

    /**
     * The count elements of current collection.
     * @return - the size of collection, 0 if current collection is empty.
     */
    int size();

    /**
     * The empty status of collection.
     * @return - true is collection is empty or false if not.
     */
    boolean isEmpty();

    /**
     * Add a single value in collection.
     * @param value - generic value.
     * @return - true if it's add successfully, false if not.
     */
    boolean add(Value value);

    /**
     * Add a single value at the given index.
     * @param index - current position of element.
     * @param value - generic value.
     */
    void addAt(int index, Value value);

    /**
     * Add a single value to first position of the collection.
     * @param value - generic value.
     */
    void addFirst(Value value);

    /**
     * Add a single value to last position of the collection.
     * @param value - generic value.
     */
    void addLast(Value value);

    /**
     * Add a more that one values to current collection.
     * @param list - another list of values.
     * @return - true if it's add successfully, false if not.
     */
    boolean addAll(Collection<? extends Value> list);

    /**
     * Position of first match value in this collection.
     * @param value - generic value.
     * @return - position of given value, -1 if value doesn't exists.
     */
    int indexOf(Value value);

    /**
     * Position of last match value in this collection.
     * @param value - generic value.
     * @return - position of given value, -1 if value doesn't exists.
     */
    int lastIndexOf(Value value);

    /**
     * Get value by index.
     * @param index - position of generic value in current collection.
     * @return - generic value.
     */
    Value get(int index);

    /**
     * Get first value in collection.
     * @return - generic value.
     */
    Value getFirst();

    /**
     * Get first value in collection.
     * @return - generic value.
     */
    Value getLast();

    /**
     * Remove first value in collection.
     * @return - generic value, that was be removed.
     */
    Value removeFirst();

    /**
     * Remove last value in collection.
     * @return - generic value, that was be removed.
     */
    Value removeLast();

    /**
     * Remove value by current position in collection.
     * @return - generic value, that was be removed.
     */
    Value removeAt(int index);

    /**
     * Remove value by current value equals method.
     * @param value - generic value, that was be removed.
     * @return - true if value was be removed, false if not.
     */
    boolean remove(Value value);

    /**
     * Method checks that given generic value is contains at current collection.
     * @param value - generic value.
     * @return - true if contains, false if not.
     */
    boolean contains(Value value);

    /**
     * Converts all elements of collection to array.
     * @return - array of generic values.
     */
    Object[] toArray();
}

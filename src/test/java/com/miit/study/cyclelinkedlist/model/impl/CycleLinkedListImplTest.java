package com.miit.study.cyclelinkedlist.model.impl;

import com.miit.study.cyclelinkedlist.model.CycleLinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CycleLinkedListImplTest {


    @Test
    @DisplayName("Collection is empty test")
    void isEmpty() {
        final CycleLinkedList<String> list = new CycleLinkedListImpl<String>();
        assertTrue(list.isEmpty());

        list.add("value");
        assertFalse(list.isEmpty());
    }

    @Test
    @DisplayName("Clear all elements in collection test")
    void clear() {
        final CycleLinkedList<String> list = new CycleLinkedListImpl<String>();
        list.add("value1");
        list.add("value2");
        list.add("value3");
        assertFalse(list.isEmpty());

        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    @DisplayName("Collection size test")
    void size() {
        final CycleLinkedList<String> list = new CycleLinkedListImpl<String>();
        assertEquals(0, list.size());

        list.add("value1");
        list.add("value2");
        list.add("value3");
        assertEquals(3, list.size());
    }

    @Test
    @DisplayName("Collection add element test")
    void add() {
        final CycleLinkedList<String> list = new CycleLinkedListImpl<String>();
        assertEquals(0, list.size());

        list.add("value");
        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("Collection add element at index test")
    void addAt() {
        final CycleLinkedList<String> list = new CycleLinkedListImpl<String>();
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            public void execute() throws Throwable {
                list.addAt(1, "error");
            }
        });

        list.add("value");
        assertDoesNotThrow(new Executable() {
            public void execute() throws Throwable {
                list.addAt(0, "notError");
            }
        });
        assertEquals("notError", list.get(0));
    }

    @Test
    @DisplayName("Collection add first element test")
    void addFirst() {
        final CycleLinkedList<String> list = new CycleLinkedListImpl<String>();
        list.add("value1");
        list.add("value2");
        list.add("value3");
        list.addFirst("first");
        assertEquals("first", list.get(0));
    }

    @Test
    @DisplayName("Collection add last element test")
    void addLast() {
        final CycleLinkedList<String> list = new CycleLinkedListImpl<String>();
        list.add("value1");
        list.add("value2");
        list.add("value3");
        list.addLast("last");
        assertEquals("last", list.get(3));
    }

    @Test
    @DisplayName("Collection add all elements test")
    void addAll() {
        final CycleLinkedList<String> list = new CycleLinkedListImpl<String>();
        final List<String> anotherList = new ArrayList<String>();
        anotherList.add("one");
        anotherList.add("two");
        anotherList.add("three");
        list.addAll(anotherList);

        assertEquals(anotherList.size(), list.size());
    }

    @Test
    @DisplayName("Collection index of element test")
    void indexOf() {
        final CycleLinkedList<String> list = new CycleLinkedListImpl<String>();
        list.add("value1");
        list.add("same");
        list.add("same");

        assertEquals(0, list.indexOf("value1"));
        assertEquals(1, list.indexOf("same"));
    }

    @Test
    @DisplayName("Collection last index of element test")
    void lastIndexOf() {
        final CycleLinkedList<String> list = new CycleLinkedListImpl<String>();
        list.add("value1");
        list.add("same");
        list.add("same");

        assertEquals(0, list.lastIndexOf("value1"));
        assertEquals(2, list.lastIndexOf("same"));
    }

    @Test
    @DisplayName("Collection get element test")
    void get() {
        final CycleLinkedList<String> list = new CycleLinkedListImpl<String>();
        list.add("value1");
        list.add("value2");
        list.add("value3");

        assertEquals("value1", list.get(0));
        assertEquals("value2", list.get(1));
        assertEquals("value3", list.get(2));
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            public void execute() throws Throwable {
                list.get(5);
            }
        });
    }

    @Test
    @DisplayName("Collection get first element test")
    void getFirst() {
        final CycleLinkedList<String> list = new CycleLinkedListImpl<String>();
        list.add("value1");
        list.add("value2");
        list.add("value3");

        assertEquals("value1", list.getFirst());
    }

    @Test
    @DisplayName("Collection get last element test")
    void getLast() {
        final CycleLinkedList<String> list = new CycleLinkedListImpl<String>();
        list.add("value1");
        list.add("value2");
        list.add("value3");

        assertEquals("value3", list.getLast());
    }

    @Test
    @DisplayName("Collection remove first element test")
    void removeFirst() {
        final CycleLinkedList<String> list = new CycleLinkedListImpl<String>();
        list.add("value1");
        list.add("value2");
        list.add("value3");

        list.removeFirst();
        assertEquals("value2", list.getFirst());
    }

    @Test
    @DisplayName("Collection remove last element test")
    void removeLast() {
        final CycleLinkedList<String> list = new CycleLinkedListImpl<String>();
        list.add("value1");
        list.add("value2");
        list.add("value3");

        list.removeLast();
        assertEquals("value2", list.getLast());
    }

    @Test
    @DisplayName("Collection remove element by index test")
    void removeAt() {
        final CycleLinkedList<String> list = new CycleLinkedListImpl<String>();
        list.add("value1");
        list.add("value2");
        list.add("value3");

        list.removeAt(1);
        assertEquals("value3", list.get(1));
        assertThrows(IndexOutOfBoundsException.class, new Executable() {
            public void execute() throws Throwable {
                list.removeAt(5);
            }
        });
    }

    @Test
    @DisplayName("Collection remove element by value test")
    void remove() {
        final CycleLinkedList<String> list = new CycleLinkedListImpl<String>();
        list.add("value1");
        list.add("value2");
        list.add("value3");

        list.remove("value1");
        assertEquals("value2", list.getFirst());
    }

    @Test
    @DisplayName("Collection contains element by value test")
    void contains() {
        final CycleLinkedList<String> list = new CycleLinkedListImpl<String>();
        list.add("value1");
        list.add("value2");
        list.add("value3");

        assertTrue(list.contains("value2"));
        list.remove("value2");
        assertFalse(list.contains("value2"));
    }

    @Test
    @DisplayName("Collection get iterator test")
    void iterator() {
        final CycleLinkedList<String> list = new CycleLinkedListImpl<String>();
        list.add("value1");
        list.add("value2");
        list.add("value3");

        int i = 0;
        for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
            assertEquals(list.get(i++), it.next());
        }
    }
}
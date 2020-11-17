package com.miit.study.cyclelinkedlist;

import com.miit.study.cyclelinkedlist.model.CycleLinkedList;
import com.miit.study.cyclelinkedlist.model.impl.CycleLinkedListImpl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {

    /**
     * Testing collection's methods
     * @param args
     */
    public static void main(String[] args) {
        CycleLinkedList<String> model = new CycleLinkedListImpl<String>();
        model.add("First");
        model.addFirst("New_First");
        model.addAt(1, "Middle");

        Set<String> collection = new HashSet<String>();
        collection.add("Collect_1");
        collection.add("Collect_2");
        collection.add("Collect_3");

        model.addAll(collection);
        model.addLast("Last");

        printAll(model);
        test(model, "New_First");
        model.removeFirst();
        printMessage("Remove first element:");
        printAll(model);
        test(model, "New_First");
        model.removeLast();
        printMessage("Remove last element:");
        printAll(model);
        test(model, "Last");

        printMessage("Index of 'Collect_2': " + model.indexOf("Collect_2"));
        printMessage("Index of 'Collect_1': " + model.lastIndexOf("Collect_1"));
        printMessage("Get first: " + model.getFirst());
        printMessage("Get last: " + model.getLast());
        printMessage("Get at index 3: " + model.get(3));

        printMessage("Check Iterator:");
        for (Iterator<String> it = model.iterator(); it.hasNext(); ) {
            System.out.println(it.next());
        }

        printMessage("Remove all:");
        model.clear();
        printAll(model);
        test(model, "Last");
    }

    private static void printAll(CycleLinkedList<String> model) {
        System.out.println("> Cycle Linked List: " + model.toString());
    }

    private static void printMessage(String msg) {
        System.out.println("> " + msg);
    }

    private static void test(CycleLinkedList<String> model, String value) {
        System.out.println("> Is contains \""+ value + "\": " + model.contains(value) +
                "\n> Collection size: " + model.size() +
                "\n> Collection is Empty: " + model.isEmpty());
    }
}

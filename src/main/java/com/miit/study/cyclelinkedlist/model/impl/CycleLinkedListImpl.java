package com.miit.study.cyclelinkedlist.model.impl;

import com.miit.study.cyclelinkedlist.model.CycleLinkedList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The entire implementation of structure interface.
 * @param <Value>
 */
public class CycleLinkedListImpl<Value> implements CycleLinkedList<Value> {

    private CycleNode first;
    private CycleNode last;
    private int size;

    public CycleLinkedListImpl() {
    }

    public CycleLinkedListImpl(Collection<? extends Value> values) {
        this.addAll(values);
    }

    public void clear() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean add(Value value) {
        this.addLast(value);
        return true;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException(INCORRECT_INPUT_ERR_MSG);
        }
    }

    private CycleNode getCycleNodeByIndex(int index) {
        if (index == 0) {
            return this.first;
        }
        if (index == this.size - 1) {
            return this.last;
        }
        if (this.size / 2 > index) {
            for (CycleNode node = this.first.next; node != this.first; node = node.next, index--) {
                if (index == 0) {
                    return node;
                }
            }
        } else {
            index = this.size - index - 1;
            for (CycleNode node = this.last.prev; node != this.last; node = node.prev, index--) {
                if (index == 0) {
                    return node;
                }
            }
        }
        return null;
    }

    public void addAt(int index, Value value) {
        if (value != null) {
            if (index != 0) {
                this.checkIndex(index);
            }
            CycleNode node = this.getCycleNodeByIndex(index);
            if (node == null || node == this.first) {
                this.addFirst(value);
            } else if (node == this.last) {
                this.addLast(value);
            } else {
                final CycleNode addNode = new CycleNode(node, node.prev, value);
                node.prev = node.prev.next = addNode;
                this.size++;
            }
        }
    }

    public void addFirst(Value value) {
        if (value != null) {
            if (this.first == null) {
                this.first = new CycleNode(value);
                this.first.prev = this.first.next = this.first;
                this.last = this.first;
            } else {
                final CycleNode node = new CycleNode(this.first, this.last, value);
                this.last.next = this.first.prev = node;
                this.first = node;
            }
            this.size++;
        }
    }

    public void addLast(Value value) {
        if (value != null) {
            if (this.first == null) {
                this.addFirst(value);
            } else {
                final CycleNode node = new CycleNode(this.first, this.last, value);
                this.first.prev = this.last.next = node;
                this.last = node;
                this.size++;
            }
        }
    }

    public boolean addAll(Collection<? extends Value> values) {
        boolean result;
        if (result = values != null && !values.isEmpty()) {
            for (Value value : values) {
                result = result && this.add(value);
            }
        }
        return result;
    }

    public int indexOf(Value value) {
        if (value != null && !this.isEmpty()) {
            if (this.first.value.equals(value)) {
                return 0;
            } else {
                int count = 1;
                for (CycleNode node = this.first.next; node != this.first; node = node.next, count++) {
                    if (node.value.equals(value)) {
                        return count;
                    }
                }
            }
        }
        return -1;
    }

    public int lastIndexOf(Value value) {
        if (!this.isEmpty()) {
            if (this.last.value.equals(value)) {
                return this.size - 1;
            } else {
                int count = this.size - 2;
                for (CycleNode node = this.last.prev; node != this.last; node = node.prev, count--) {
                    if (node.value.equals(value)) {
                        return count;
                    }
                }
            }
        }
        return -1;
    }

    public Value get(int index) {
        this.checkIndex(index);
        return this.getCycleNodeByIndex(index).value;
    }

    public Value getFirst() {
        if (this.first == null) {
            throw new NoSuchElementException(ELEMENT_NOT_FOUND_ERR_MSG);
        }
        return this.first.value;
    }

    public Value getLast() {
        if (this.last == null) {
            throw new NoSuchElementException(ELEMENT_NOT_FOUND_ERR_MSG);
        }
        return this.last.value;
    }

    public Value removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException(ELEMENT_NOT_FOUND_ERR_MSG);
        }
        if (this.size == 1) {
            final Value old = this.first.value;
            this.clear();
            return old;
        } else {
            final Value old = this.first.value;
            CycleNode newFirst = this.first.next;
            newFirst.prev = this.last;
            this.last.next = newFirst;
            this.first = newFirst;
            this.size--;
            return old;
        }
    }

    public Value removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException(ELEMENT_NOT_FOUND_ERR_MSG);
        }
        if (this.size == 1) {
            final Value old = this.first.value;
            this.clear();
            return old;
        } else {
            final Value old = this.last.value;
            CycleNode newLast = this.last.prev;
            newLast.next = this.first;
            this.first.prev = newLast;
            this.last = newLast;
            this.size--;
            return old;
        }
    }

    public Value removeAt(int index) {
        this.checkIndex(index);
        if (index == 0) {
            return this.removeFirst();
        }
        if (index == this.size - 1) {
            return this.removeLast();
        } else {
            final CycleNode node = this.getCycleNodeByIndex(index);
            if (node != null) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                this.size--;
                return node.value;
            }
            return null;
        }
    }

    public boolean remove(Value value) {
        boolean result;
        if (result = value != null) {
            if (result = !this.isEmpty()) {
                if (result = this.first.value.equals(value)) {
                    this.removeFirst();
                } else {
                    CycleNode delete = null;
                    for (CycleNode node = this.first.next; node != this.first; node = node.next) {
                        if (node.value.equals(value)) {
                            delete = node;
                        }
                    }
                    if (result = delete != null) {
                        if (delete == this.last) {
                            this.removeLast();
                        } else {
                            delete.prev.next = delete.next;
                            delete.next.prev = delete.prev;
                            this.size--;
                        }
                    }
                }
            }
        }
        return result;
    }

    public Object[] toArray() {
        final Object[] values = new Object[this.size];
        if (this.size() > 0) {
            values[0] = this.first.value;
            int count = 1;
            for (CycleNode node = this.first.next; node != this.first; node = node.next, count++) {
                values[count] = node.value;
            }
        }
        return values;
    }

    public String toString() {
        return Arrays.toString(this.toArray());
    }

    public boolean contains(Value value) {
        return this.indexOf(value) != -1;
    }

    public Iterator<Value> iterator() {
        return new CycleIterator();
    }

    private class CycleIterator implements Iterator<Value> {
        private CycleNode cursor = first;
        private int count = 0;

        public boolean hasNext() {
            return count < size;
        }

        public Value next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException(ELEMENT_NOT_FOUND_ERR_MSG);
            }
            Value value = cursor.value;
            cursor = cursor.next;
            this.count++;
            return value;
        }

        public void remove() {
        }
    }

    private class CycleNode {
        private CycleNode next;
        private CycleNode prev;
        private Value value;

        CycleNode(CycleNode next, CycleNode prev, Value value) {
            this.next = next;
            this.prev = prev;
            this.value = value;
        }

        CycleNode(Value value) {
            this(null, null, value);
        }
    }
}

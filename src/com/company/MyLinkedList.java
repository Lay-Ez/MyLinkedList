package com.company;

import java.util.*;

public class MyLinkedList<T> implements List<T> {

    private int size;

    class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node last;

    public MyLinkedList() {
        clear();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.data == o) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        for (int i = 0; i < size(); i++) {
            array[i] = this.get(i);
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        Object[] array = new Object[size()];
        for (int i = 0; i < size(); i++) {
            array[i] = this.get(i);
        }
        return (T1[]) array;
    }

    @Override
    public boolean add(T t) {
        Node newNode = new Node(t);
        last.next = newNode;
        last = newNode;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int indexToRemove = indexOf(o);
        if (indexToRemove == -1) {
            return false;
        } else {
            remove(indexToRemove);
            return true;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!this.contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (Object o : c) {
            this.add((T) o);
        }
        return c.size() != 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        if (c.size() == 0) {
            return false;
        }
        if (index == size()) {
            //Adding to the end
            addAll(c);
        } else {
            int currentIndex = index;
            for (T e : c) {
                add(currentIndex, e);
                currentIndex++;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            this.remove(o);
        }
        return c.size() != 0;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean listChanged = false;
        Node currentNode = this.head;
        while (currentNode != null) {
            if (!c.contains(currentNode.data)) {
                this.remove(currentNode.data);
                listChanged = true;
            }
            currentNode = currentNode.next;
        }
        return listChanged;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.head = new Node(null);
        this.last = this.head;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Node currentNode = this.head.next;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.data;
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Node currentNode = this.head.next;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        T previousData = currentNode.data;
        currentNode.data = element;
        return previousData;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size()) {
            //Adding to the end of the list
            add(element);
        } else {
            Node currentNode = this.head.next;
            Node previousNode = null;
            for (int i = 0; i < index; i++) {
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
            Node newNode = new Node(element);
            if (previousNode != null) {
                previousNode.next = newNode;
            } else {
                //Adding to the 0th index
                this.head.next = newNode;
            }
            newNode.next = currentNode;
            size++;
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Node previousNode = null;
        Node currentNode = this.head;
        for (int i = 0; i <= index; i++) {
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        T data;
        if (currentNode == null) {
            //Removing end of the list
            data = previousNode.data;
            previousNode = null;
        } else {
            data = currentNode.data;
            previousNode.next = currentNode.next;
            currentNode = null;
        }
        size--;
        return data;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size(); i++) {
            if (this.get(i) == o) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = -1;
        for (int i = 0; i < size(); i++) {
            if (this.get(i) == o) {
                lastIndex = i;
            }
        }
        return lastIndex;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new MyLinkedListIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return new MyLinkedListIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex > toIndex || fromIndex < 0 || toIndex >= size()) {
            throw new IndexOutOfBoundsException();
        }
        MyLinkedList<T> newList = new MyLinkedList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            newList.add(this.get(i));
        }
        return newList;
    }

    private class MyLinkedListIterator implements ListIterator<T> {

        private int currentIndex;

        public MyLinkedListIterator() {
            this.currentIndex = 0;
        }

        public MyLinkedListIterator(int index) {
            this.currentIndex = index;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T element = get(currentIndex);
            currentIndex++;
            return element;
        }

        @Override
        public boolean hasPrevious() {
            return currentIndex >= 0;
        }

        @Override
        public T previous() {
            if (hasPrevious()) {
                currentIndex--;
                return get(currentIndex);
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public int nextIndex() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            } else {
                return currentIndex + 1;
            }
        }

        @Override
        public int previousIndex() {
            if (!hasPrevious()) {
                throw new IndexOutOfBoundsException();
            } else {
                return currentIndex - 1;
            }
        }

        @Override
        public void remove() {
            MyLinkedList.this.remove(currentIndex);
            currentIndex--;
        }

        @Override
        public void set(T t) {
            MyLinkedList.this.set(currentIndex, t);
        }

        @Override
        public void add(T t) {
            MyLinkedList.this.add(currentIndex, t);
        }
    }
}

package sort.heapsort;

import sap.Comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyHeap<E extends Comparable<E>> {
    private List<E> elements = new ArrayList<>();

    public MyHeap(List<E> elements) {
        this.elements = elements;
    }

    public void add(E e) {
        elements.add(e);
        var elementIndex = elements.size() - 1;
        while (elementIndex != 0 && !isCorrectChild(elementIndex)) {
            var parentIndex = parentIndex(elementIndex);
            Collections.swap(elements, parentIndex, elementIndex);
            elementIndex = parentIndex;
        }
    }

    public void pop(E e) throws IllegalAccessException {
        if (elements.size() == 0) {
            throw new IllegalAccessException("Empty Heap!");
        }
        E element = elements.get(0);

        int lastIndex = elements.size() - 1;
        E lastElement = elements.get(lastIndex);

        Collections.swap(elements, 0, lastIndex);
        elements.remove(lastIndex);

        var elementIndex = 0;
        while (!isLeaf(elementIndex) && !isCorrectParent(elementIndex)) {
            var smallerChlildIndex = getSmallerChildIndex(elementIndex);


        }


    }

    private int getSmallerChildIndex(int elementIndex) {
        var leftChildIndex = leftChildIndex(elementIndex);
        var rightChildIndex = rightChildIndex(elementIndex);

        if(rightChildIndex <= elements.size() - 1){
            return elements.get(leftChildIndex).compareTo(elements.get(rightChildIndex)) <= 0
                    ? leftChildIndex : rightChildIndex;
        } else{ // no left child
            return leftChildIndex;
        }
    }

    // check if all the child of this node is larger or equal to this node
    // if not swap with the smaller node position
    private boolean isCorrectParent(int elementIndex) {
        var leftChildIndex = leftChildIndex(elementIndex);
        var rightChildIndex = rightChildIndex(elementIndex);
        boolean rightValidation;
        boolean leftValidation = elements.get(elementIndex).compareTo(elements.get(leftChildIndex)) <= 0;

        if(rightChildIndex <= elements.size() - 1){
            rightValidation = elements.get(elementIndex).compareTo(elements.get(rightChildIndex)) <= 0;
            return rightValidation && leftValidation;
        }

        return leftValidation;
    }

    private boolean isLeaf(int elementIndex) {
        var largestIndex = elements.size() - 1;

        return leftChildIndex(elementIndex) > largestIndex &&
                rightChildIndex(elementIndex) > largestIndex;
    }


    // check the parent is less or equal to this node
    private boolean isCorrectChild(int elementIndex) {
        var parentIndex = parentIndex(elementIndex);
        return elements.get(parentIndex).compareTo(elements.get(elementIndex)) <= 0;
    }


    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    private int leftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int rightChildIndex(int index) {
        return index * 2 + 1;
    }


}

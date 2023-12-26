package tt.blue;

import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedianFromDataStream295 {
    // max heap for storing the first half elements
    // min heap for soting another half elements
    // add function: compare the num with the top of the heap to decide which heap
    // need to be added, then balance the heap by comparing these two heap size
    // remove function: same as add logic, just need to change the add to poll
    // find med function: if the total element in these two heap is equal, get the
    // top of the max and min, adding them together, then divide by 2
    // if is odd get the larger size heap top then return
    private PriorityQueue<Integer> maxHeap; // Lower half
    private PriorityQueue<Integer> minHeap; // Upper half

    public FindMedianFromDataStream295() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // Max heap
        minHeap = new PriorityQueue<>(); // Min heap
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        // Rebalance the heaps, the size difference from 1 is acceptable, the difference indicate which heap holds the median
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    public void removeNum(int num) {
        if (!maxHeap.isEmpty() && num <= maxHeap.peek()) {
            maxHeap.remove(num);
        } else if (!minHeap.isEmpty()) {
            minHeap.remove(num);
        }

        // Rebalance the heaps, the size difference from 1 is acceptable, the difference indicate which heap holds the median
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        }
        return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }

    public static void main(String[] args) {
        FindMedianFromDataStream295 mf = new FindMedianFromDataStream295();
        mf.addNum(8); // Add 8
        mf.addNum(4); // Add 4
        double median1 = mf.findMedian(); // findMedian -> 6.0
        mf.addNum(5); // Add 5
        mf.removeNum(4); // remove 4
        double median2 = mf.findMedian(); // findMedian -> 6.5

        System.out.println("Median after adding 8 and 4: " + median1);
        System.out.println("Median after adding 5 and removing 4: " + median2);
    }
}

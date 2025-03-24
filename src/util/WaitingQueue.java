package util;

import java.util.LinkedList;
import java.util.Queue;

public class WaitingQueue {
    private final int capacity = 3;
    private final Queue<Integer> queue = new LinkedList<>();

    public synchronized boolean tryToSit(int studentId) {
        if (queue.size() < capacity) {
            queue.add(studentId);
            System.out.println("Student " + studentId + " sat in the hallway. Chairs occupied: " + queue.size());
            return true;
        } else {
            System.out.println("No chairs available. Student " + studentId + " cannot wait.");
            return false;
        }
    }

    public synchronized Integer nextStudent() {
        return queue.poll();
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}

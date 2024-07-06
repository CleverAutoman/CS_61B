import org.junit.Test;

import javax.swing.*;
import java.util.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;


public class tests {

    @Test
    public void testInitialization() {
        myPriorityQueue<Integer> integers = new myPriorityQueue<>();
        myPriorityQueue<Integer> integers1 = new myPriorityQueue<>(20);
        myPriorityQueue<Integer> integers2 = new myPriorityQueue<>(30, (a, b) -> a - b);

    }

    @Test
    public void testAdd() {
        myPriorityQueue<String> q = new myPriorityQueue<>();
        q.add("a");
        q.add("b");

    }

    @Test
    public void testAddAndResize() {
        myPriorityQueue<Integer> q = new myPriorityQueue<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            q.add(i);
            list.add(i);
        }
        Collections.shuffle(list);
        System.out.println(list);

        myPriorityQueue<Integer> q2 = new myPriorityQueue<>();
        for (Integer i : list) {
            q2.add(i);
        }
        q2.clear();
        System.out.println(q2.peek());
        q.showHeap();
    }

    @Test
    public void testRemoveAndPoll() {
        myPriorityQueue<Integer> q = new myPriorityQueue<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            q.add(i);
            list.add(i);
        }
        for (int i = 0; i < 100; i++) {
            Integer poll = q.poll();
            q.showHeap();
            assertEquals(i, (int) poll);
        }
    }

    @Test
    public void testIterator() {
        myPriorityQueue<Integer> q = new myPriorityQueue<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            q.add(i);
            list.add(i);
        }
        Iterator<Integer> iterator = q.iterator();
        int index = 0;
        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
            assertEquals((int) iterator.next(), index++);
        }
    }

}

package service;

import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final HashMap<Integer, Node> history;
    private Node head;
    private Node tail;

    public InMemoryHistoryManager() {
        this.history = new HashMap<>();

    }

    public static class Node {
        Node prev;
        Task item;
        Node next;

        public Node(Node prev, Task item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    @Override
    public void add(Task task) {
        Node node = history.get(task.getId());
        removeNode(node);
        history.put(task.getId(), new Node(tail, task, null));
        linkLast(task);
    }

    @Override
    public void remove(int id) {
        Node node = history.get(id);
        removeNode(node);
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    private void linkLast(Task task) {
        final Node oldTail = tail;
        final Node newTail = new Node(oldTail, task, null);
        history.put(task.getId(), newTail);
        tail = newTail;
        if (oldTail == null) {
            head = newTail;
        } else {
            oldTail.next = newTail;
        }
    }

    private List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        Node node = head;
        while (node != null) {
            tasks.add(node.item);
            node = node.next;
        }
        return tasks;
    }

    private void removeNode(Node node) {
        if (node == null) {
            return;
        }
        Node nodePrev = node.prev;
        Node nodeNext = node.next;

        if (head == node && tail == node) { // Если node осталась одна
            head = null;
            tail = null;
        } else if (head == node) { // Если node первая
            head = nodeNext;
            head.prev = null;
        } else if (tail == node) { // Если node последняя
            tail = nodePrev;
            tail.next = null;
        } else { // Если node между head и tail
            nodePrev.next = nodeNext;
            nodeNext.prev = nodePrev;
        }
    }
}
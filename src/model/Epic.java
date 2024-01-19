package model;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtasks = new ArrayList<>();

    public Epic(String title, String description) {
        super(title, description);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id='" + getId() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status='" + getStatus() + '\'' + "}";
    }

    public ArrayList<Integer> getSubtasks() {
        return subtasks;
    }

    public void addSubtask(Integer subtaskId) {
        this.subtasks.add(subtaskId);
    }

    public void removeSubtask(Integer subtaskId) {
        this.subtasks.remove(subtaskId);
    }

    public void clearSubtasks() {
        this.subtasks.clear();
    }
}

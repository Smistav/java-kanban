package service;

import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private int uid = 0;
    private HashMap<Integer, Task> taskList;
    private HashMap<Integer, Subtask> subtaskList;
    private HashMap<Integer, Epic> epicList;

    public TaskManager() {
        this.taskList = new HashMap<>();
        this.subtaskList = new HashMap<>();
        this.epicList = new HashMap<>();
    }

    public int generateId() {
        return ++uid;
    }

    /* Методы Task*/
    public Task createTask(Task task) {
        task.setId(generateId());
        taskList.put(task.getId(), task);
        return task;
    }

    public void updateTask(Task task) {
        taskList.put(task.getId(), task);
    }

    public Task getTaskById(int id) {
        return taskList.get(id);
    }

    public void removeTaskById(int id) {
        taskList.remove(id);
    }

    public ArrayList<Task> printAllTasks() {
        return new ArrayList<>(taskList.values());
    }

    public void clearAllTasks() {
        taskList.clear();
    }

    /*Методы Subtask*/
    public Subtask createSubtask(Subtask subtask) {
        subtask.setId(generateId());
        subtaskList.put(subtask.getId(), subtask);
        Epic epic = epicList.get(subtask.getEpicId());
        epic.addSubtask(subtask.getId());
        epic.setStatus(calculateEpicStatus(epic));
        return subtask;
    }

    public Subtask getSubtaskById(int id) {
        return subtaskList.get(id);
    }

    public void updateSubtask(Subtask subtask) {
        Epic epic = epicList.get(subtask.getEpicId());
        epic.setStatus(calculateEpicStatus(epic));
        subtaskList.put(subtask.getId(), subtask);
    }

    public void removeSubtaskById(int id) {
        int epicId = subtaskList.get(id).getEpicId();
        Epic epic = epicList.get(epicId);
        subtaskList.remove(id);
        epic.removeSubtask(id);
        epic.setStatus(calculateEpicStatus(epic));
    }

    public void clearAllSubtasks() {
        subtaskList.clear();
    }

    public ArrayList<Subtask> printAllSubtasks() {
        return new ArrayList<>(subtaskList.values());
    }

    /*Методы Epic*/
    public Epic createEpic(Epic epic) {
        epic.setId(generateId());
        epicList.put(epic.getId(), epic);
        return epic;
    }

    public Epic getEpicById(int id) {
        return epicList.get(id);
    }

    public ArrayList<Subtask> getAllSubTaskByEpicId(int id) {
        if (epicList.get(id) == null) {
            return new ArrayList<>();
        }
        ArrayList<Subtask> subtasks = new ArrayList<>();
        for (Integer subtaskId : epicList.get(id).getSubtasks()) {
            subtasks.add(subtaskList.get(subtaskId));
        }
        return subtasks;
    }

    public void updateEpic(Epic epic) {
        Epic savedEpic = epicList.get(epic.getId());
        epic.setStatus(savedEpic.getStatus());
    }

    public void removeEpicById(int id) {
        for (Integer subtask : epicList.get(id).getSubtasks()) {
            subtaskList.remove(subtask);
        }
        epicList.remove(id);
    }

    public void clearAllEpics() {
        subtaskList.clear();
        epicList.clear();
    }

    public ArrayList<Epic> printAllEpics() {
        return new ArrayList<>(epicList.values());
    }

    private TaskStatus calculateEpicStatus(Epic epic) {

        ArrayList<Integer> subtasks = epic.getSubtasks();
        if (subtasks == null) {
            return TaskStatus.NEW;
        }
        int countNew = 0;
        int countDone = 0;
        for (Integer subtaskId : subtasks) {
            if (subtaskList.get(subtaskId).getStatus().equals(TaskStatus.NEW)) {
                countNew++;
            }
            if (subtaskList.get(subtaskId).getStatus().equals(TaskStatus.DONE)) {
                countDone++;
            }
        }
        if (countNew == subtasks.size()) {
            return TaskStatus.NEW;
        }
        if (countDone == subtasks.size()) {
            return TaskStatus.DONE;
        }
        return TaskStatus.IN_PROGRESS;
    }
}


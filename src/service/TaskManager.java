package service;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    List<Task> getHistory();

    /* Методы Task*/
    Task createTask(Task task);

    void updateTask(Task task);

    Task getTask(int id);

    void removeTask(int id);

    ArrayList<Task> getTasks();

    void clearTasks();

    /*Методы Subtask*/
    Subtask createSubtask(Subtask subtask);

    Subtask getSubtask(int id);

    void updateSubtask(Subtask subtask);

    void removeSubtask(int id);

    void clearSubtasks();

    ArrayList<Subtask> getSubtasks();

    /*Методы Epic*/
    Epic createEpic(Epic epic);

    Epic getEpic(int id);

    ArrayList<Subtask> getSubTaskByEpic(int id);

    void updateEpic(Epic epic);

    void removeEpic(int id);

    void clearEpics();

    ArrayList<Epic> getEpics();


}

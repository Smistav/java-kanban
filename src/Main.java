import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;
import service.Managers;
import service.TaskManager;

import java.util.ArrayList;

public class Main {
    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getEpics()) {
            System.out.println(epic);

            for (Task task : manager.getSubTaskByEpic(epic.getId())) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }

    public static void main(String[] args) {
        System.out.println("Поехали!");

        /* Операции по Task */
        TaskManager taskManager = Managers.getDefault();
        Task task1 = taskManager.createTask(new Task("делать", "что-то"));
        Task task2 = taskManager.createTask(new Task("не делать", "ничего"));
        System.out.println("Создание task1: " + task1);
        System.out.println("Создание task2: " + task2);

        System.out.println(taskManager.getTasks());

        Task taskFromManager = taskManager.getTask(task2.getId());
        System.out.println("Получить task2 по id: " + taskFromManager);
        taskFromManager.setStatus(TaskStatus.DONE);
        taskManager.updateTask(taskFromManager);
        System.out.println("Обновить task2: " + taskFromManager);
        taskManager.removeTask(taskFromManager.getId());
        System.out.println("Удалить task2: " + task2);

        /* Операции по Epic */
        Epic epic1 = taskManager.createEpic(new Epic("много делать", "что-то большое"));
        Epic epic2 = taskManager.createEpic(new Epic("не много делать", "ничего практически"));
        System.out.println("Создание epic1: " + epic1);
        System.out.println("Создание epic2: " + epic2);

        System.out.println(taskManager.getEpics());

        Epic epicFromManager = taskManager.getEpic(epic1.getId());
        System.out.println("Получить epic1 по id: " + epicFromManager);
        epicFromManager.setTitle("Новый Title");
        taskManager.updateEpic(epicFromManager);
        System.out.println("Обновить epic1: " + epicFromManager);
        taskManager.removeEpic(epicFromManager.getId());
        System.out.println("Удалить epic1: " + epic1);

        /* Операции по Subtask */
        Subtask subtask1 = taskManager.createSubtask(new Subtask(4, "подзадача1", "нереальная"));
        Subtask subtask2 = taskManager.createSubtask(new Subtask(4, "подзадача2", "реальная"));
        System.out.println("Создание Subtask1: " + subtask1);
        System.out.println("Создание Subtask2: " + subtask2);

        System.out.println(taskManager.getSubtasks());

        Subtask subtaskFromManager = taskManager.getSubtask(subtask1.getId());
        System.out.println("Получить Subtask1 по id: " + subtaskFromManager);
        subtaskFromManager.setTitle("Новый title");
        taskManager.updateSubtask(subtaskFromManager);
        System.out.println("Обновить Subtask1: " + subtaskFromManager);
        taskManager.removeSubtask(subtaskFromManager.getId());
        System.out.println("Удалить Subtask1: " + subtask1);

        Subtask subtaskFromManager2 = taskManager.getSubtask(subtask2.getId());
        System.out.println("Получить Subtask2 по id: " + subtaskFromManager2);
        subtaskFromManager2.setStatus(TaskStatus.DONE);
        taskManager.updateSubtask(subtaskFromManager2);
        System.out.println("Обновить Subtask2: " + subtaskFromManager2);

        /* Доп метод Epic*/
        Epic epicFromManager2 = taskManager.getEpic(epic2.getId());
        System.out.println("Получить epic2 по id: " + epicFromManager2);

        // проверка повтора вызова задачи для History Manager
        Subtask subtaskFromManager3 = taskManager.getSubtask(subtask2.getId());

        ArrayList<Subtask> subtasksEpic = taskManager.getSubTaskByEpic(epicFromManager2.getId());
        System.out.println("Получить все Subtasks Epic2: " + subtasksEpic);

        //
        printAllTasks(taskManager);

    }

}

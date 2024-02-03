package service;

import model.Epic;
import model.Subtask;
import model.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Task Manager")
class InMemoryTaskManagerTest {
    private final TaskManager taskManager = new InMemoryTaskManager(new InMemoryHistoryManager());

    @Test
    @DisplayName("должен создать Task")
    void shouldCreateTask() {
        Task task = new Task("Test createTask", "Test createTask description");
        final int taskId = taskManager.createTask(task).getId();
        final Task savedTask = taskManager.getTask(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = taskManager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }

    @Test
    @DisplayName("должен сравнить Task по ID")
    void shouldCompareTasksById() {
        Task task = new Task("Test createTask", "Test createTask description");
        final int taskId = taskManager.createTask(task).getId();
        assertEquals(task, taskManager.getTask(taskId), "Задачи не совпадают по одинаковому ID.");
    }

    @Test
    @DisplayName("должен создать Epic")
    void createEpic() {
        Epic epic = new Epic("Test createEpic", "Test createEpic description");
        final int epicId = taskManager.createEpic(epic).getId();
        final Task savedEpic = taskManager.getEpic(epicId);

        assertNotNull(savedEpic, "Эпик не найден.");
        assertEquals(epic, savedEpic, "Эпики не совпадают.");

        final List<Epic> epics = taskManager.getEpics();

        assertNotNull(epics, "Эпики не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество Эпиков.");
        assertEquals(epic, epics.get(0), "Эпики не совпадают.");
    }

    @Test
    @DisplayName("должен сравнить Epics по ID")
    void shouldCompareEpicsById() {
        Epic epic = new Epic("Test createEpic", "Test createEpic description");
        final int epicId = taskManager.createEpic(epic).getId();

        assertEquals(epic, taskManager.getEpic(epicId), "Эпики не совпадают по одинаковому ID.");
    }

    @Test
    @DisplayName("должен создать Subtask")
    void createSubTask() {
        Epic epic = new Epic("Test createEpic", "Test createEpic description");
        final int epicId = taskManager.createEpic(epic).getId();
        Subtask subtask = new Subtask(1, "Test createSubtask", "Test createSubtask description");
        final int subtaskId = taskManager.createSubtask(subtask).getId();
        final Task savedSubtask = taskManager.getSubtask(subtaskId);

        assertNotNull(savedSubtask, "ПодЗадача не найдена.");
        assertNotNull(taskManager.getEpic(epicId), "Эпик Подзадачи не найден.");
        assertEquals(subtask, savedSubtask, "ПодЗадачи не совпадают.");

        final List<Subtask> subtasks = taskManager.getSubtasks();

        assertNotNull(subtasks, "ПодЗадачи не возвращаются.");
        assertEquals(1, subtasks.size(), "Неверное количество ПодЗадач.");
        assertEquals(subtask, subtasks.get(0), "ПодЗадачи не совпадают.");
    }

    @Test
    @DisplayName("должен сравнить Subtask по ID")
    void shouldCompareSubtaskById() {
        Epic epic = new Epic("Test createEpic", "Test createEpic description");
        final int epicId = taskManager.createEpic(epic).getId();
        Subtask subtask = new Subtask(epicId, "Test createSubtask", "Test createSubtask description");
        final int subtaskId = taskManager.createSubtask(subtask).getId();

        assertEquals(subtask, taskManager.getSubtask(subtaskId), "ПодЗадачи не совпадают по одинаковому ID.");
    }
}
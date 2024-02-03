package service;

import model.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("History Manager")
class InMemoryHistoryManagerTest {
    private final HistoryManager historyManager = new InMemoryHistoryManager();

    @Test
    @DisplayName("должен добавить Task")
    void shouldAddTask() {
        Task task = new Task("Test addTask", "Test addTask description");
        historyManager.add(task);
        final List<Task> history = historyManager.get();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
    }

    @Test
    @DisplayName("Должен добавить Task и проверить что предыдущая сохранилась")
    void addNewTaskAndCheckPreviousTask() {
        Task task1 = new Task("Test addTask1", "Test addTask2 description");
        Task task2 = new Task("Test addTask2", "Test addTask2 description");
        historyManager.add(task1);
        historyManager.add(task2);
        final List<Task> history = historyManager.get();
        assertNotNull(history, "История не пустая.");
        assertEquals(2, history.size(), "Предыдущая история не сохранена.");
    }
}
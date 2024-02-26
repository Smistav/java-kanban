package service;

import model.Task;
import model.TaskStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("History Manager")
class InMemoryHistoryManagerTest {
    private final HistoryManager historyManager = new InMemoryHistoryManager();
    private final Task task1 = new Task(1, "Test addTask", "Test addTask description", TaskStatus.NEW);
    private final Task task2 = new Task(2, "Test addTask2", "Test addTask2 description", TaskStatus.NEW);
    private final Task task3 = new Task(3, "Test addTask3", "Test addTask3 description", TaskStatus.NEW);

    @Test
    @DisplayName("должен добавить Task")
    void shouldAddTask() {
        historyManager.add(task1);
        final List<Task> history = historyManager.getHistory();

        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
    }

    @Test
    @DisplayName("Должен добавить Task и проверить что предыдущая сохранилась")
    void shouldAddNewTaskAndCheckPreviousTask() {
        historyManager.add(task1);
        historyManager.add(task2);
        final List<Task> history = historyManager.getHistory();

        assertNotNull(history, "История не пустая.");
        assertEquals(2, history.size(), "Предыдущая история не сохранена.");
    }

    @Test
    @DisplayName("Должен удалить первую Task")
    void shouldRemoveFirstTask() {
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.remove(1);

        assertEquals(List.of(task2), historyManager.getHistory());
    }

    @Test
    @DisplayName("Должен удалить Task между началом и концом списка")
    void shouldRemoveTaskBetweenHeadAndTail() {
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task3);
        historyManager.remove(2);

        assertEquals(List.of(task1, task3), historyManager.getHistory());
    }

    @Test
    @DisplayName("Должен удалить последнюю Task")
    void shouldRemoveLastTask() {
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.remove(2);

        assertEquals(List.of(task1), historyManager.getHistory());
    }

    @Test
    @DisplayName("Должен ничего не делать, если нет такой Task")
    void shouldDoNothingIsIncorrectTask() {
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.remove(3);

        assertEquals(List.of(task1, task2), historyManager.getHistory());
    }

    @Test
    @DisplayName("Должен удалить первую Task при повторе")
    void shouldRemoveFirstTaskWhenRepeated() {
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task1);

        assertEquals(List.of(task2, task1), historyManager.getHistory());
    }
}
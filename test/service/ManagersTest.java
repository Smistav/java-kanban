package service;

import model.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Managers")
class ManagersTest {
    @Test
    @DisplayName("должен вернуть проинициализированные экземпляры менеджеров")
    void shouldReturnInitializedInstancesOfManagers() {
        TaskManager taskManager = Managers.getDefault();
        Task task = taskManager.createTask(new Task("Test createTask", "Test createTask description"));
        Task taskFromManager = taskManager.getTask(task.getId());

        assertEquals(taskFromManager.getTitle(), "Test createTask", "Task Manager nicht arbeiten");

        Task history = taskManager.getHistory().get(0);

        assertEquals(history.getTitle(), "Test createTask", "History Manager nicht arbeiten");
    }
}
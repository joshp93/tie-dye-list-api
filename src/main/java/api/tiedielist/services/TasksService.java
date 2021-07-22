package api.tiedielist.services;

import api.tiedielist.dtos.TaskDto;
import api.tiedielist.dtos.TaskListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class TasksService {

    private Map<UUID, TaskListDto> taskLists = new HashMap<>();
    private Map<UUID, TaskDto> tasks = new HashMap<>();
    private boolean showCompleted = false;

    public void showCompleted() {
        this.showCompleted = true;
    }

    public Collection<TaskListDto> getTaskLists() {
        return taskLists.values();
    }

    public TaskListDto insertTaskList(String title) {
        TaskListDto taskList = new TaskListDto(title);
        taskLists.put(taskList.getId(), taskList);
        return taskList;
    }

    public TaskListDto patchTaskList(UUID id, String title) {
        TaskListDto taskList = getTaskList(id);
        taskList.setTitle(title);
        taskLists.put(id, taskList);
        return taskList;
    }

    public TaskListDto getTaskList(UUID id) {
        return taskLists.get(id);
    }

    public TaskListDto deleteTaskList(UUID id) {
        return taskLists.remove(id);
    }

    public Collection<TaskDto> listTasks(String taskListId, Boolean showCompletedTasks) {
        return showCompleted ? tasks.values() : tasks.values().stream().filter(taskDto -> taskDto.isHidden() == false).collect(Collectors.toList());
    }

    public TaskDto getTask(UUID taskListId, UUID taskId) {
        return tasks.get(taskId);
    }

    public TaskDto insertTask(UUID tasklistId, UUID previousTaskId, TaskDto task) {
        TaskDto newTask = new TaskDto(task);
        if (previousTaskId != null) {
            TaskDto prevTask = this.tasks.get(previousTaskId);
            if (prevTask != null) {
                task.setPosition(prevTask.getPosition() + 1);
            }
        }
        task.setTaskListId(tasklistId);
        tasks.put(task.getId(), task);
        return tasks.get(task.getId());
    }

    public TaskDto patchTask(UUID taskId, TaskDto task) {
        tasks.put(taskId, task);
        return tasks.get(taskId);
    }

    public TaskDto completeTask(UUID taskListId, UUID taskId, TaskDto task) {
        TaskDto compTask = tasks.get(taskId);
        compTask.setCompleted(task.getCompleted());
        compTask.setHidden(!showCompleted);
        tasks.put(taskId, compTask);
        return tasks.get(taskId);
    }

    public TaskDto deleteTask(UUID taskListId, UUID taskId) {
        return tasks.remove(taskId);
    }
}

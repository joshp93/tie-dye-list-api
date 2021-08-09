package api.tiedielist.services;

import api.tiedielist.dtos.TaskDto;
import api.tiedielist.dtos.TaskListDto;
import api.tiedielist.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TasksService {

    @Autowired
    TasksRepository tasksRepository;

    private Map<UUID, TaskListDto> taskLists = new HashMap<>();
    private Map<UUID, TaskDto> tasks = new HashMap<>();
    private boolean showCompleted = false;

    public void showCompleted() {
        this.showCompleted = false;
    }

    public Collection<TaskListDto> getTaskLists() {
        return tasksRepository.getTaskLists();
    }

    public TaskListDto insertTaskList(String title) {
        TaskListDto taskList = new TaskListDto(title);
        return tasksRepository.insertTaskList(taskList);
    }

    public TaskListDto patchTaskList(TaskListDto taskList) {
        return tasksRepository.updateTaskList(taskList);
    }

    public TaskListDto getTaskList(UUID id) {
        return tasksRepository.getTaskList(id);
    }

    public boolean deleteTaskList(UUID id) {
        return tasksRepository.deleteTaskList(id);
    }

    public Collection<TaskDto> listTasks() {
        return showCompleted ? tasks.values() : tasks.values().stream().filter(taskDto -> taskDto.isHidden() == false).collect(Collectors.toList());
    }

    public TaskDto getTask(UUID taskId) {
        return tasksRepository.getTask(taskId);
    }

    public TaskDto insertTask(TaskDto task) {
        return tasksRepository.insertTask(task);
    }

    public TaskDto patchTask(TaskDto task) {
        return tasksRepository.updateTask(task);
    }

    public boolean completeTask(TaskDto task) {
        tasksRepository.setTaskComplete(task.getId(), task.isHidden());
        return true;
    }

    public boolean deleteTask(UUID taskId) {
        return tasksRepository.deleteTask(taskId);
    }
}

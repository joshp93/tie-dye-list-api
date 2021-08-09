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

    public void showCompleted(boolean shouldShowComplete) {
        this.showCompleted = shouldShowComplete;
    }

    public Collection<TaskListDto> getTaskLists() {
        return tasksRepository.getTaskLists();
    }

    public TaskListDto insertTaskList(String title) {
        TaskListDto taskList = new TaskListDto(title);
        return tasksRepository.insertTaskList(taskList);
    }

    public TaskListDto patchTaskList(UUID id, TaskListDto taskList) {
        taskList.setId(id);
        return tasksRepository.updateTaskList(taskList);
    }

    public TaskListDto getTaskList(UUID id) {
        return tasksRepository.getTaskList(id);
    }

    public boolean deleteTaskList(UUID id) {
        return tasksRepository.deleteTaskList(id);
    }

    public Collection<TaskDto> getTasks(UUID taskListId) {
        Collection<TaskDto> tasks = tasksRepository.getTasks(taskListId);
        return showCompleted ? tasks : tasks.stream().filter(taskDto -> taskDto.isHidden() == false).collect(Collectors.toList());
    }

    public TaskDto getTask(UUID taskId) {
        return tasksRepository.getTask(taskId);
    }

    public TaskDto insertTask(UUID taskListId, TaskDto task) {
        task.setTaskListId(taskListId);
        TaskDto newTask = new TaskDto(task);
        return tasksRepository.insertTask(newTask);
    }

    public TaskDto patchTask(UUID taskListId, UUID id, TaskDto task) {
        task.setTaskListId(taskListId);
        task.setId(id);
        return tasksRepository.updateTask(task);
    }

    public boolean completeTask(UUID taskListId, UUID id, TaskDto task) {
        task.setTaskListId(taskListId);
        task.setId(id);
        tasksRepository.setTaskComplete(task.getId(), task.isHidden());
        return true;
    }

    public boolean deleteTask(UUID taskId) {
        return tasksRepository.deleteTask(taskId);
    }
}

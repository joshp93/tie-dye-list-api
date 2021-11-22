package api.tiedyelist.services;

import api.tiedyelist.dtos.TaskDTO;
import api.tiedyelist.dtos.TaskListDTO;
import api.tiedyelist.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TasksService {

    @Autowired
    TasksRepository tasksRepository;

    private Map<UUID, TaskListDTO> taskLists = new HashMap<>();
    private Map<UUID, TaskDTO> tasks = new HashMap<>();
    private boolean showCompleted = false;

    public Collection<TaskListDTO> getTaskLists() {
        return tasksRepository.getTaskLists();
    }

    public TaskListDTO insertTaskList(String title) {
        TaskListDTO taskList = new TaskListDTO(title);
        return tasksRepository.insertTaskList(taskList);
    }

    public TaskListDTO patchTaskList(UUID id, TaskListDTO taskList) {
        taskList.setId(id);
        return tasksRepository.updateTaskList(taskList);
    }

    public TaskListDTO getTaskList(UUID id) {
        return tasksRepository.getTaskList(id);
    }

    public boolean deleteTaskList(UUID id) {
        return tasksRepository.deleteTaskList(id);
    }

    public Collection<TaskDTO> getTasks(UUID taskListId, boolean showCompleted) {
        Collection<TaskDTO> tasks = tasksRepository.getTasks(taskListId);
        return showCompleted ? tasks : tasks.stream().filter(taskDTO -> taskDTO.isHidden() == false).collect(Collectors.toList());
    }

    public TaskDTO getTask(UUID taskId) {
        return tasksRepository.getTask(taskId);
    }

    public TaskDTO insertTask(UUID taskListId, TaskDTO task) {
        task.setTaskListId(taskListId);
        TaskDTO newTask = new TaskDTO(task);
        return tasksRepository.insertTask(newTask);
    }

    public TaskDTO patchTask(UUID taskListId, UUID id, TaskDTO task) {
        task.setTaskListId(taskListId);
        task.setId(id);
        return tasksRepository.updateTask(task);
    }

    public boolean completeTask(UUID taskListId, UUID id, TaskDTO task) {
        task.setTaskListId(taskListId);
        task.setId(id);
        tasksRepository.setTaskComplete(task.getId(), task.isHidden());
        return true;
    }

    public boolean deleteTask(UUID taskId) {
        return tasksRepository.deleteTask(taskId);
    }
}

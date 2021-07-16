package api.tiedielist.services;

import api.tiedielist.dtos.TaskListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TasksService {

    private Map<UUID, TaskListDto> taskLists;

    TasksService() {
        taskLists = new HashMap<>();
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
}

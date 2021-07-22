package api.tiedielist.controllers;

import api.tiedielist.dtos.TaskDto;
import api.tiedielist.dtos.TaskListDto;
import api.tiedielist.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("tasks")
public class TasksController {

    @Autowired
    TasksService tasksService;

    @GetMapping("hello")
    public String hello() {
        return "Hello everyone";
    }

    @PostMapping("show-complete")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public void showCompleteTasks() throws IOException {
        tasksService.showCompleted();
    }

    @GetMapping("lists")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public Collection<TaskListDto> listTaskLists() throws IOException {
        return tasksService.getTaskLists();
    }

    @GetMapping("lists/{id}")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskListDto getTaskList(@PathVariable UUID id) throws IOException {
        return tasksService.getTaskList(id);
    }

    @DeleteMapping("lists/{id}")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskListDto deleteTaskList(@PathVariable UUID id) throws IOException {
        return tasksService.deleteTaskList(id);
    }

    @PatchMapping("lists/{id}")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskListDto patchTaskList(@PathVariable UUID id, @RequestBody TaskListDto taskList) throws IOException {
        return tasksService.patchTaskList(id, taskList.getTitle());
    }

    @PostMapping("lists")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskListDto insertTaskList(@RequestBody TaskListDto taskList) throws IOException {
        return tasksService.insertTaskList(taskList.getTitle());
    }

    @GetMapping("lists/{taskListId}/tasks")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public Collection<TaskDto> listTasks(@PathVariable String taskListId, @RequestParam(required = false, name = "show_completed") Boolean showCompletedTasks) throws IOException {
        return tasksService.listTasks(taskListId, showCompletedTasks);
    }

    @GetMapping("lists/{taskListId}/tasks/{taskId}")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskDto getTask(@PathVariable UUID taskListId, @PathVariable UUID taskId) throws IOException {
        return tasksService.getTask(taskListId, taskId);
    }

    @PostMapping("lists/{taskListId}/tasks")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskDto insertTask(@PathVariable UUID taskListId,
                           @RequestParam(required = false, name = "previous_task_id") UUID previousTaskId,
                           @RequestBody TaskDto task) throws IOException {
        return tasksService.insertTask(taskListId, previousTaskId, task);
    }

    @PatchMapping("lists/{taskListId}/tasks/{taskId}")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskDto patchTask(@PathVariable UUID taskListId, @PathVariable UUID taskId, @RequestBody TaskDto task) throws IOException {
        return tasksService.patchTask(taskId, task);
    }

    @PostMapping("lists/{taskListId}/tasks/{taskId}/complete")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskDto completeTask(@PathVariable UUID taskListId, @PathVariable UUID taskId, @RequestBody TaskDto task) throws IOException {
        return tasksService.completeTask(taskListId, taskId, task);
    }

    @DeleteMapping("lists/{taskListId}/tasks/{taskId}")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskDto deleteTask(@PathVariable UUID taskListId, @PathVariable UUID taskId) throws IOException {
        return tasksService.deleteTask(taskListId, taskId);
    }
}

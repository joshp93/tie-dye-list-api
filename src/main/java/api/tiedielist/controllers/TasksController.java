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
    public void showCompleteTasks() {
        tasksService.showCompleted();
    }

    @GetMapping("lists")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public Collection<TaskListDto> listTaskLists() {
        return tasksService.getTaskLists();
    }

    @GetMapping("lists/{id}")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskListDto getTaskList(@PathVariable UUID id) {
        return tasksService.getTaskList(id);
    }

    @DeleteMapping("lists/{id}")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public boolean deleteTaskList(@PathVariable UUID id) {
        return tasksService.deleteTaskList(id);
    }

    @PatchMapping("lists/{id}")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskListDto patchTaskList(@PathVariable UUID id, @RequestBody TaskListDto taskList) {
        return tasksService.patchTaskList(taskList);
    }

    @PostMapping("lists")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskListDto insertTaskList(@RequestBody TaskListDto taskList) {
        return tasksService.insertTaskList(taskList.getTitle());
    }

    @GetMapping("lists/{taskListId}/tasks")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public Collection<TaskDto> listTasks(@PathVariable String taskListId, @RequestParam(required = false, name = "show_completed") Boolean showCompletedTasks) {
        return tasksService.listTasks();
    }

    @GetMapping("lists/{taskListId}/tasks/{taskId}")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskDto getTask(@PathVariable UUID taskListId, @PathVariable UUID taskId) {
        return tasksService.getTask(taskId);
    }

    @PostMapping("lists/{taskListId}/tasks")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskDto insertTask(@PathVariable UUID taskListId,
                           @RequestParam(required = false, name = "previous_task_id") UUID previousTaskId,
                           @RequestBody TaskDto task) {
        return tasksService.insertTask(task);
    }

    @PatchMapping("lists/{taskListId}/tasks/{taskId}")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskDto patchTask(@PathVariable UUID taskListId, @PathVariable UUID taskId, @RequestBody TaskDto task) {
        return tasksService.patchTask(task);
    }

    @PostMapping("lists/{taskListId}/tasks/{taskId}/complete")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public boolean completeTask(@PathVariable UUID taskListId, @PathVariable UUID taskId, @RequestBody TaskDto task) {
        return tasksService.completeTask(task);
    }

    @DeleteMapping("lists/{taskListId}/tasks/{taskId}")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public boolean deleteTask(@PathVariable UUID taskListId, @PathVariable UUID taskId) {
        return tasksService.deleteTask(taskId);
    }
}

package api.tiedielist.controllers;

import api.tiedielist.dtos.TaskDto;
import api.tiedielist.dtos.TaskListDto;
import api.tiedielist.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
    public void showCompleteTasks(@RequestBody boolean showComplete) {
        tasksService.showCompleted(showComplete);
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
        return tasksService.patchTaskList(id, taskList);
    }

    @PostMapping("lists")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskListDto insertTaskList(@RequestBody TaskListDto taskList) {
        return tasksService.insertTaskList(taskList.getTitle());
    }

    @GetMapping("lists/{taskListId}/tasks")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public Collection<TaskDto> listTasks(@PathVariable UUID taskListId) {
        return tasksService.getTasks(taskListId);
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
        return tasksService.insertTask(taskListId, task);
    }

    @PatchMapping("lists/{taskListId}/tasks/{id}")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskDto patchTask(@PathVariable UUID taskListId, @PathVariable UUID id, @RequestBody TaskDto task) {
        return tasksService.patchTask(taskListId, id, task);
    }

    @PostMapping("lists/{taskListId}/tasks/{id}/complete")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public boolean completeTask(@PathVariable UUID taskListId, @PathVariable UUID id, @RequestBody TaskDto task) {
        return tasksService.completeTask(taskListId, id, task);
    }

    @DeleteMapping("lists/{taskListId}/tasks/{id}")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public boolean deleteTask(@PathVariable UUID taskListId, @PathVariable UUID id) {
        return tasksService.deleteTask(id);
    }
}

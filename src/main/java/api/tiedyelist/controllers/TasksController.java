package api.tiedyelist.controllers;

import api.tiedyelist.dtos.TaskDTO;
import api.tiedyelist.dtos.TaskListDTO;
import api.tiedyelist.services.TasksService;
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

    @GetMapping("lists")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public Collection<TaskListDTO> listTaskLists() {
        return tasksService.getTaskLists();
    }

    @GetMapping("lists/{id}")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskListDTO getTaskList(@PathVariable UUID id) {
        return tasksService.getTaskList(id);
    }

    @DeleteMapping("lists/{id}")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public boolean deleteTaskList(@PathVariable UUID id) {
        return tasksService.deleteTaskList(id);
    }

    @PatchMapping("lists/{id}")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskListDTO patchTaskList(@PathVariable UUID id, @RequestBody TaskListDTO taskList) {
        return tasksService.patchTaskList(id, taskList);
    }

    @PostMapping("lists")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskListDTO insertTaskList(@RequestBody TaskListDTO taskList) {
        return tasksService.insertTaskList(taskList.getTitle());
    }

    @GetMapping("lists/{taskListId}/tasks")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public Collection<TaskDTO> listTasks(@PathVariable UUID taskListId, @RequestParam boolean showCompletedTasks) {
        return tasksService.getTasks(taskListId, showCompletedTasks);
    }

    @GetMapping("lists/{taskListId}/tasks/{taskId}")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskDTO getTask(@PathVariable UUID taskListId, @PathVariable UUID taskId) {
        return tasksService.getTask(taskId);
    }

    @PostMapping("lists/{taskListId}/tasks")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskDTO insertTask(@PathVariable UUID taskListId,
                              @RequestParam(required = false, name = "previous_task_id") UUID previousTaskId,
                              @RequestBody TaskDTO task) {
        return tasksService.insertTask(taskListId, task);
    }

    @PatchMapping("lists/{taskListId}/tasks/{id}")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public TaskDTO patchTask(@PathVariable UUID taskListId, @PathVariable UUID id, @RequestBody TaskDTO task) {
        return tasksService.patchTask(taskListId, id, task);
    }

    @PostMapping("lists/{taskListId}/tasks/{id}/complete")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public boolean completeTask(@PathVariable UUID taskListId, @PathVariable UUID id, @RequestBody TaskDTO task) {
        return tasksService.completeTask(taskListId, id, task);
    }

    @DeleteMapping("lists/{taskListId}/tasks/{id}")
    @CrossOrigin(origins = { "http://localhost:4200" })
    public boolean deleteTask(@PathVariable UUID taskListId, @PathVariable UUID id) {
        return tasksService.deleteTask(id);
    }
}

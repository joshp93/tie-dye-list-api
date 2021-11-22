package api.tiedyelist.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class TaskDTO {
    private UUID id;
    private UUID taskListId;
    private String title;
    private int position;
    private String notes;
    private LocalDateTime due;
    private LocalDateTime completed;
    private boolean hidden;

    public TaskDTO() {

    }

    public TaskDTO(String title, int position, String notes, LocalDateTime due, LocalDateTime completed, boolean hidden, UUID taskListId) {
        this.id = UUID.randomUUID();
        this.taskListId = taskListId;
        this.title = title;
        this.position = position;
        this.notes = notes;
        this.due = due;
        this.completed = completed;
        this.hidden = hidden;
    }

    public TaskDTO(UUID id, UUID taskListId, String title, int position, String notes, LocalDateTime due, LocalDateTime completed, boolean hidden) {
        this.id = id;
        this.taskListId = taskListId;
        this.title = title;
        this.position = position;
        this.notes = notes;
        this.due = due;
        this.completed = completed;
        this.hidden = hidden;
    }

    public TaskDTO(TaskDTO task) {
        this.id = UUID.randomUUID();
        this.taskListId = task.getTaskListId();
        this.title = task.getTitle();
        this.position = task.getPosition();
        this.notes = task.getNotes();
        this.due = task.getDue();
        this.completed = task.getCompleted();
        this.hidden = task.isHidden();
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public LocalDateTime getCompleted() {
        return completed;
    }

    public void setCompleted(LocalDateTime completed) {
        this.completed = completed;
    }

    public LocalDateTime getDue() {
        return due;
    }

    public void setDue(LocalDateTime due) {
        this.due = due;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public UUID getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(UUID taskListId) {
        this.taskListId = taskListId;
    }
}

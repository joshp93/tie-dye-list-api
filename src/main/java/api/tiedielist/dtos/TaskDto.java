package api.tiedielist.dtos;

import java.util.Date;
import java.util.UUID;

public class TaskDto {
    private UUID id;
    private UUID taskListId;
    private String title;
    private int position;
    private String notes;
    private Date due;
    private Date completed;
    private boolean hidden;

    public TaskDto() {

    }

    public TaskDto(String title, int position, String notes, Date due, Date completed, boolean hidden, UUID taskListId) {
        this.id = UUID.randomUUID();
        this.taskListId = taskListId;
        this.title = title;
        this.position = position;
        this.notes = notes;
        this.due = due;
        this.completed = completed;
        this.hidden = hidden;
    }

    public TaskDto(UUID id, UUID taskListId, String title, int position, String notes, Date due, Date completed, boolean hidden) {
        this.id = id;
        this.taskListId = taskListId;
        this.title = title;
        this.position = position;
        this.notes = notes;
        this.due = due;
        this.completed = completed;
        this.hidden = hidden;
    }

    public TaskDto(TaskDto task) {
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

    public Date getCompleted() {
        return completed;
    }

    public void setCompleted(Date completed) {
        this.completed = completed;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
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

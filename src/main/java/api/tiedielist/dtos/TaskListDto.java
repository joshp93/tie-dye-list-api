package api.tiedielist.dtos;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TaskListDto {
    private UUID id;
    private String title;

    public TaskListDto() {

    }

    public TaskListDto(String title) {
        this.id = UUID.randomUUID();
        this.title = title;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
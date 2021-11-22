package api.tiedyelist.dtos;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TaskListDTO {
    private UUID id;
    private String title;

    public TaskListDTO() {

    }

    public TaskListDTO(String title) {
        this.id = UUID.randomUUID();
        this.title = title;
    }

    public TaskListDTO(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

    public void setId(UUID id) {
        this.id = id;
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

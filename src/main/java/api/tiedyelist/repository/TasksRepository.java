package api.tiedyelist.repository;

import api.tiedyelist.dtos.TaskDTO;
import api.tiedyelist.dtos.TaskListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class TasksRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public TaskListDTO insertTaskList(TaskListDTO taskList) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName("insert_task_list");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("pId", taskList.getId())
                .addValue("pTitle", taskList.getTitle());
        Map<String, Object> out = simpleJdbcCall.execute(in);
        List<Object> resultsSet = (List<Object>) out.get("#result-set-1");
        if (!resultsSet.isEmpty()) {
            LinkedCaseInsensitiveMap<String> result = (LinkedCaseInsensitiveMap<String>) resultsSet.get(0);
            return new TaskListDTO(UUID.fromString(result.get("id")), result.get("title"));
        } else {
            return null;
        }
    }

    public TaskListDTO getTaskList(UUID id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName("get_task_list");
        SqlParameterSource in = new MapSqlParameterSource().addValue("pId", id);

        Map<String, Object> out = simpleJdbcCall.execute(in);
        List<Object> resultsSet = (List<Object>) out.get("#result-set-1");
        if (!resultsSet.isEmpty()) {
            LinkedCaseInsensitiveMap<String> result = (LinkedCaseInsensitiveMap<String>) resultsSet.get(0);
            return new TaskListDTO(UUID.fromString(result.get("id")), result.get("title"));
        } else {
            return null;
        }
    }

    public Collection<TaskListDTO> getTaskLists() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName("get_task_lists");
        SqlParameterSource in = new MapSqlParameterSource();

        Map<String, Object> out = simpleJdbcCall.execute(in);
        List<Object> resultsSet = (List<Object>) out.get("#result-set-1");
        List<TaskListDTO> taskLists = new ArrayList<>();
        if (!resultsSet.isEmpty()) {
            resultsSet.forEach(result -> {
                LinkedCaseInsensitiveMap<String> taskList = (LinkedCaseInsensitiveMap<String>) result;
                taskLists.add(new TaskListDTO(UUID.fromString(taskList.get("id")), taskList.get("title")));
            });
        }
        return taskLists;
    }

    public TaskListDTO updateTaskList(TaskListDTO taskList) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName("update_task_list");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("pId", taskList.getId())
                .addValue("pTitle", taskList.getTitle());

        Map<String, Object> out = simpleJdbcCall.execute(in);
        List<Object> resultsSet = (List<Object>) out.get("#result-set-1");
        if (!resultsSet.isEmpty()) {
            LinkedCaseInsensitiveMap<String> result = (LinkedCaseInsensitiveMap<String>) resultsSet.get(0);
            return new TaskListDTO(UUID.fromString(result.get("id")), result.get("title"));
        } else {
            return null;
        }
    }

    public boolean deleteTaskList(UUID id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName("delete_task_list");
        SqlParameterSource in = new MapSqlParameterSource().addValue("pId", id);

        Map<String, Object> out = simpleJdbcCall.execute(in);
        return true;
    }

    public TaskDTO insertTask(TaskDTO task) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName("insert_task");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("pId", task.getId())
                .addValue("pTaskListId", task.getTaskListId())
                .addValue("pTitle", task.getTitle())
                .addValue("pPosition", task.getPosition())
                .addValue("pNotes", task.getNotes())
                .addValue("pDue", task.getDue())
                .addValue("pCompleted", task.getCompleted())
                .addValue("pHidden", task.isHidden());

        Map<String, Object> out = simpleJdbcCall.execute(in);
        List<Object> resultsSet = (List<Object>) out.get("#result-set-1");
        if (!resultsSet.isEmpty()) {
            LinkedCaseInsensitiveMap<Object> result = (LinkedCaseInsensitiveMap<Object>) resultsSet.get(0);
            return new TaskDTO(UUID.fromString((String) result.get("id")), UUID.fromString((String) result.get("taskListId")), (String) result.get("title"),
                    (int) result.get("position"), (String) result.get("notes"), (LocalDateTime) result.get("due"), (LocalDateTime) result.get("completed"), (boolean) result.get("hidden"));
        } else {
            return null;
        }
    }

    public TaskDTO getTask(UUID id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName("get_task");
        SqlParameterSource in = new MapSqlParameterSource().addValue("pId", id);
        Map<String, Object> out = simpleJdbcCall.execute(in);

        List<Object> resultsSet = (List<Object>) out.get("#result-set-1");
        if (!resultsSet.isEmpty()) {
            LinkedCaseInsensitiveMap<Object> task = (LinkedCaseInsensitiveMap<Object>) resultsSet.get(0);
            return new TaskDTO(UUID.fromString((String) task.get("id")), UUID.fromString((String) task.get("taskListId")), (String) task.get("title"),
                    (int) task.get("position"), (String) task.get("notes"), (LocalDateTime) task.get("due"), (LocalDateTime) task.get("completed"), (boolean) task.get("hidden"));
        } else {
            return null;
        }
    }

    public Collection<TaskDTO> getTasks(UUID taskListId) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName("get_tasks");
        SqlParameterSource in = new MapSqlParameterSource().addValue("pTaskListId", taskListId);

        Map<String, Object> out = simpleJdbcCall.execute(in);
        List<Object> resultsSet = (List<Object>) out.get("#result-set-1");
        List<TaskDTO> tasks = new ArrayList<>();
        if (!resultsSet.isEmpty()) {
            resultsSet.forEach(result -> {
                LinkedCaseInsensitiveMap<Object> task = (LinkedCaseInsensitiveMap<Object>) result;
                tasks.add(new TaskDTO(UUID.fromString((String) task.get("id")), UUID.fromString((String) task.get("taskListId")), (String) task.get("title"),
                        (int) task.get("position"), (String) task.get("notes"), (LocalDateTime) task.get("due"), (LocalDateTime) task.get("completed"), (boolean) task.get("hidden")));
            });
        }
        return tasks;
    }

    public TaskDTO updateTask(TaskDTO task) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName("update_task");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("pId", task.getId())
                .addValue("pTaskListId", task.getTaskListId())
                .addValue("pTitle", task.getTitle())
                .addValue("pPosition", task.getPosition())
                .addValue("pNotes", task.getNotes())
                .addValue("pDue", task.getDue())
                .addValue("pCompleted", task.getCompleted())
                .addValue("pHidden", task.isHidden());

        Map<String, Object> out = simpleJdbcCall.execute(in);
        List<Object> resultsSet = (List<Object>) out.get("#result-set-1");
        if (!resultsSet.isEmpty()) {
            LinkedCaseInsensitiveMap<Object> result = (LinkedCaseInsensitiveMap<Object>) resultsSet.get(0);
            return new TaskDTO(UUID.fromString((String) result.get("id")), UUID.fromString((String) result.get("taskListId")), (String) result.get("title"),
                    (int) result.get("position"), (String) result.get("notes"), (LocalDateTime) result.get("due"), (LocalDateTime) result.get("completed"), (boolean) result.get("hidden"));
        } else {
            return null;
        }
    }

    public boolean deleteTask(UUID id) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName("delete_task");
        SqlParameterSource in = new MapSqlParameterSource().addValue("pId", id);

        Map<String, Object> out = simpleJdbcCall.execute(in);
        return true;
    }

    public boolean setTaskComplete(UUID id, boolean complete) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName("set_task_complete");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("pId", id)
                .addValue("pComplete", complete);

        Map<String, Object> out = simpleJdbcCall.execute(in);
        return true;
    }
}

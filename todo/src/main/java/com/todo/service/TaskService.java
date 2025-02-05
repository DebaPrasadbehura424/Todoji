package com.todo.service;

import java.util.List;
import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.model.Task;
import com.todo.repository.TaskRepository;

@Service
public class TaskService {
    // service
    // @Autowired
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void makeTasks(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskRepository.save(task);
    }

    public void delTask(Long id) {
        taskRepository.deleteById(id);

    }

    public void toggleTask(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional == null) {
            throw new IllegalArgumentException("task id is null");
        }
        Task task = taskOptional.get();
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }

}

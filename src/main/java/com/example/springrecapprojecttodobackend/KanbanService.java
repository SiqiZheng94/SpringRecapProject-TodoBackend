package com.example.springrecapprojecttodobackend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KanbanService {
    private final KanbanRepo repo;
    public List<Todos> getAllTodos(){
       List<Todos> todoList=repo.findAll();
        if(todoList.isEmpty()){
            throw new NullPointerException("nothing there");
        }
        return repo.findAll();
    }
    public Todos addTodo(Todos todo){
        Todos newTodo = new Todos(UUID.randomUUID().toString(), todo.description(), todo.status());

        return repo.save(newTodo);
    }

    public Todos updateStatus(String id) {
        Optional<Todos> todo = repo.findById(id);
        if(todo.isPresent()){
            if (todo.get().status().equals("OPEN")){
                Todos newTodo = todo.get().withStatus("IN_PROGRESS");
                return repo.save(newTodo);
            } else if(todo.get().status().equals("IN_PROGRESS")){
                Todos newTodo = todo.get().withStatus("DONE");
                return repo.save(newTodo);
            }
        }
        throw new NullPointerException("not found!");
    }
    public Todos updateStatusPT(Todos todo) {
        Optional<Todos> find = repo.findById(todo.id());
        if(find.isPresent()){
                Todos updatedTodo = find.get().withStatus(todo.status())
                        .withDescription(todo.description());
                return repo.save(updatedTodo);
        }
        throw new NullPointerException("not found!");
    }

    public void deleteTodo(String id){
        repo.deleteById(id);
    }


    public Todos findByid(String id) {
        Optional<Todos> findById = repo.findById(id);
        if(findById.isPresent()){
            return repo.findById(id).get();
        }
        throw new NullPointerException("not found!");
    }
}

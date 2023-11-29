package com.example.springrecapprojecttodobackend;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class KanbanController {

    private final KanbanService service;
    @GetMapping
    public List<Todos> getAll(){
        return service.getAllTodos();
    }
    @PostMapping
    public Todos addTodo(@RequestBody Todos todo){
        return service.addTodo(todo);
    }
    @PutMapping("/{id}")
    public Todos putIdStuff(@RequestBody Todos todo)
    {
       return service.updateStatusPT(todo);
    }
//    public Todos advanceTodo(@PathVariable String id){
//        return service.updateStatus(id);
//    }
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable String id){
        service.deleteTodo(id);
    }
    @GetMapping("/{id}")
    public Todos allInfo(@PathVariable String id){
        return service.findByid(id);
    }





}

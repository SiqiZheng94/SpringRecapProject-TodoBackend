package com.example.springrecapprojecttodobackend;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class KanbanController {
    @Value("${OPEN_API_KEY}")
    private String chatGPTApiKey;
    private final KanbanService service;
    @GetMapping
    public List<Todos> getAll(){
        return service.getAllTodos();
    }
    @PostMapping
    public Todos addTodo(@RequestBody Todos todo){
        ChatgptRequest chatgptRequest = new ChatgptRequest("gpt-3.5-turbo",
                List.of(new ChatgptMessage("user", "Check the following text for spelling and grammar and only return the corrected version. no explanation just the corrected version: "+todo.description())));
        ChatgptResponse response = Objects.requireNonNull(
                WebClient.create()
                        .post()
                        .uri("https://api.openai.com/v1/chat/completions")
                        .bodyValue(chatgptRequest)
                        .header("Authorization", "Bearer "+chatGPTApiKey)
                        .retrieve()
                        .toEntity(ChatgptResponse.class)
                        .block()
        ).getBody();
        if(response.choices().isEmpty()){
            throw new NullPointerException("not found!");
        }
        Todos chatgptTodo = todo.withDescription(response.choices().get(0).message().content());
        return service.addTodo(chatgptTodo);
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

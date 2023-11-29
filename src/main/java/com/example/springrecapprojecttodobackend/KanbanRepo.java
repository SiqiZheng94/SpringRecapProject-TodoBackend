package com.example.springrecapprojecttodobackend;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KanbanRepo extends MongoRepository<Todos,String> {
}

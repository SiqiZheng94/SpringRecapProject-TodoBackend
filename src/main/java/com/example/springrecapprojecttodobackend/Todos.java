package com.example.springrecapprojecttodobackend;

import lombok.Builder;
import lombok.With;
@Builder
@With
public record Todos(String id, String description, String status) {
}

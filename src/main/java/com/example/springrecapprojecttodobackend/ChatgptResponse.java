package com.example.springrecapprojecttodobackend;

import java.util.List;

public record ChatgptResponse(List<ChatgptChoice> choices) {
}

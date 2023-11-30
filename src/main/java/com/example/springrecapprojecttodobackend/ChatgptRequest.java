package com.example.springrecapprojecttodobackend;

import java.util.List;

public record ChatgptRequest(String model, List<ChatgptMessage> messages ) {
}

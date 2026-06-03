package com.example.payment.infraestructure.exception.model;

import java.util.List;

public record ResponseExceptionCustom(
        List<MessageItem> messages
) {
}

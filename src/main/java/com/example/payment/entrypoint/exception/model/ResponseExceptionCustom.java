package com.example.payment.entrypoint.exception.model;

import java.util.List;

public record ResponseExceptionCustom(
        List<MessageItem> messages
) {
}

package com.wpCorp.dsCommerce.Resource.Exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidateMessage extends StandarError {

    private List<FieldMessage> messages = new ArrayList<>();

    public ValidateMessage() {
    }

    public ValidateMessage(Instant moment, String path, String message, String error, Integer status) {
        super(moment, path, message, error, status);
    }

    public List<FieldMessage> getMessages() {
        return messages;
    }

    public void addMessage(String field, String message) {
        messages.add(new FieldMessage(field, message));
    }
}

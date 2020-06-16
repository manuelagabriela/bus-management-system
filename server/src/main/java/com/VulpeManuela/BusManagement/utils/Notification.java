package com.VulpeManuela.BusManagement.utils;

import java.util.ArrayList;
import java.util.List;

public class Notification {
    private List<String> errors;

    public Notification(){
        this.errors = new ArrayList<>();
    }
    public void addError(String message) { errors.add(message); }
    public boolean hasErrors() {
        return ! errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}

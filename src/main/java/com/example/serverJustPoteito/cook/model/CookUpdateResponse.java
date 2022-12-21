package com.example.serverJustPoteito.cook.model;

public class CookUpdateResponse {
    private boolean exists;
    private Cook updatedCook;

    public CookUpdateResponse() {
    }

    public CookUpdateResponse(boolean exists, Cook updatedCook) {
        this.exists = exists;
        this.updatedCook = updatedCook;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public Cook getUpdatedCook() {
        return updatedCook;
    }

    public void setUpdatedCook(Cook updatedCook) {
        this.updatedCook = updatedCook;
    }

    @Override
    public String toString() {
        return "CookUpdateResponse{" +
                "exists=" + exists +
                ", updatedCook=" + updatedCook +
                '}';
    }
}

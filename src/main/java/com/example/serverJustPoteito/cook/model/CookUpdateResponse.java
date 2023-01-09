package com.example.serverJustPoteito.cook.model;

public class CookUpdateResponse {
    private boolean exists;
    private CookServiceModel updatedCook;

    public CookUpdateResponse() {
    }

    public CookUpdateResponse(boolean exists, CookServiceModel updatedCook) {
        this.exists = exists;
        this.updatedCook = updatedCook;
    }

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public CookServiceModel getUpdatedCook() {
        return updatedCook;
    }

    public void setUpdatedCook(CookServiceModel updatedCook) {
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

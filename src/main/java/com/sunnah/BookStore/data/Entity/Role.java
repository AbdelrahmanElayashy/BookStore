package com.sunnah.BookStore.data.Entity;



public enum Role{
    ADMIN("admin"), USER("user");
    String role;

    Role(String ro) {
        this.role = ro;
    }
    public String getRole() {
        return this.role;
    }


}
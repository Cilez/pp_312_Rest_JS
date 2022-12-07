package com.mygroup.kata.model;

import java.util.List;

public class UserCreationDTO {
    private List<User> users;

    public UserCreationDTO() {
    }

    public UserCreationDTO(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

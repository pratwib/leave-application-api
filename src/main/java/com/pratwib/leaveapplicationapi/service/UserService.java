package com.pratwib.leaveapplicationapi.service;

import com.pratwib.leaveapplicationapi.model.entity.User;

public interface UserService {
    User create(User user);

    User getEntityById(String id);

    User getByUsername(String username);

    void softDeleteById(String id);
}

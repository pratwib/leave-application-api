package com.pratwib.leaveapplicationapi.service.impl;

import com.pratwib.leaveapplicationapi.exception.NotFoundException;
import com.pratwib.leaveapplicationapi.model.entity.User;
import com.pratwib.leaveapplicationapi.repository.UserRepository;
import com.pratwib.leaveapplicationapi.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getEntityById(String id) {
        return userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User not found"));
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new NotFoundException("User not found"));
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void softDeleteById(String id) {
        User user = getEntityById(id);

        user.setIsActive(false);
        userRepository.save(user);
    }
}
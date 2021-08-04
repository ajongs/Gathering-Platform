package com.gatheringplatform.service;

import com.gatheringplatform.domain.User;

import java.util.Map;

public interface UserService {
    void signUp(User user);
    Map<String, String> logIn(User user);
    Map<String, String> refresh();
}

package com.open.capacity.user.controller;

import com.open.capacity.common.web.Result;
import com.open.capacity.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/deduction")
    public Result deduction(@NotNull String userId, HttpServletRequest request) {
        return  userService.deduction(userId);
    }

}

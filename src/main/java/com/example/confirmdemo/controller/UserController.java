package com.example.confirmdemo.controller;

import com.example.confirmdemo.dal.UserDal;
import com.example.confirmdemo.init.Response;
import com.example.confirmdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author 25290
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserDal userDal;

    @Autowired
    public UserController(UserDal userDal) {
        this.userDal = userDal;
    }

    @PostMapping("/addUser")
    public Response addUser(@RequestBody User user) {
        return userDal.saveUser(user);
    }

    @DeleteMapping("/deleteById")
    public Response deleteById(@RequestParam UUID id) {
        return userDal.deleteById(id);
    }

    @PostMapping("/updateDescribeById")
    public Response addUser(@RequestParam UUID id,String description) {
        return userDal.updateDescribeById(id, description);
    }

    @PostMapping("/updatePasswordById")
    public Response updatePasswordById(@RequestParam UUID id, @RequestParam String password) {
        return userDal.updatePasswordById(id, password);
    }

    @GetMapping("/findById")
    public Response findById(@RequestParam UUID id) {
        return userDal.findById(id);
    }

    @GetMapping("/findByUsername")
    public Response findByUsername(@RequestParam String username) {
        return userDal.findByUsername(username);
    }

    @GetMapping("/findAll")
    public Response findAll() {
        return userDal.findAll();
    }
}

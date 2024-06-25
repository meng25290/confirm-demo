package com.example.confirmdemo.controller;

import com.example.confirmdemo.dal.UserDal;
import com.example.confirmdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            userDal.saveUser(user);
            return ResponseEntity.ok().body("删除成功");
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<?> deleteById(@RequestParam String id) {
        if (userDal.deleteById(id)) {
            return ResponseEntity.ok().body("删除成功");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/updateDescribeById")
    public ResponseEntity<?> addUser(@RequestParam String id, String description) {
        if (userDal.updateDescribeById(id, description)) {
            return ResponseEntity.ok().body("更新成功");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/updatePasswordById")
    public ResponseEntity<?> updatePasswordById(@RequestParam String id, @RequestParam String password) {
        if (userDal.updatePasswordById(id, password)) {
            return ResponseEntity.ok().body("更新成功");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam String id) {
        User user = userDal.findById(id);
        if (user != null) {
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findByUsername")
    public ResponseEntity<?> findByUsername(@RequestParam String username) {
        User user = userDal.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(userDal.findAll());
    }
}

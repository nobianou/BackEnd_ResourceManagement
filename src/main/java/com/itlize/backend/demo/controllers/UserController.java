package com.itlize.backend.demo.controllers;

import com.itlize.backend.demo.entities.User;
import com.itlize.backend.demo.services.UserService;
import com.itlize.backend.demo.utils.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> allUserByUserId() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> userById(@PathVariable int id) {
        return new ResponseEntity<>(userService.findOneById(id), HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteById(@RequestParam("id") int id) {
        return new ResponseEntity<>(userService.deleteOneById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateById(@RequestBody UserDto dto) {
        return new ResponseEntity<>(userService.updateOneById(dto), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOne(@RequestBody User user) {
        return new ResponseEntity<>(userService.createOne(user), HttpStatus.CREATED);
    }

}
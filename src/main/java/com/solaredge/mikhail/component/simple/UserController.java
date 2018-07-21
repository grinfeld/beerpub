package com.solaredge.mikhail.component.simple;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@Slf4j
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") long id) {
        assertId(id);
        return userRepository.getUserById(id);
    }

    private void assertId(long id) {
        if (id <= 0)
            throw new IllegalArgumentException("Invalid id " + String.valueOf(id));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<Error> handleAllExceptions(Exception ex) {
        log.info(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Error.builder().message(ex.getMessage()).build());
    }

    @Data @Value @Builder
    private static class Error {
        private String message;
    }
}
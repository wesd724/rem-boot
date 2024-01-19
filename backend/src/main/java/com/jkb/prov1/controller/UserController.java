package com.jkb.prov1.controller;

import com.jkb.prov1.dto.UserDto;
import com.jkb.prov1.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Long> saveUser(@RequestBody UserDto userDto) {
        Long id = userService.saveUser(userDto);
        log.info("유저 저장 완료 ");
        return ResponseEntity.ok(id);
    }

    @PostMapping
    public ResponseEntity<Boolean> loginUser(@RequestBody UserDto userDto) {
        boolean login = userService.loginUser(userDto);
        return ResponseEntity.ok(login);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

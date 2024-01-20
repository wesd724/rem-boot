package com.jkb.prov1.controller;

import com.jkb.prov1.common.types.LoginStatus;
import com.jkb.prov1.dto.LoginDto;
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
    public ResponseEntity<Boolean> saveUser(@RequestBody UserDto userDto) {
        boolean result = userService.saveUser(userDto);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<LoginDto> loginUser(@RequestBody UserDto userDto) {
        LoginStatus login = userService.loginUser(userDto);
        return ResponseEntity.ok(LoginDto.from(login));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

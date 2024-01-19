package com.jkb.prov1.service;

import com.jkb.prov1.dto.UserDto;
import com.jkb.prov1.entity.User;
import com.jkb.prov1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public Long saveUser(UserDto userDto) {
        User user = User.builder()
                .name(userDto.getName())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .build();

        User saveUser = userRepository.save(user);
        return saveUser.getId();
    }

    public boolean loginUser(UserDto userDto) {
        String name = userDto.getName();
        boolean flag = userRepository.existsByName(name);
        if(!flag) return false;

        String password = userRepository.getPasswordByName(name);
        return password.equals(userDto.getPassword());
    }

    public void deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        userRepository.delete(user);
    }
}

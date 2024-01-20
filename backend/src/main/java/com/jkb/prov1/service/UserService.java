package com.jkb.prov1.service;

import com.jkb.prov1.common.types.LoginStatus;
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
    public boolean saveUser(UserDto userDto) {
        if(userRepository.existsByName(userDto.getName())) {
            return false;
        }
        User user = User.builder()
                .name(userDto.getName())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .build();

        User saveUser = userRepository.save(user);
        return true;
    }

    public LoginStatus loginUser(UserDto userDto) {
        String name = userDto.getName();
        if(!userRepository.existsByName(name)) {
            return LoginStatus.FAIL;
        }
        String password = userRepository.getPasswordByName(name);
        if(password.equals(userDto.getPassword())){
            return LoginStatus.SUCCESS;
        }
        return LoginStatus.INVALID_PASSWORD;
    }

    public void deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        userRepository.delete(user);
    }
}

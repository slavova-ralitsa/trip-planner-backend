package com.example.tripplanner.service;

import com.example.tripplanner.dto.UserRegistrationDTO;
import com.example.tripplanner.entity.User;
import com.example.tripplanner.exception.UserAlreadyExistsException;
import com.example.tripplanner.exception.UserNotFoundException;
import com.example.tripplanner.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException(user.getId());
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()  -> new UserNotFoundException(id));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()  -> new UserNotFoundException(email));
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id))
            throw new UserNotFoundException(id);

        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        if (user.getEmail() != null && !user.getEmail().isBlank()) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return userRepository.save(existingUser);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User registerUser(UserRegistrationDTO dto) {
        String email = dto.getEmail();
        String password = dto.getPassword();

        Optional<User> existingUser = userRepository.findByEmail(email);

        if(existingUser.isPresent())
            throw new UserAlreadyExistsException();

        if(!dto.passwordsMatch())
            throw new IllegalArgumentException("Passwords do not match!");

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }
}




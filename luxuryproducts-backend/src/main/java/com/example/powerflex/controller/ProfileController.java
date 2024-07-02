package com.example.powerflex.controller;

import com.example.powerflex.config.JWTUtil;
import com.example.powerflex.dao.ProfileDAO;
import com.example.powerflex.dao.UserRepository;
import com.example.powerflex.dto.LoginResponse;
import com.example.powerflex.models.CustomUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:19574", "http://s1149574.student.inf-hsleiden.nl:19574"})
@RequestMapping("/profile")
public class ProfileController {
    private final UserRepository userRepository;
    private final ProfileDAO profileDAO;

    public ProfileController(UserRepository userRepository, AuthenticationManager authManager, JWTUtil jwtUtil, ProfileDAO profileDAO, ProfileDAO profileDAO1) {
        this.userRepository = userRepository;
        this.profileDAO = profileDAO1;
    }

    @GetMapping
    public ResponseEntity<CustomUser> getUserProfile() {
        return ResponseEntity.ok(profileDAO.getCurrentUserProfile());
    }

    @GetMapping(params = "email")
    public ResponseEntity<CustomUser> getCustomUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(this.userRepository.findByEmail(email));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<CustomUser> updateCustomUserById(@PathVariable long id, @RequestBody CustomUser customUserDetails) {
//        CustomUser updateCustomUser = userRepository.findById(id).get();
//        updateCustomUser.setEmail(customUserDetails.getEmail());
//        updateCustomUser.setPassword(customUserDetails.getPassword());
//        updateCustomUser.setAddress(customUserDetails.getAddress());
//
//        userRepository.save(updateCustomUser);
//        return ResponseEntity.ok(updateCustomUser);
//    }
}

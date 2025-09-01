package com.library.lms.controller;

import com.library.lms.dto.RegistrationRequest;
import com.library.lms.mapper.MapperFactory;
import com.library.lms.model.Member;
import com.library.lms.model.User;
import com.library.lms.service.MemberService;
import com.library.lms.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final MemberService memberService;

    public UserController(UserService userService, MemberService memberService) {
        this.userService = userService;
        this.memberService = memberService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest request) {
        if (userService.existsByUsername(request.getUsername()))
            return ResponseEntity.badRequest().body("Username already exists");
        if (userService.existsByEmail(request.getEmail()))
            return ResponseEntity.badRequest().body("Email already exists");

        // 1️⃣ Create User
        User newUser = MapperFactory.createUser(
                request.getUsername(),
                request.getEmail(),
                request.getPassword(),
                "Members"
        );
        User savedUser = userService.createUser(newUser);

        // 2️⃣ Create Member linked to User
        Member member = new Member();
        member.setFullName(request.getFullName());
        Member savedMember = memberService.createMember(member, savedUser);

        return ResponseEntity.ok("User and Member registered successfully: " + savedUser.getUsername());
    }
}

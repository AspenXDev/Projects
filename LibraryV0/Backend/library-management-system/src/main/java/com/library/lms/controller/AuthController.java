package com.library.lms.controller;

import com.library.lms.auth.JwtTokenProvider;
import com.library.lms.dto.AuthRequest;
import com.library.lms.dto.AuthResponse;
import com.library.lms.dto.RegistrationRequest;
import com.library.lms.model.Librarian;
import com.library.lms.model.Member;
import com.library.lms.model.Role;
import com.library.lms.model.User;
import com.library.lms.service.LibrarianService;
import com.library.lms.service.MemberService;
import com.library.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final UserService userService;
    private final MemberService memberService;
    private final LibrarianService librarianService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(UserService userService,
                          MemberService memberService,
                          LibrarianService librarianService,
                          JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.memberService = memberService;
        this.librarianService = librarianService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        if (request == null
                || !StringUtils.hasText(request.getUsername())
                || !StringUtils.hasText(request.getPassword())) {
            return ResponseEntity.badRequest().body(new AuthResponse("username and password required"));
        }

        // Use authenticate method from UserService
        User user = userService.authenticate(request.getUsername(), request.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse("Invalid credentials"));
        }

        String fullName = deriveFullNameForUser(user);
        String token = jwtTokenProvider.generateToken(user);

        return ResponseEntity.ok(new AuthResponse(user, fullName, token));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegistrationRequest request) {
        if (request == null
                || !StringUtils.hasText(request.getUsername())
                || !StringUtils.hasText(request.getEmail())
                || !StringUtils.hasText(request.getPassword())
                || !StringUtils.hasText(request.getFullName())) {
            return ResponseEntity.badRequest().body(new AuthResponse(
                    "username, email, password, fullName are required"));
        }

        if (userService.existsByUsername(request.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new AuthResponse("Username already exists"));
        }

        if (userService.existsByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new AuthResponse("Email already exists"));
        }

        Role membersRole = userService.getRoleByName("Members");
        if (membersRole == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AuthResponse("Server misconfigured: Members role not found"));
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(request.getPassword()); // plain save; actual hashing can be handled inside UserService
        user.setRole(membersRole);
        user.setIsActive(true);

        User createdUser = userService.createUser(user);

        Member member = new Member();
        member.setUser(createdUser);
        member.setFullName(request.getFullName());
        member.setRegistrationDate(LocalDate.now());
        member.setMembershipValidUntil(LocalDate.now().plusYears(1));
        member.setMembershipStatus(Member.MembershipStatus.Active);

        memberService.createMember(member, createdUser);

        String token = jwtTokenProvider.generateToken(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse(createdUser, member.getFullName(), token));
    }

    @GetMapping("/me")
    public ResponseEntity<AuthResponse> me(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse("Missing token"));
        }
        String token = authHeader.substring("Bearer ".length());
        String username = jwtTokenProvider.getUsernameFromToken(token);

        User user = userService.getUserByUsername(username).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse("User not found"));
        }

        String fullName = deriveFullNameForUser(user);
        return ResponseEntity.ok(new AuthResponse(user, fullName, token));
    }

    private String deriveFullNameForUser(User user) {
        if (user == null || user.getRole() == null) return null;
        String roleName = user.getRole().getRoleName();
        if (roleName == null) return null;

        try {
            if ("Members".equalsIgnoreCase(roleName)) {
                Member m = memberService.getMemberByUserId(user.getUserId()).orElse(null);
                return m != null ? m.getFullName() : null;
            } else if ("Librarians".equalsIgnoreCase(roleName)) {
                Librarian l = librarianService.getLibrarianByUserId(user.getUserId()).orElse(null);
                return l != null ? l.getFullName() : null;
            }
        } catch (Exception ignored) {
        }
        return null;
    }
}

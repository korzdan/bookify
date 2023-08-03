package by.bookify.user.controller;

import by.bookify.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/my")
    public ResponseEntity<User> getMyUserAccountInfo(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }
}

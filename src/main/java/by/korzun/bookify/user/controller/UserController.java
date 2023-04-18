package by.korzun.bookify.user.controller;

import by.korzun.bookify.user.model.User;
import by.korzun.bookify.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/author/recommendations")
    public ResponseEntity<User> setAuthorRecommendations(
            @RequestParam String userEmail,
            @RequestBody List<Long> authorIds
    ) {
        return ResponseEntity.ok(userService.addAuthorRecommendationsToUser(userEmail, authorIds));
    }

    @PostMapping("/genre/recommendations")
    public ResponseEntity<User> setGenreRecommendations(
            @RequestParam String userEmail,
            @RequestBody List<Long> genreIds
    ) {
        return ResponseEntity.ok(userService.addGenreRecommendationsToUser(userEmail, genreIds));
    }
}

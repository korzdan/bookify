package by.bookify.image.controller;

import by.bookify.image.service.ImageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(@Qualifier("mongoImageService") ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<InputStreamResource> findImage(@PathVariable Long bookId) {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageService.findImage(bookId));
    }

    @PostMapping("/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable Long bookId) {
        imageService.uploadImage(bookId, image);
        return ResponseEntity.ok("Success");
    }
}

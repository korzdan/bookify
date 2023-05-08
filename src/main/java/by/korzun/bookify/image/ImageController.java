package by.korzun.bookify.image;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/img")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @SneakyThrows
    @GetMapping("/{bookId}")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String bookId) {
        String path = System.getProperty("user.dir") + "/src/main/images/" + bookId + ".jpg";
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(
                                new FileInputStream(path)
                        )
                );
    }

    @PostMapping("/{bookId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable Long bookId)
            throws IOException {
        imageService.uploadImage(bookId, image);
        return ResponseEntity.ok("Success");
    }
}

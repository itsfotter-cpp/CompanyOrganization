package it.companyorganization.controller;

import it.companyorganization.model.Image;
import it.companyorganization.service.ImageService;
import it.companyorganization.util.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload/image")
    public ResponseEntity<Image> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        return new ResponseEntity<Image>(imageService.uploadImage(file), HttpStatus.OK);
    }

    @GetMapping("/get/image/{name}")
    public ResponseEntity<byte[]> getImage(@PathVariable("name") long id) {
        return ResponseEntity.ok().contentType(MediaType.valueOf(imageService
                .getImage(id).getType()))
                .body(ImageUtility.decompressImage(imageService.getImage(id).getImage()));
    }

    @PostMapping("/upload-locally/image")
    public ResponseEntity<Image> uploadImageLocally(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        return new ResponseEntity<Image>(imageService.uploadImageLocally(multipartFile), HttpStatus.OK);
    }

}

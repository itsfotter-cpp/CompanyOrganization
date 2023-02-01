package it.companyorganization.service;

import it.companyorganization.model.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface ImageService {

    Image uploadImage(MultipartFile file) throws IOException;

    Image getImage(long id);

    Image uploadImageLocally(MultipartFile multipartFile) throws IOException;

}

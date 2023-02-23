package it.companyorganization.service.impl;

import it.companyorganization.exception.ResourceNotFoundException;
import it.companyorganization.model.Image;
import it.companyorganization.repository.ImageRepository;
import it.companyorganization.service.ImageService;
import it.companyorganization.util.FileUploadUtil;
import it.companyorganization.util.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    /*
     * Save Photo only on the database
     */
    @Override
    public Image uploadImage(MultipartFile file) throws IOException {

        Image imageSaved = Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageUtility.compressImage(file.getBytes())).build();

        imageRepository.save(imageSaved);

        return imageSaved;
    }

    /*
     * getting Photo from the database by the ID
     */
    @Override
    public Image getImage(long id) {

        final Optional<Image> dbImage = imageRepository.findById(id);

        if(dbImage.isPresent()) {
            return dbImage.get();
        }
        else {
            throw new ResourceNotFoundException("Image", "image", id);
        }

    }

    /*
     * Save Photo on the database and also on a local directory
     */
    @Override
    public Image uploadImageLocally(MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        Image imageSaved = Image.builder()
                .name(multipartFile.getOriginalFilename())
                .type(multipartFile.getContentType())
                .path(fileName)
                .image(ImageUtility.compressImage(multipartFile.getBytes())).build();

        imageRepository.save(imageSaved);

        String uploadDir = "user-photos/" + imageSaved.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return imageSaved;
    }

}

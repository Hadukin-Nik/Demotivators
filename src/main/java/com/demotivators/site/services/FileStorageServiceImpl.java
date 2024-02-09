package com.demotivators.site.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import com.demotivators.site.configuration.FileStorageProperties;
import com.demotivators.site.services.exceptions.WrongImageExtensionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;


    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Couldn't create the directory where the upload files will be saved.", ex);
        }

    }

    public String storeFile(MultipartFile file) {
        // Normalize file name
        String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        final Set<String> allowedExtensions = Set.of("jpg", "jpeg", "png", "webp");
        if(!allowedExtensions.contains(filenameExtension)) {
            throw new WrongImageExtensionException();
        }

        String fileName = UUID.randomUUID() + "." + filenameExtension;

        try {
            // Copy file to the target place (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fileName;
    }
}

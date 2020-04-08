package io.stream.com.services;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class S3Client {

    @Autowired
    private AmazonS3 client;

    @Value("${s3.bucket.name}")
    private String bucketName;

    @Value("${s3.bucket.url}")
    private String url;

    public void upload(MultipartFile multipartFile) {
        File file = convertMultiPartToFile(multipartFile);
    }

    private File convertMultiPartToFile(MultipartFile multipartFile){
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()), "MultipartFile provided to S3Client is null");
        createOutputStream(file, multipartFile);
        return file;
    }

    private void createOutputStream(File file, MultipartFile multipartFile){
        try {
            writeOnStream(new FileOutputStream(file), multipartFile);
        } catch (FileNotFoundException e) {
            log.error("File provided to Create Output Stream is not Found");
        }
    }

    private void writeOnStream(FileOutputStream stream, MultipartFile multipartFile){
        try {
            stream.write(multipartFile.getBytes());
            stream.close();
        } catch (IOException e) {
            log.error("Error occurred while tying to write to file output stream");
        }
    }
}
package cloud_computing.image_tag_service.controller;

import cloud_computing.image_tag_service.exception.ImageFileProcessingException;
import cloud_computing.image_tag_service.service.TagDetectionService;
import com.google.protobuf.ByteString;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/api/vision/tag")
@RequiredArgsConstructor
public class ImageTagDetectionController {
    private final TagDetectionService tagDetectionService;

    @PostMapping("/detect")
    public ResponseEntity<?> detectTagsFromImage(@RequestParam MultipartFile file){
        List<String> englishTagList;

        try{
            englishTagList = tagDetectionService.detectTag(file.getInputStream());
        }catch(IOException e){
            throw new ImageFileProcessingException("이미지 파일 처리 오류", e);
        }

        return ResponseEntity.ok(englishTagList);
    }
}

package cloud_computing.image_tag_service.controller;

import cloud_computing.image_tag_service.dto.TagListResponse;
import cloud_computing.image_tag_service.exception.ImageFileProcessingException;
import cloud_computing.image_tag_service.service.TagDetectionClient;
import cloud_computing.image_tag_service.service.TagService;
import cloud_computing.image_tag_service.service.TranslationClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/vision/")
@RequiredArgsConstructor
public class ImageTagDetectionController {
    private final TagService tagService;
    @PostMapping("/tags/detect")
    public ResponseEntity<?> detectTagsFromImage(@RequestAttribute BigDecimal userId, @RequestParam MultipartFile imageFile){
        try{
            tagService.createTags(userId, imageFile.getInputStream());
        }catch(IOException e){
            throw new ImageFileProcessingException("이미지 파일 처리 오류", e);
        }

        return ResponseEntity.ok().build();
    }
    @GetMapping("/tags")
    public ResponseEntity<?> getTagsFromUserId(@RequestAttribute BigDecimal userId){
        return ResponseEntity.ok(TagListResponse.builder().tags(tagService.findTags(userId)).build());
    }
}

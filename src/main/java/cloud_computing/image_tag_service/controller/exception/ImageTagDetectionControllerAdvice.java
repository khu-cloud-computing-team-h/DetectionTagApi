package cloud_computing.image_tag_service.controller.exception;

import cloud_computing.image_tag_service.dto.ErrorResponse;
import cloud_computing.image_tag_service.exception.ImageFileProcessingException;
import cloud_computing.image_tag_service.exception.TagDetectionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ImageTagDetectionControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<?> imageFileProcessingException(ImageFileProcessingException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder().message(ex.getMessage()).build());
    }

    @ExceptionHandler
    public ResponseEntity<?> tagDetectionException(TagDetectionException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder().message(ex.getMessage()).build());
    }
}

package cloud_computing.image_tag_service.dto;

import lombok.Builder;

@Builder
public record ErrorResponse(String message) {
}

package cloud_computing.image_tag_service.exception;

public class TagDetectionException extends RuntimeException{
    public TagDetectionException() {
    }

    public TagDetectionException(String message) {
        super(message);
    }

    public TagDetectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagDetectionException(Throwable cause) {
        super(cause);
    }
}

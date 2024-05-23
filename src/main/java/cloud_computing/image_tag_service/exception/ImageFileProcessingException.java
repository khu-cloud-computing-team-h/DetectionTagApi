package cloud_computing.image_tag_service.exception;

public class ImageFileProcessingException extends RuntimeException{
    public ImageFileProcessingException() {
    }

    public ImageFileProcessingException(String message) {
        super(message);
    }

    public ImageFileProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImageFileProcessingException(Throwable cause) {
        super(cause);
    }
}

package likelion14th.blog.exception;

public class PermissionDeniedException extends RuntimeException {
    public PermissionDeniedException(String message) {
      super(message);
    }
}

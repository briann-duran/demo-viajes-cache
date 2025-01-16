package brian.duran.commons_service.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ExceptionDetail(String message, LocalDateTime timestamp, HttpStatus httpStatus, String path) {
}

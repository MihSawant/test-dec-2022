package sawant.mihir.testdec2022.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public record PersonNotFoundHandler() {

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail personNotFound(IllegalArgumentException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404),
                ex.getMessage());
    }
}

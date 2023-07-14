package academy.devdojo.springboot2essentials.exception;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionsDetails{
    private final String fields;
    private final String fieldsMenssage;

}

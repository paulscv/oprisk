package scv.paul.opriskback.validator.common;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.internal.constraintvalidators.hv.LengthValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Annotation;

/**
 * Валидирует, что строка непустая и её длина находится в допустимом диапазоне.
 * @author Rostislav Dublin
 * @since 2017-03-15
 */
public class NotNullLengthValidator implements ConstraintValidator<NotNullLength, CharSequence> {

    Length parameters;
    LengthValidator validator;

    @Override
    public void initialize(NotNullLength constraintAnnotation) {
        parameters = new Length(){

            @Override
            public Class<? extends Annotation> annotationType() {
                return constraintAnnotation.annotationType();
            }

            @Override
            public int min() {
                return constraintAnnotation.min();
            }

            @Override
            public int max() {
                return constraintAnnotation.max();
            }

            @Override
            public String message() {
                return constraintAnnotation.message();
            }

            @Override
            public Class<?>[] groups() {
                return constraintAnnotation.groups();
            }

            @Override
            public Class<? extends Payload>[] payload() {
                return constraintAnnotation.payload();
            }
        } ;
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null){
            return false;
        }

        validator = new LengthValidator();
        validator.initialize(parameters);

        return validator.isValid(value, constraintValidatorContext);
    }
}

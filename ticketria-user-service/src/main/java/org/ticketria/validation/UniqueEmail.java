package org.ticketria.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {

    String message() default "{email.validation.constraints.NotUnique.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

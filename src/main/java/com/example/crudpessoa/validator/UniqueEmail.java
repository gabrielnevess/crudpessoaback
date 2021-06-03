package com.example.crudpessoa.validator;

import com.example.crudpessoa.model.Pessoa;
import com.example.crudpessoa.repository.PessoaRepository;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Constraint(validatedBy = UniqueEmail.UniqueEmailValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String message() default "Já existe uma pessoa cadastrada com este E-mail.";
    String fieldId();
    String fieldEmail();

    class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, Object> {
        private static final String MESSAGE_BAD_REQUEST_EMAIL = "Já existe uma pessoa cadastrada com este E-mail.";

        private String fieldId;
        private String fieldEmail;

        @Autowired
        private PessoaRepository pessoaRepository;

        public void initialize(UniqueEmail constraintAnnotation) {
            this.fieldId = constraintAnnotation.fieldId();
            this.fieldEmail = constraintAnnotation.fieldEmail();
        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            Integer id = (Integer) new BeanWrapperImpl(value).getPropertyValue(fieldId);
            String email = (String) new BeanWrapperImpl(value).getPropertyValue(fieldEmail);

            context.disableDefaultConstraintViolation();

            if (nonNull(id)) {
                Optional<Pessoa> optionalUser = this.pessoaRepository.findById(id);
                if (optionalUser.isPresent()) {
                    Pessoa pessoa = optionalUser.get();

                    if (!pessoa.getEmail().equalsIgnoreCase(email)) {
                        if (this.pessoaRepository.findByEmail(email).isPresent()) {
                            context.buildConstraintViolationWithTemplate(MESSAGE_BAD_REQUEST_EMAIL)
                                    .addPropertyNode(fieldEmail)
                                    .addConstraintViolation();
                            return false;
                        }
                    }
                }
            } else {
                if (this.pessoaRepository.findByEmail(email).isPresent()) {
                    context.buildConstraintViolationWithTemplate(MESSAGE_BAD_REQUEST_EMAIL)
                            .addPropertyNode(fieldEmail)
                            .addConstraintViolation();
                    return false;
                }
            }
            return true;
        }
    }

}
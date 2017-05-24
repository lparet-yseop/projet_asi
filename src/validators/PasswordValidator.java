package validators;

import javax.faces.validator.FacesValidator;

/**
 * Validator for Passwords
 * 
 * @author Lucas Grégoire
 */
@FacesValidator( value = "validators.password" )
public class PasswordValidator extends AbstractValidator {

    private static final String PASSWORD_PATTERN = "^[_A-Za-z0-9-@]+";

    /**
     * Constructs the PasswordValidator
     */
    public PasswordValidator() {
        super(PASSWORD_PATTERN);
    }
}

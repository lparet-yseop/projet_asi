package validators;

import javax.faces.validator.FacesValidator;

/**
 * Validator for Names
 * 
 * @author Lucas Grégoire
 */
@FacesValidator( value = "validators.name" )
public class NameValidator extends AbstractValidator {

    private static final String USERNAME_PATTERN = "^[_A-Za-z0-9-@]+";

    /**
     * Constructs the NameValidator
     */
    public NameValidator() {
        super(USERNAME_PATTERN);
    }

}

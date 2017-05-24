package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validators.password")
public class PasswordValidator extends AbstractValidator {

	private static final String PASSWORD_PATTERN = "^[_A-Za-z0-9-@]+" ;
	
	private Pattern pattern;
	private Matcher matcher;
	
	public PasswordValidator() {
		super();
	}
	


}

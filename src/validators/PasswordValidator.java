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
public class PasswordValidator implements Validator {

	private static final String PASSWORD_PATTERN = "^[_A-Za-z0-9-@]+" ;
	
	private Pattern pattern;
	private Matcher matcher;
	
	public PasswordValidator() {
		this.pattern = Pattern.compile(PASSWORD_PATTERN);
	}
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		 
		matcher = pattern.matcher(value.toString());
		if(!matcher.matches()){
 
			FacesMessage msg = 
				new FacesMessage("Password validation failed.", 
						"Password Validation failed please follow the contraint "+PASSWORD_PATTERN);
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 
		}
	}

}

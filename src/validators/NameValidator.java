package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.validator.FacesValidator;

@FacesValidator(value = "validators.name")
public class NameValidator extends AbstractValidator  {

	private static final String USERNAME_PATTERN = "^[_A-Za-z0-9-@]+" ;
	
	private Pattern pattern;
	private Matcher matcher;
 
	public NameValidator() {
		super();
	}

}

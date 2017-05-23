package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public abstract class AbstractValidator implements Validator {

	private static final String ABSTRACT_PATTERN = "^[_A-Za-z0-9-@]+";

	private Pattern pattern;
	private Matcher matcher;
	private String descriptor = "Ce champs "; // String used to generate error
												// messages

	/**
	 * Default constructor with use the default pattern "^[_A-Za-z0-9-@]+"
	 */
	public AbstractValidator() {
		this.pattern = Pattern.compile(ABSTRACT_PATTERN);
	}

	/**
	 * Abstract constructor which use a pattern in string format
	 * 
	 * @param pattern
	 */
	public AbstractValidator(String pattern) {
		this.pattern = Pattern.compile(pattern);
	}

	/**
	 * Absatrc constructor which used a pattern
	 * 
	 * @param pattern
	 */
	public AbstractValidator(Pattern pattern) {
		this.pattern = pattern;
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		matcher = pattern.matcher(value.toString());

		if (value.toString().length() == 0) {
			FacesMessage msg = new FacesMessage(this.descriptor + " ne peut pas être vide",
					"Vous devez les renseigner ");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		if (!matcher.matches()) {

			FacesMessage msg = new FacesMessage(this.descriptor + "validation failed.",
					this.descriptor + " Validation failed please follow the contraint " + this.pattern.toString());
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);

		}

	}

}

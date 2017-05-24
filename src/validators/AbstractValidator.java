package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Abstract validator class
 * 
 * @author Hugo Blanc, Lucas Grégoire
 */
public abstract class AbstractValidator implements Validator {

    private static final String ABSTRACT_PATTERN = "^[_A-Za-z0-9-@]+";
    private static final String VALIDATION_ERROR_MESSAGE = "The field validation failed.";
    private static final String EMPTY_MESSAGE = "This field cannot be empty.";
    private static final String NULL_VALUE = "This field cannot be empty.";    
    private static final String BAD_FORMAT_MESSAGE = "This field isn't in the correct format \"%s\".";

    private String formatMessage;
    protected Pattern pattern;
    protected Matcher matcher;

    /**
     * Default constructor with use the default pattern "^[_A-Za-z0-9-@]+"
     */
    public AbstractValidator() {
        this.pattern = Pattern.compile(ABSTRACT_PATTERN);
    }

    /**
     * Abstract constructor which use a pattern in string format
     * 
     * @param pattern The pattern
     */
    public AbstractValidator(String pattern) {
        this(pattern, pattern);
    }

    /**
     * Abstract constructor which use a pattern in string format
     * 
     * @param pattern The pattern
     * @param formatMessage The format message
     */
    public AbstractValidator(String pattern, String formatMessage) {
        this.formatMessage = formatMessage;
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
    public void validate( FacesContext context, UIComponent component, Object value ) throws ValidatorException {
        if (value == null) {
            FacesMessage msg = new FacesMessage(VALIDATION_ERROR_MESSAGE, NULL_VALUE);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

        matcher = pattern.matcher(value.toString());

        if (value.toString().length() == 0) {
            FacesMessage msg = new FacesMessage(VALIDATION_ERROR_MESSAGE, EMPTY_MESSAGE);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        if (!matcher.matches()) {
            FacesMessage msg = new FacesMessage(VALIDATION_ERROR_MESSAGE, String.format(BAD_FORMAT_MESSAGE, this.formatMessage));
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }

}

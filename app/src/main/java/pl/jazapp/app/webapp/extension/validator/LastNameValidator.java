package pl.jazapp.app.webapp.extension.validator;


import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.PropertyResourceBundle;
import java.util.regex.Pattern;

@FacesValidator("lastNameValidator")
public class LastNameValidator implements Validator<String> {

    private final Pattern onlyDialecticLettersPattern = Pattern.compile("[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]+");

    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if (!onlyDialecticLettersPattern.matcher(value).matches()) {
            var message = getMsg("pl.jazapp.app.webapp.extension.validator.LastNameValidator.CAN_ONLY_HAVE_DIALECTIC");
            throw new ValidatorException(new FacesMessage(message));
        }
    }

    private String getMsg(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        var msg = context.getApplication().evaluateExpressionGet(
                context, "#{msg}", PropertyResourceBundle.class);

        return msg.getString(key);
    }
}

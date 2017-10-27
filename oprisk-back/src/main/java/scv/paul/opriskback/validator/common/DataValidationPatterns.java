package scv.paul.opriskback.validator.common;

/**
 * Общеупотребимые регулярные выражения
 *
 * @author Rostislav Dublin
 * @since 2017-03-05
 */
public class DataValidationPatterns {
    /**
     * Проверка на корректность введённого Email
     */
    public static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final String PHONE_NUMBER_PATTERN = "^[0-9\\-\\+\\(\\)]{9,15}$";//"^\s*(?:\+?(\d{1,3}))?[-. (]*(\d{3})[-. )]*(\d{3})[-. ]*(\d{2})[-. ]*(\d{2})(?: *x(\d+))?\s*$""//";^\\d{10}$";
}

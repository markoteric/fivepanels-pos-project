package Domain.Misc;

import Domain.Exception.ApplicationException;

import java.util.function.Supplier;

import static java.lang.StringTemplate.STR;

public abstract class Assertion {

    // NULL ASSERTIONS -----------------

    // public static Object isNotNull(Object value, String paramName) {
    public static <T> T isNotNull(T value, String paramName) {
        if (value == null)
            throw new ApplicationException(STR."\{paramName} is null");

        return value;
    }


    // String Assertions -----------------------------------------------------------

    public static String isNotBlank(String value, String paramName) {
        isNotNull(value, paramName);

        if (value.isBlank())
            throw new ApplicationException(STR."\{paramName} is blank");

        return value;
    }

    public static char[] charsAreNotBlank(char[] value, String paramName) {
        if (char[].class.isInstance(value))
            throw new ApplicationException(STR."\{paramName} is blank");

        return value;
    }

    public static String hasMaxLength(String value, int maxLength, String paramName) {

        isNotBlank(value, paramName);

        if (value.length() > maxLength)
            throw new ApplicationException(STR."\{paramName} is greater than \{maxLength}");

        return value;
    }



    // Expression Assertions -------------------------------------------------------

    public static void isTrue(boolean expression, Supplier<String> errorMsg) {
        if (!expression)
            throw new ApplicationException(errorMsg.get());
    }

    public static double isMin(double value, double min, String valueName, String valueName2){
        if (value < min){
            throw new ApplicationException(valueName + " is greater than " + min);
        }// else if (value == value2){throw new ApplicationException(valueName + " is equal to " + value2);}
        return value;
    }

    public static double isMax(double value, double value2, String valueName, String valueName2){
        if (value < value2){
            throw new ApplicationException(valueName2 + " is greater than " + valueName);
        }else if (value == value2){throw new ApplicationException(valueName + " is equal to " + value2);}
        return value;
    }

    public static double valuesGreater0orEqual(double value, double value2, String valueName, String valueName2){
        if ( value > 0.0 && value2 > 0.0) {
            return value;
        } else if (value == 0.0 && value2 < 0.0) {
            throw new ApplicationException(valueName + " and " + valueName2 + " are greater than 0.");
        }
        return value;
    }
}

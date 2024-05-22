package Foundation.Assertion;

import Foundation.Exception.AssertionException;

import java.util.Collection;
import java.util.function.Supplier;

import static java.lang.StringTemplate.STR;

public abstract class Assertion {

    // Null-Assertions
    public static <T> T isNotNull(T value, String paramName) {

        if (value == null) {

            throw new AssertionException(STR. "\{ paramName } is null" );
        }

        return value;
    }

    public static <T> T isNull(T value, String paramName) {

        if (value != null) {

            throw new AssertionException(STR. "\{ paramName } is not null" );
        }

        return null;
    }

    // String-Assertions
    public static String isNotBlank(String value, String paramName) {

        isNotNull(value, paramName);
        if (value.isBlank()) {

            throw new AssertionException(STR. "\{ paramName } is blank" );
        }

        return value;
    }

    public static String hasMaxLength(String value, int maxLength, String paramName) {

        isNotNull(value, paramName);
        isNotBlank(value, paramName);
        if (value.length() > maxLength) {

            throw new AssertionException(STR. "\{ paramName } has max length of \{ maxLength }" );
        }

        return value;
    }

    public static String hasMinLength(String value, int minLength, String paramName) {

        isNotNull(value, paramName);
        isNotBlank(value, paramName);
        if (value.length() < minLength) {

            throw new AssertionException(STR. "\{ paramName } has min length of \{ minLength }" );
        }

        return value;
    }

    public static String containsNumber(String value, String paramName) {

        isNotNull(value, paramName);
        if (!value.matches(".*\\d.*")) {

            throw new AssertionException(STR. "\{ paramName } must contain at least one number.");
        }
        return value;
    }

    public static String containsLetter(String value, String paramName) {

        isNotNull(value, paramName);
        if (!value.matches(".*[a-zA-Z].*")) {

            throw new AssertionException(STR. "\{ paramName } must contain at least one letter.");
        }

        return value;
    }

    public static String containsSpecialCharacter(String value, String paramName) {

        isNotNull(value, paramName);
        if (!value.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {

            throw new AssertionException(STR. "\{ paramName } must contain at least one special character.");
        }

        return value;
    }

    // Boolean-Assertions
    public static void isTrue(boolean expression, Supplier<String> errorMsg) {

        if (!expression) {

            throw new AssertionException(errorMsg.get());
        }
    }

    public static void isFalse(boolean expression, Supplier<String> errorMsg) {

        if (expression) {

            throw new AssertionException(errorMsg.get());
        }
    }

    // Numeric-Assertions
    public static <T extends Number & Comparable<T>> T isGreaterThan(T value, T minValue, String paramName) {

        isNotNull(value, paramName);
        if (value.compareTo(minValue) <= 0) {

            throw new AssertionException(STR. "\{ paramName } must be greater than \{ minValue }" );
        }

        return value;
    }

    public static <T extends Number & Comparable<T>> T isLessThan(T value, T maxValue, String paramName) {

        isNotNull(value, paramName);
        if (value.compareTo(maxValue) >= 0) {

            throw new AssertionException(STR. "\{ paramName } must be less than \{ maxValue }" );
        }

        return value;
    }

    public static <T extends Number & Comparable<T>> T isInRange(T value, T minValue, T maxValue, String paramName) {

        isNotNull(value, paramName);
        if (value.compareTo(minValue) < 0 || value.compareTo(maxValue) > 0) {

            throw new AssertionException(STR. "\{ paramName } must be between \{ minValue } and \{ maxValue }" );
        }

        return value;
    }

    // Collection-Assertions
    public static <T> Collection<T> isNotEmpty(Collection<T> collection, String paramName) {

        isNotNull(collection, paramName);
        if (collection.isEmpty()) {

            throw new AssertionException(STR. "\{ paramName } must not be empty" );
        }

        return collection;
    }

    public static <T> Collection<T> hasSize(Collection<T> collection, int size, String paramName) {

        isNotNull(collection, paramName);
        if (collection.size() != size) {

            throw new AssertionException(STR. "\{ paramName } must have size \{ size }" );
        }

        return collection;
    }

    // Array-Assertions
    public static <T> T[] isNotEmpty(T[] array, String paramName) {

        isNotNull(array, paramName);
        if (array.length == 0) {

            throw new AssertionException(STR. "\{ paramName } must not be empty" );
        }

        return array;
    }
}

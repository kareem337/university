package com.universityapp.university.exception;

public class ErrorMessageUtil {

    private ErrorMessageUtil() {
        throw new UnsupportedOperationException("a utility class that cannot be instantiated");
    }
    public static final String CREDIT_INVALID_MESSAGE = "Credit must be greater than 0.";
    public static final String PAGINATION_INVALID_MESSAGE = "Page and page size must be greater than or equal to 0.";
    public static final String AUTHOR_DELETION_INVALID_MESSAGE = "Author cannot be deleted because it is assigned to one or more courses.";

    public static String formatCourseNotFoundMessage(int id) {
        return String.format("Course not found with id: %d", id);
    }

    public static String formatAuthorNotFoundMessage(int id) {
        return String.format("Author not found with id: %d", id);
    }

    public static String emailInvalidMessage(String email) {
        return String.format("Author not found with email: %s", email);
    }
}

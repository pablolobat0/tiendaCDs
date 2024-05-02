package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

  private static final String USERNAME_REGEX = "^[a-zA-Z0-9_]{3,20}$";
  private static final String EMAIL_REGEX =
      "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
  private static final String PASSWORD_REGEX =
      "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{3,}$";

  private static final Pattern USERNAME_PATTERN =
      Pattern.compile(USERNAME_REGEX);
  private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
  private static final Pattern PASSWORD_PATTERN =
      Pattern.compile(PASSWORD_REGEX);

  public static boolean isValidUsername(String username) {
    Matcher matcher = USERNAME_PATTERN.matcher(username);
    return matcher.matches();
  }

  public static boolean isValidEmail(String email) {
    Matcher matcher = EMAIL_PATTERN.matcher(email);
    return matcher.matches();
  }

  public static boolean isValidPassword(String password) {
    Matcher matcher = PASSWORD_PATTERN.matcher(password);
    return matcher.matches();
  }
}

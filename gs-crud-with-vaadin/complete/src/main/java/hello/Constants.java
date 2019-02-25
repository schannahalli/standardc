package hello;

public class Constants {

    public static final Long SUCCESS_EC = 99L ;
    public static final Long UNKNOW_ERROR_EC = 100L;
    public static final Long SIGNUP_FAILED_EC = 101L ;
    public static final Long LOGIN_FAILED_PASSWORD_INCORRECT_EC = 102L ;
    public static final Long MULTIPLE_LOGIN_ATTEMPTS_EC=103L ;
    public static final Long INCORRECT_USER_INFO_EC=104L ;
    public static final String SUCCESS_EM="success";
    public static final String UNKNOWN_ERROR_EM = "Unknow system error. Please try later";
    public static final String SIGNUP_FAILED_EM = "Signup failed. Please try again.";
    public static final String LOGIN_FAILED_PASSWORD_INCORRECT_EM = "Username/Password incorrect.Please try again";
    public static final String MULTIPLE_LOGIN_ATTEMPTS_EM = "Multiple failed login attempts. Your account is locked. Please wait for 2h";
    public static final String INCORRECT_USER_INFO="Incorrect user info. Please ensure the username/email/password are all valid. password has to be atleast 8 chars in length and alphanumberic with special characters in [#,.]";
}

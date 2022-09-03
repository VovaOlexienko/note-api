package com.github.notes.api.common.config.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RegexConstants {
    public static final String USERNAME_REGEX = "^(?=[a-zA-Z0-9._]{5,25}$)(?!.*[_.]{2})[^_.].*[^_.]$";
    public static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%_]).{10,50}$";
}

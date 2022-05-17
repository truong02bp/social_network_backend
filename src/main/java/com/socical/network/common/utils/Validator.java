package com.socical.network.common.utils;

import org.apache.commons.lang3.StringUtils;

public class Validator {

    private Validator() {
    }

    public static boolean isValidEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return false;
        }
        final String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return email.matches(regex);
    }
}

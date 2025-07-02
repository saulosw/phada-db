package br.com.saulo.phadadb.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class SecurityUtil {

    public static String hashPassword(String plainTextPassword) {
        String hashUserPassword = BCrypt.withDefaults().hashToString(12, plainTextPassword.toCharArray());

        return hashUserPassword;
    }

    public static boolean checkPassword(String plainTextPassword, String hashedPasswordFromDB) {
        BCrypt.Result isPasswordCheck = BCrypt.verifyer().verify(plainTextPassword.toCharArray(), hashedPasswordFromDB);

        if (isPasswordCheck.verified) { return true; }
        return false;
    }
}
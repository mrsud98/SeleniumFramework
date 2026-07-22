package com.iasys.utilities;

import java.util.Random;
import java.util.UUID;

public final class RandomDataUtil {

    private static final Random RANDOM = new Random();

    private RandomDataUtil() {}

    public static String randomEmail() {

        return "test"
                + System.currentTimeMillis()
                + "@gmail.com";

    }

    public static String randomMobile() {

        return "9"
                + (100000000L + RANDOM.nextInt(900000000));

    }

    public static String randomString(int length) {

        String chars =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {

            builder.append(
                    chars.charAt(
                            RANDOM.nextInt(chars.length())));

        }

        return builder.toString();

    }

    public static String randomAlphaNumeric(int length) {

        String chars =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < length; i++) {

            builder.append(
                    chars.charAt(
                            RANDOM.nextInt(chars.length())));

        }

        return builder.toString();

    }

    public static int randomNumber(int min,
                                   int max) {

        return RANDOM.nextInt(max - min + 1) + min;

    }

    public static String uuid() {

        return UUID.randomUUID().toString();

    }

}
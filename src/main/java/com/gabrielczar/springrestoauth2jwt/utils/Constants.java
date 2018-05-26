package com.gabrielczar.springrestoauth2jwt.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Constants {
    public static final Set<String> SAFE_METHODS = new HashSet<>(Arrays.asList("GET", "HEAD", "TRACE", "OPTIONS"));

    public static final String CSRF_COOKIE = "CSRF-TOKEN";
    public static final String CSRF_HEADER = "X-CSRF-TOKEN";

}

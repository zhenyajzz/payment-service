package com.payment.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseUtil {

    public static final Map<String, Integer> OK = Collections.singletonMap("status", 200);
}

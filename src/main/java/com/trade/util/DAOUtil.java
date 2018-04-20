package com.trade.util;

import java.util.UUID;

public class DAOUtil {

    public static String generateId() {

        String rawId = UUID.randomUUID().toString();
        return rawId.replace("-", "");

    }
}

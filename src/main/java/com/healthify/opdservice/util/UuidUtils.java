package com.healthify.opdservice.util;

import java.util.UUID;

public class UuidUtils {
    public static  String getnewUuid(){
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

}

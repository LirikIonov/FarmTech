package com.farm.cow.breeding.service;

import java.math.BigInteger;

public interface CowLifeService {
    void addCow(BigInteger parentCowId, BigInteger childCowId, String childNickName);

    Integer getCowsCount();

    String getFarmData();
    void removeCow(BigInteger id);
}

package com.farm.cow.breeding.collection;

import com.farm.cow.breeding.dto.CowDTO;

import java.math.BigInteger;

public interface CowsCollection {
    boolean addCow(CowDTO cowDTO);
    boolean containsId(BigInteger id);

    String getAllAsString();

    int getCount();
    BigInteger removeCowById(BigInteger id);
}

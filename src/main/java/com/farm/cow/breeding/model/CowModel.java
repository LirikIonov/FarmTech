package com.farm.cow.breeding.model;

import java.math.BigInteger;

public class CowModel {
    BigInteger cowId;
    String nickName;

    public CowModel(BigInteger cowId, String nickName) {
        this.cowId = cowId;
        this.nickName = nickName;
    }
}

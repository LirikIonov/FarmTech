package com.farm.cow.breeding.dto;

import java.math.BigInteger;
import java.util.Objects;

public class CowDTO {
    private final BigInteger cowId;
    private final String nickName;
    private final BigInteger parentId;

    public BigInteger getCowId() {
        return cowId;
    }

    public String getNickName() {
        return nickName;
    }

    public BigInteger getParentId() {
        return parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CowDTO)) return false;
        CowDTO cowDTO = (CowDTO) o;
        return getCowId().equals(cowDTO.getCowId()) &&
                getNickName().equals(cowDTO.getNickName()) &&
                getParentId().equals(cowDTO.getParentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCowId(), getNickName(), getParentId());
    }

    @Override
    public String toString() {
        return "cowId=" + cowId +
                ", nickName='" + nickName + '\'' +
                ", parentId=" + parentId;
    }

    public CowDTO(BigInteger cowId, String nickName, BigInteger parentId) {
        this.cowId = cowId;
        this.nickName = nickName;
        this.parentId = parentId;
    }
}

package com.farm.cow.breeding.collection.impl;

import com.farm.cow.breeding.collection.CowsCollection;
import com.farm.cow.breeding.dto.CowDTO;

import java.math.BigInteger;
import java.util.*;

public class CowsMap implements CowsCollection {
    Map<BigInteger, BigInteger> parentOfCow = new HashMap<>();
    Set<CowDTO> allCows = new HashSet<>();

    @Override
    public boolean addCow(CowDTO cowDTO) {
        parentOfCow.put(cowDTO.getCowId(), cowDTO.getParentId());
        return allCows.add(cowDTO);
    }

    @Override
    public boolean containsId(BigInteger id) {
        return parentOfCow.containsKey(id);
    }

    @Override
    public String getAllAsString() {
        StringBuilder farmData = new StringBuilder();
        for (CowDTO cowDTO : allCows) {
            farmData.append(cowDTO.toString()).append("\n");
        }
        return farmData.toString();
    }

    @Override
    public int getCount() {
        return allCows.size();
    }

    @Override
    public BigInteger removeCowById(BigInteger id) {
        allCows.removeIf(cowDTO -> cowDTO.getCowId().equals(id));
        return parentOfCow.remove(id);
    }
}

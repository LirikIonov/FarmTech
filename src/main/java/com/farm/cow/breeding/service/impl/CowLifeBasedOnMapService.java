package com.farm.cow.breeding.service.impl;

import com.farm.cow.breeding.service.CowLifeService;

import java.math.BigInteger;
import java.util.*;

public class CowLifeBasedOnMapService implements CowLifeService {
    Map<BigInteger, String> nameOfCow = new HashMap<>();
    Map<BigInteger, BigInteger> parentOfCow = new HashMap<>();

    Set<BigInteger> allCows = new HashSet<>();

    BigInteger specialCow = new BigInteger("-1");

    public void addCow(BigInteger parentCowId, BigInteger childCowId, String childNickName) {
        if (allCows.contains(childCowId)) {
            throw new RuntimeException("There is already cow with id = " + childCowId + " on the farm");
        }

        if (!allCows.contains(parentCowId) && !parentCowId.equals(specialCow)) {
            throw new RuntimeException("No parent cow with id = " + parentCowId + " is found on the farm");
        }

        nameOfCow.put(childCowId, childNickName);
        parentOfCow.put(childCowId, parentCowId);
        allCows.add(childCowId);
    }

    public Integer getCowsCount() {
        return allCows.size();
    }

    public String getFarmData() {
        StringBuilder farmData = new StringBuilder();
        for (BigInteger id : allCows) {
            farmData.append("id = ")
                    .append(id)
                    .append(", name = ")
                    .append(nameOfCow.get(id))
                    .append("\n");
        }
        return farmData.toString();
    }

    public void removeCow(BigInteger id) {
        if (!allCows.contains(id)) {
            throw new RuntimeException("Can't remove cow with id = " + id + ", because it is absent on the farm");
        }

        nameOfCow.remove(id);
        parentOfCow.remove(id);
        allCows.remove(id);
    }
}

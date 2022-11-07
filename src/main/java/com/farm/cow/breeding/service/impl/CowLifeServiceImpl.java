package com.farm.cow.breeding.service.impl;

import com.farm.cow.breeding.collection.CowsCollection;
import com.farm.cow.breeding.collection.impl.CowsMap;
import com.farm.cow.breeding.dto.CowDTO;
import com.farm.cow.breeding.service.CowLifeService;

import java.math.BigInteger;

public class CowLifeServiceImpl implements CowLifeService {
    CowsCollection cowsCollection = new CowsMap();
    BigInteger specialCow = BigInteger.ONE.negate();

    @Override
    public void addCow(BigInteger parentCowId, BigInteger childCowId, String childNickName) {
        if (cowsCollection.containsId(childCowId)) {
            throw new RuntimeException("There is already cow with id = " + childCowId + " on the farm");
        }

        if (!cowsCollection.containsId(parentCowId) && !parentCowId.equals(specialCow)) {
            throw new RuntimeException("No parent cow with id = " + parentCowId + " is found on the farm");
        }

        cowsCollection.addCow(new CowDTO(childCowId, childNickName, parentCowId));
    }

    @Override
    public Integer getCowsCount() {
        return cowsCollection.getCount();
    }

    @Override
    public String getFarmData() {
        if (getCowsCount() == 0) {
            return "No cows on the farm";
        }
        return cowsCollection.getAllAsString();
    }

    @Override
    public void removeCow(BigInteger id) {
        if (!cowsCollection.containsId(id)) {
            throw new RuntimeException("Can't remove cow with id = " + id + ", because it is absent on the farm");
        }
        cowsCollection.removeCowById(id);
    }
}

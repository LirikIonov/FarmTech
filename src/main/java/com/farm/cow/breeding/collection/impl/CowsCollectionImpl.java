package com.farm.cow.breeding.collection.impl;

import com.farm.cow.breeding.collection.CowsCollection;
import com.farm.cow.breeding.dto.CowDTO;

import java.math.BigInteger;

public class CowsCollectionImpl implements CowsCollection {
    private Node<CowDTO> head;
    private long size = 0;

    @Override
    public boolean addCow(CowDTO cowDTO) {
        Node<CowDTO> insertedNode = new Node<>(cowDTO);
        if (head == null) {
            head = insertedNode;
        }
        else {
            Node<CowDTO> node = head;
            while (node.getNext() != null) {
                node = node.getNext();
            }
            node.setNext(insertedNode);
        }
        size++;
        return true;
    }

    @Override
    public boolean containsId(BigInteger id) {
        Node<CowDTO> node = head;

        while (node != null) {
            if (node.getData().getCowId().equals(id)) {
                return true;
            }
            node = (Node<CowDTO>) node.getNext();
        }

        return false;
    }

    @Override
    public String getAllAsString() {
        StringBuilder res = new StringBuilder();

        Node<CowDTO> node = (Node<CowDTO>) head;
        res.append(node.getData().toString()).append("\n");

        while (node.getNext() != null) {
            node = (Node<CowDTO>) node.getNext();
            res.append(node.getData().toString()).append("\n");
        }

        return res.toString();
    }

    @Override
    public int getCount() {
        return (int) size;
    }

    @Override
    public BigInteger removeCowById(BigInteger id) {
        Node<CowDTO> node = head;

        if (node.getData().getCowId().equals(id)) {
            head = node.getNext();
            size--;
            return id;
        }

        while (node.getNext() != null) {
            Node<CowDTO> prevNode = node;
            node = (Node<CowDTO>) node.getNext();
            if (node.getData().getCowId().equals(id)) {
                prevNode.setNext(node.getNext());
                size--;
                return id;
            }
        }

        return null;
    }
}

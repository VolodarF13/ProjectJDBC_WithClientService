package com.client.jdbc.service;

import com.client.jdbc.dao.ClientDao;
import com.client.jdbc.entity.Client;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;

    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public long create(String name) {
        isNameValid(name);
        return clientDao.create(name);
    }

    @Override
    public String getById(long id) {
        isCorrectId(id);
        String name = clientDao.getById(id);
        if (name == null) {
            throw new IllegalArgumentException("No client found with id " + id);
        }
        return name;
    }

    @Override
    public void setName(long id, String name) {
        isCorrectId(id);
        isNameValid(name);
        clientDao.setName(id, name);
    }

    @Override
    public void deleteById(long id) {
        isCorrectId(id);
        clientDao.deleteById(id);
    }

    @Override
    public List<Client> listAll() {
        return clientDao.listAll();
    }

    private void isNameValid(String name) {
        if (name == null || name.isBlank() || name.length() <= 2 || name.length() >= 1000) {
            throw new IllegalArgumentException("Invalid name");
        }
    }

    private void isCorrectId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid id");
        }
    }
}

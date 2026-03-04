package com.client.jdbc.dao;

import com.client.jdbc.entity.Client;
import java.util.List;

public interface ClientDao {

    long create(String name);

    String getById(long id);

    void setName(long id, String name);

    void deleteById(long id);

    List<Client> listAll();
}

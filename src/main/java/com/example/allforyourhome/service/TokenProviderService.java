package com.example.allforyourhome.service;

public interface TokenProviderService {

    void authorize();

    void refresh();

    String getToken();
}

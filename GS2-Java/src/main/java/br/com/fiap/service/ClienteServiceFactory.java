package br.com.fiap.service;

public class ClienteServiceFactory {
    public ClienteServiceFactory() {
    }
    public static ClienteService create(){
        return new ClienteServiceImpl();
    }
}

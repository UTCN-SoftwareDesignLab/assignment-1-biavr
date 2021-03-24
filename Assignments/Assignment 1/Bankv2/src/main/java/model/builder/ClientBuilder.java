package model.builder;

import model.Client;

public class ClientBuilder {
    private Client client;

    public ClientBuilder setName(String name){
        client.setName(name);
        return this;
    }

    public ClientBuilder setCnp(String cnp){
        client.setCnp(cnp);
        return this;
    }

    public ClientBuilder setId(Long id){
        client.setId(id);
        return this;
    }

    public ClientBuilder setAddress(String address){
        client.setAddress(address);
        return this;
    }

    public Client build(){
        return client;
    }
}

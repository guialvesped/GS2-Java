package br.com.fiap.config;

public class DBConfig {

    private DBConfig() {
        throw new UnsupportedOperationException();
    }

    static String getUrl(){
        return "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    }

    static String getUser(){
        return "RM555357";
    }

    static String getPassword(){
        return "200805";
    }

}

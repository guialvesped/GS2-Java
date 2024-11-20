package br.com.fiap.model;

public class Cliente {
    private String email;
    private String senha;

    public Cliente(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String validarEmail() {
        if(email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            return email;
        }else {
            throw new RuntimeException("Email Inválido!");
        }

    }
    public String validarSenha() {
        if (senha != null && senha.length() >= 4){
            return senha;
        }else{
            throw new RuntimeException("Senha Inválida!");
        }
    }
}

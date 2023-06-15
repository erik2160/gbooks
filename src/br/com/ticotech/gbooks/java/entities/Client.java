package br.com.ticotech.gbooks.java.entities;

public class Client {
    private String cpf;
    private int points;

    public Client(String cpf, int points){
        this.cpf = cpf;
        this.points = points;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

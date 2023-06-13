package br.com.ticotech.gbooks.java.entities;

import br.com.ticotech.gbooks.java.model.UserRepository;

public class User {
  private final String name;
  private final String id;
  private final String password;
  private final int role;
  public String getId() {
    return id;
  }
  public String getPassword() {
    return password;
  }
  public int getRole() {
    return role;
  }
  public String getName() {
    return name;
  }

  public User(String name, String id, String password, int role) {
    this.name = name;
    this.id = id;
    this.password = password;
    this.role = role;
  }
}
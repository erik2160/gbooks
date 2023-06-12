package br.com.ticotech.gbooks.java.entities;

import br.com.ticotech.gbooks.java.model.UserRepository;

public class User {
  private String id;
  private String password;
  private UserRepository userRepository;
  public User(String id, String password) {
    this.id = id;
    this.password = password;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  public User() {
    userRepository = new UserRepository();
  }

  public boolean loginVerify(String id, String password) {
    for (User user : userRepository.getUsers()) {
      if (user.getId().equals(id) && user.getPassword().equals(password)) {
        return true;
      }
    }
    return false;
  }
}
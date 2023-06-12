package br.com.ticotech.gbooks.java.entities;

import br.com.ticotech.gbooks.java.model.UserRepository;

public class User {
  private final String id;
  private final String password;
  //private final String role;
  public UserRepository userRepository;
  public User(String id, String password) {
    this.id = id;
    this.password = password;
    //this.role = role;
  }
  public String getId() {
    return id;
  }
  public String getPassword() {
    return password;
  }
  /*
  public String getRole() {
    return role;
  }
   */

  public boolean loginVerify(String id, String password) {
    userRepository = new UserRepository();
    for (User user : userRepository.getUsers()) {
      if (user.getId().equals(id) && user.getPassword().equals(password)) {
        return true;
      }
    }
    return false;
  }
}
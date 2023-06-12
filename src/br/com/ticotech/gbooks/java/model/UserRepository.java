package br.com.ticotech.gbooks.java.model;

import br.com.ticotech.gbooks.java.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
  public List<User> users;
  public UserRepository() {
    users = new ArrayList<>();
    users.add(new User("Cashier", "Cashier123"));
    users.add(new User("321", "321"));
  }

  public List<User> getUsers() {
    return users;
  }
}

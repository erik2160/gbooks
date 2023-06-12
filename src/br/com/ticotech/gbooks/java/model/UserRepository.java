package br.com.ticotech.gbooks.java.model;

import br.com.ticotech.gbooks.java.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
  public List<User> users;
  public UserRepository() {
    users = new ArrayList<>();
    users.add(new User("Erik", "Cashier123"));
    /*
    users.add(new User("Manager", "Manager123"));
    users.add(new User("Erik", "Erik123"));
    users.add(new User("Lisandra", "Lisandra123"));
    users.add(new User("Fernando", "Fernando123"));
    users.add(new User("Edenilson", "Edenilson123"));
    users.add(new User("Jonathan", "Jonathan123"));
     */
  }

  public List<User> getUsers() {
    return users;
  }
  public List<User> getRole() {
    return getRole();
  }

}

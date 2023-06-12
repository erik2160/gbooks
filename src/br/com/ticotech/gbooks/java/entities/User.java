package br.com.ticotech.gbooks.java.entities;

import java.util.ArrayList;
import java.util.List;

public class User {
  private String id;
  private String password;
  public static List<User> userList = new ArrayList<>();
  public User(String id, String password) {
    this.id = id;
    this.password = password;
  }

  public static void createList () {
    addUser(new User("123", "123"));
    addUser(new User("321", "321"));
  }

  public static void addUser(User user) {
    userList.add(user);
    System.out.println("sucess");
  }

  public boolean login(String enteredId, String enteredPassword) {
    for (User user : userList) {
      if (user.getId().equals(enteredId) && user.getPassword().equals(enteredPassword)) {
        return true;
      }
    }
    return false;
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
}
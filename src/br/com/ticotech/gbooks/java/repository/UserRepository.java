package br.com.ticotech.gbooks.java.repository;

import br.com.ticotech.gbooks.java.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepository {
  public List<User> userList;
  public UserRepository() {
    userList = new ArrayList<>();
    userList.add(new User("John Doe", "123123", "cashier123", 1));
    userList.add(new User("Jane Doe", "321321","manager123", 2));
  }

  public User getUser(String id){
    for (User user : userList){
      if (Objects.equals(user.getId(),id)){
        return user;
      }
    }
    return null;
  }
}

package br.com.ticotech.gbooks.java.repository;

import br.com.ticotech.gbooks.java.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepository {
  public List<User> userList;
  public UserRepository() {
    userList = new ArrayList<>();
    userList.add(new User("Cashier", "123", "Cashier123", 1));
    userList.add(new User("Manager", "124","Manager123", 2));
    userList.add(new User("Erik", "125","Erik123", 2));
    userList.add(new User("Lisandra","126", "Lisandra123", 2));
    userList.add(new User("Fernando", "127","Fernes123", 2));
    userList.add(new User("Edenilson", "128", "Edenilson123", 2));
    userList.add(new User("Jonathan", "129", "Jonathan123", 2));
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

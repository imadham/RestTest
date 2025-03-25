package com.example.resttest.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Imad", LocalDate.now().minusYears(30)));
        users.add(new User(2, "AL", LocalDate.now().minusYears(13)));
        users.add(new User(3, "Ram", LocalDate.now().minusDays(25*363)));
        users.add(new User(4, "Ahmad", LocalDate.now().minusDays(30*333)));
    }
    public List<User> findAll(){
        return  users;
    }

    public User findById(Integer id) {
        return users.stream().filter(user -> user.getId()==id).findFirst().orElse(new User(0,"No User", LocalDate.now()));
    }
}

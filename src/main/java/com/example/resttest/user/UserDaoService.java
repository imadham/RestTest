package com.example.resttest.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int userCounter = 0;

    static {
        users.add(new User(++userCounter, "Imad", LocalDate.now().minusYears(30)));
        users.add(new User(++userCounter, "AL", LocalDate.now().minusYears(13)));
        users.add(new User(++userCounter, "Ram", LocalDate.now().minusDays(25*363)));
        users.add(new User(++userCounter, "Ahmad", LocalDate.now().minusDays(30*333)));
    }
    public List<User> findAll(){
        return  users;
    }

    public User findById(Integer id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public User save(User user){
        user.setId(++userCounter);
        users.add(user);
        return user;
    }

    public void deleteById(Integer id) {
        users.removeIf(user -> user.getId() == id);
    }
}

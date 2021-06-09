package io.jmix.backenddemo.provider;

import io.jmix.backenddemo.entity.User;
import io.jmix.bpm.provider.UserListProvider;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@UserListProvider(description = "Returns a list of users with matched email")
public class MyUserListProvider {

    @Autowired
    private DataManager dataManager;

    public List<String> findByEmailLike(String email) {
        return dataManager.load(User.class)
                .query("select u from User u where u.email like :email")
                .parameter("email", "%" + email + "%")
                .list()
                .stream().map(User::getUsername)
                .collect(Collectors.toList());
    }

}

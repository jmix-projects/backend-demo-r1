package io.jmix.backenddemo.provider;

import io.jmix.backenddemo.entity.User;
import io.jmix.bpm.provider.UserProvider;
import io.jmix.core.DataManager;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UserProvider(description = "Returns a user with the specified email")
public class MyUserProvider {

    @Autowired
    private DataManager dataManager;

    public String getUserByEmail(String email) {
        return dataManager.load(User.class)
                .query("select u from User u where u.email = :email")
                .parameter("email", email)
                .one()
                .getUsername();
    }

    public String getUserByEmailLikeAndNameStartingWith(String email, String username) {
        List<User> userList = dataManager.load(User.class)
                .query("select u from User u where u.email like :email and u.username like :username")
                .parameter("email", "%" + email + "%")
                .parameter("username", username + "%")
                .list();
        if (!CollectionUtils.isEmpty(userList)) {
            return userList.get(0).getUsername();
        } else {
            return "";
        }
    }

}

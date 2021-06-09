package io.jmix.backenddemo.provider;

import io.jmix.bpm.entity.UserGroup;
import io.jmix.bpm.provider.UserGroupListProvider;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@UserGroupListProvider(description = "Returns a user groups with the specified code")
public class MyUserGroupListProvider {

    @Autowired
    private DataManager dataManager;

    public List<String> findByCodeLike(String userGroupCode) {
        return dataManager.load(UserGroup.class)
                .query("select u from bpm_UserGroup u where u.code like :userGroupCode")
                .parameter("userGroupCode", "%" + userGroupCode + "%")
                .list()
                .stream().map(UserGroup::getCode)
                .collect(Collectors.toList());
    }

}

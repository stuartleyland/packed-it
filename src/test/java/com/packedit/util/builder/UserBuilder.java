package com.packedit.util.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.packedit.model.Authority;
import com.packedit.model.User;

public class UserBuilder {

    public static final String TEST_USER_USERNAME = "testUser";

    private static final Date NOW = new Date();

    private final User user = new User();

    private final String username = TEST_USER_USERNAME;
    private final String password = "test";
    private final String firstname = "Test";
    private final String lastname = "User";
    private final Boolean enabled = true;
    private final Date lastPasswordResetDate = NOW;
    private final List<Authority> authorities = new ArrayList<>();

    public UserBuilder withAuthorities(final Authority... authorities) {
        this.authorities.addAll(Arrays.asList(authorities));
        return this;
    }

    public User build() {
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEnabled(enabled);
        user.setLastPasswordResetDate(lastPasswordResetDate);
        user.setAuthorities(authorities);
        return user;
    }
}

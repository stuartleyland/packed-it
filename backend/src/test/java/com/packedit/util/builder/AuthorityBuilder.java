package com.packedit.util.builder;

import com.packedit.model.Authority;
import com.packedit.model.AuthorityName;

public class AuthorityBuilder {

    private final Authority authority = new Authority();

    private final AuthorityName name;

    public AuthorityBuilder(final AuthorityName name) {
        this.name = name;
    }

    public Authority build() {
        authority.setName(name);
        return authority;
    }
}

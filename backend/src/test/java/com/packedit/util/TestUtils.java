package com.packedit.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packedit.model.Authority;
import com.packedit.model.AuthorityName;
import com.packedit.model.Item;
import com.packedit.model.ItemCategory;
import com.packedit.model.PackingList;
import com.packedit.model.User;
import com.packedit.repository.AuthorityRepository;
import com.packedit.repository.ItemCategoryRepository;
import com.packedit.repository.ItemRepository;
import com.packedit.repository.PackingListRepository;
import com.packedit.repository.UserRepository;
import com.packedit.util.builder.AuthorityBuilder;
import com.packedit.util.builder.UserBuilder;

@Service
public class TestUtils {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PackingListRepository listRepository;

    @Autowired
    private ItemCategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Authority getOrCreateAuthority(final AuthorityName name) {
        Authority authority = authorityRepository.findByName(name);
        if (authority == null) {
            authority = new AuthorityBuilder(name).build();
            authority = authorityRepository.save(authority);
        }
        return authority;
    }

    public User getOrCreateTestUser() {
        User user = userRepository.findByUsername(UserBuilder.TEST_USER_USERNAME);
        if (user == null) {
            user = new UserBuilder().withAuthorities(getOrCreateAuthority(AuthorityName.ROLE_ADMIN)).build();
            user = userRepository.save(user);
        }
        return user;
    }

    public ItemCategory saveCategory(final ItemCategory category) {
        return categoryRepository.save(category);
    }

    public Item saveItem(final Item item) {
        return itemRepository.save(item);
    }

    public List<Item> saveItems(final List<Item> items) {
        return itemRepository.save(items);
    }

    public PackingList saveList(final PackingList list) {
        return listRepository.save(list);
    }
}

package com.packedit.util.matcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import com.packedit.model.Item;
import com.packedit.model.ListItem;

public class ListItemMatcher extends TypeSafeMatcher<List<ListItem>> {

    private final List<Item> items;

    private ListItemMatcher(final List<Item> items) {
        this.items = items;
    }

    @Override
    public void describeTo(final Description description) {
        final List<String> descriptions = new ArrayList<>();
        items.forEach(item -> descriptions.add(item.getDescription()));
        description.appendValueList("ListItems with the following descriptions: ", ", ", "", descriptions);
    }

    @Override
    protected void describeMismatchSafely(final List<ListItem> listItems, final Description mismatchDescription) {
        final List<String> descriptions = new ArrayList<>();
        listItems.forEach(listItem -> descriptions.add(listItem.getItem().getDescription()));
        mismatchDescription.appendValueList("was ", ", ", "", descriptions);
    };

    @Override
    protected boolean matchesSafely(final List<ListItem> listItems) {
        final List<Item> unmatchedItems = new ArrayList<>();
        final List<Item> matchedItems = new ArrayList<>();

        for (final ListItem listItem : listItems) {
            final Item item = listItem.getItem();
            if (!items.contains(item)) {
                unmatchedItems.add(item);
            } else {
                matchedItems.add(item);
            }
        }

        return (unmatchedItems.size() == 0) && (matchedItems.size() == items.size());
    }

    public static ListItemMatcher listItemsAreLinkedToItems(final Item... items) {
        return new ListItemMatcher(Arrays.asList(items));
    }
}

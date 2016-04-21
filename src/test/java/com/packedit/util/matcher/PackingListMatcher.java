package com.packedit.util.matcher;

import java.util.Objects;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import com.packedit.model.PackingList;

public class PackingListMatcher extends TypeSafeMatcher<PackingList> {

    private final PackingList expectedList;

    public PackingListMatcher(final PackingList expected) {
        this.expectedList = expected;
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("Expected a list with the following values: ");
        description.appendValue(
                "Description: " + expectedList.getDescription() + ", Start date: " + expectedList.getStartDate() + ", End date: " + expectedList.getEndDate());
    }

    @Override
    protected void describeMismatchSafely(final PackingList list, final Description mismatchDescription) {
        mismatchDescription.appendText("was ");
        mismatchDescription.appendValue("Description: " + list.getDescription() + ", Start date: " + list.getStartDate() + ", End date: " + list.getEndDate());
    }

    @Override
    protected boolean matchesSafely(final PackingList list) {

        return list.getDescription().equals(expectedList.getDescription())
                && Objects.equals(list.getStartDate(), expectedList.getStartDate())
                && Objects.equals(list.getEndDate(), expectedList.getEndDate());
    }

    public static PackingListMatcher editableListFieldsMatch(final PackingList expected) {
        return new PackingListMatcher(expected);
    }
}

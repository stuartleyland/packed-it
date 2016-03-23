package com.packedit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.packedit.model.Shipwreck;

public class ShipwreckStub {
    private static Map<Long, Shipwreck> wrecks = new HashMap<Long, Shipwreck>();
    private static Long idIndex = 3L;

    //populate initial wrecks
    static {
        final Shipwreck a = new Shipwreck(1L, "U869", "A very deep German UBoat", "FAIR", 200, 44.12, 138.44, 1994);
        wrecks.put(1L, a);
        final Shipwreck b = new Shipwreck(2L, "Thistlegorm", "British merchant boat in the Red Sea", "GOOD", 80, 44.12, 138.44, 1994);
        wrecks.put(2L, b);
        final Shipwreck c = new Shipwreck(3L, "S.S. Yongala", "A luxury passenger ship wrecked on the great barrier reef", "FAIR", 50, 44.12, 138.44, 1994);
        wrecks.put(3L, c);
    }

    public static List<Shipwreck> list() {
        return new ArrayList<Shipwreck>(wrecks.values());
    }

    public static Shipwreck create(final Shipwreck wreck) {
        idIndex += idIndex;
        wreck.setId(idIndex);
        wrecks.put(idIndex, wreck);
        return wreck;
    }

    public static Shipwreck get(final Long id) {
        return wrecks.get(id);
    }

    public static Shipwreck update(final Long id, final Shipwreck wreck) {
        wrecks.put(id, wreck);
        return wreck;
    }

    public static Shipwreck delete(final Long id) {
        return wrecks.remove(id);
    }
}

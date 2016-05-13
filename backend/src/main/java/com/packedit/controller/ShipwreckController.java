package com.packedit.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.packedit.model.Shipwreck;
import com.packedit.repository.ShipwreckRepository;

@RestController
@RequestMapping("api/v1/")
public class ShipwreckController {

    @Autowired
    private ShipwreckRepository shipwreckRepository;

    @RequestMapping(value = "shipwrecks", method = RequestMethod.GET)
    public List<Shipwreck> list() {
        return shipwreckRepository.findAll();
    }

    @RequestMapping(value = "shipwrecks", method = RequestMethod.POST)
    public Shipwreck create(@RequestBody final Shipwreck shipwreck) {
        return shipwreckRepository.saveAndFlush(shipwreck);
    }

    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.GET)
    public Shipwreck get(@PathVariable final long id) {
        return shipwreckRepository.findOne(id);
    }

    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.PUT)
    public Shipwreck update(@PathVariable final long id, @RequestBody final Shipwreck shipwreck) {
        final Shipwreck existingShipwreck = shipwreckRepository.findOne(id);
        BeanUtils.copyProperties(shipwreck, existingShipwreck);
        return shipwreckRepository.saveAndFlush(existingShipwreck);
    }

    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.DELETE)
    public Shipwreck delete(@PathVariable final long id) {
        final Shipwreck existingShipwreck = shipwreckRepository.findOne(id);
        shipwreckRepository.delete(existingShipwreck);
        return existingShipwreck;
    }
}
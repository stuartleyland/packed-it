package com.packedit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.packedit.model.PackingList;
import com.packedit.service.ListManager;

@RestController
@CrossOrigin(origins = "http://localhost:8050")
@RequestMapping("api/v1/")
public class ListController {

    @Autowired
    private ListManager listManager;

    @RequestMapping(value = "lists", method = RequestMethod.GET)
    public List<PackingList> list() {
        return listManager.findAll();
    }

    @RequestMapping(value = "lists", method = RequestMethod.POST)
    public PackingList create(@RequestBody final PackingList list) {
        return listManager.createOrUpdateList(list);
    }
}

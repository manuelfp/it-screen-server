package com.devcodes.training.itscreenserver.controllers;

import com.devcodes.training.itscreenserver.services.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v1/command")
public class CommandController {

    @Autowired
    private CommandService commandService;

    @GetMapping("/{inter}")
    public String runLS(@PathVariable String inter){
        return commandService.doExec(inter);
    }
}

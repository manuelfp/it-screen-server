package com.devcodes.training.itscreenserver.services;


import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Service
public class CommandServiceImpl implements CommandService {

    @Override
    public String doExec(String args) {
        try {
            String output = "";
            try (BufferedReader br = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("ifconfig " + args).getInputStream()))) {
                output =  br.lines().collect(Collectors.joining(System.lineSeparator()));
            }
            return output;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

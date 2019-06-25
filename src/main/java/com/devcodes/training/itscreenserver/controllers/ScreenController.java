package com.devcodes.training.itscreenserver.controllers;


import com.devcodes.training.itscreenserver.jpa.Screen;
import com.devcodes.training.itscreenserver.jpa.ScreenRepository;
import com.devcodes.training.itscreenserver.services.KafkaService;
import com.devcodes.training.itscreenserver.utils.ImageTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.io.*;

@CrossOrigin
@RestController
@RequestMapping("/rest/v1/screen")
public class ScreenController {

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private ScreenRepository screenRepository;

    @Value("${file.upload-dir}")
    private String pathImage;

    @GetMapping("/test/{dst}/{message}")
    public String sendMessageToScreen(@PathVariable String dst, @PathVariable String message){
        try{
            kafkaService.sendMessage(message, dst);
        }catch (Exception ex ){
            return "ERROR";
        }
        return "OK";
    }

    @GetMapping("/{dst}/{image}")
    public String getImage(@PathVariable String dst, @PathVariable String image) throws IOException {
        File file = new File(pathImage + "/" + image);
        if(file.isFile()) {
            InputStream imgStream = new FileInputStream(file.getPath());
            String imageAsString = ImageTool.encodeToString(ImageIO.read(imgStream),"png");
            kafkaService.sendMessage(imageAsString, dst);
        }else {
            return "ERROR";
        }
        return "OK";
    }

    @GetMapping("/connects")
    public Iterable<Screen> findAllScreenConnected(){
        return screenRepository.findAll();
    }
}

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
@RequestMapping("/rest/v1/fr")
public class FaceRecognitionController {

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private ScreenRepository screenRepository;

    @Value("${file.upload-dir}")
    private String pathImage;

    @Value("${kafka.topic.dst}")
    private String workerToFaceRecognition;

    @GetMapping("/direct/{dst}/{image}")
    public String doSendImageToTopicDst(@PathVariable String dst, @PathVariable String image) throws IOException {
        return doSendToChannel(image, dst);
    }

    @GetMapping("/{username}/{image}")
    public String doFaceRecognition(@PathVariable String username, @PathVariable String image) throws IOException {
        return doSendToChannel(image, workerToFaceRecognition);
    }

    private String doSendToChannel(@PathVariable String image, String workerToFaceRecognition) throws IOException {
        File file = new File(pathImage + "/" + image);
        if (file.isFile()) {
            //String typeImage = file.getName().split(".")[1];
            //if(typeImage.equals("jpeg") || !typeImage.equals("png")) {
                InputStream imgStream = new FileInputStream(file.getPath());
                String imageAsString = ImageTool.encodeToString(ImageIO.read(imgStream), "png");
                kafkaService.sendMessage(imageAsString, workerToFaceRecognition);
            //}else{
            //    return "FORMAT NOT SUPPORTED JET.";
            //}
        } else {
            return "ERROR";
        }
        return "OK";
    }

    @GetMapping("/connects")
    public Iterable<Screen> findAllScreenConnected(){
        return screenRepository.findAll();
    }
}

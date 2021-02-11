package com.diplom.controller;

//import com.diplom.model.FileInfo;

import com.diplom.dto.CustomerDto;
import com.diplom.model.Photo;
import com.diplom.service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping
@AllArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping("/upload")
    public String getUploadForm(Model model) {
        model.addAttribute("uploadForm", new CustomerDto());
        return "upload/uploadFile";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model, Principal principal) {
        String message = "";
        try {
            photoService.save(file, principal.getName());
            model.addAttribute("message", "Uploaded the file successfully:" + file.getOriginalFilename());
            return "upload/uploadFile";
        } catch (Exception e) {
            model.addAttribute("message", "Could not upload the file: " + file.getOriginalFilename());
            e.printStackTrace();
            return "upload/uploadFile";
        }
    }

    @GetMapping("/files/{filename}")
    //@ResponseBody
    public String getPhoto(@PathVariable String filename, Model model) throws IOException {
        Photo photo = photoService.load(filename);
        model.addAttribute("url", photo.getUrl());
        return "redirect:/customers/profile";
    }
}



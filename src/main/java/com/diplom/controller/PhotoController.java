package com.diplom.controller;

//import com.diplom.model.FileInfo;

import com.diplom.dto.CustomerDto;
import com.diplom.model.Photo;
import com.diplom.service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping
@AllArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    //@PostMapping("/upload")
    //public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    //    String message = "";
    //    try {
    //        photoService.save(file);
    //
    //        message = "Uploaded the file successfully: " + file.getOriginalFilename();
    //        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    //    } catch (Exception e) {
    //        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
    //        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    //    }
    //}

    @GetMapping("/upload")
    public String getUploadForm(Model model) {
        model.addAttribute("uploadForm", new CustomerDto());
        return "upload/uploadFile";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        String message = "";
        try {
            photoService.save(file);
            model.addAttribute("message", "Uploaded the file successfully:" + file.getOriginalFilename());
            return "upload/uploadFile";
        } catch (Exception e) {
            model.addAttribute("message", "Could not upload the file: " + file.getOriginalFilename());
            e.printStackTrace();
            return "upload/uploadFile";
        }
    }

    //@GetMapping("/files")
    //public ResponseEntity<List<FileInfo>> getListFiles() {
    //    List<FileInfo> fileInfos = photoService.loadAll().map(path -> {
    //        String filename = path.getFileName().toString();
    //        String url = MvcUriComponentsBuilder
    //                .fromMethodName(FileUploadController.class, "getFile", path.getFileName().toString()).build().toString();
    //
    //        return new FileInfo(filename, url);
    //    }).collect(Collectors.toList());
    //
    //    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    //}
    //

    @GetMapping("/files")
    public String getListFiles(Model model) {
        List<Photo> photos = photoService.loadAll().map(path -> {
            String url = MvcUriComponentsBuilder
                    .fromMethodName(PhotoController.class, "getPhoto", path.getFileName().toString()).build().toString();
            model.addAttribute("url", url);
            return new Photo(url);
        }).collect(Collectors.toList());
        //model.addAttribute("photos", photos);
        //});
        //model.addAttribute("photo", new Photo(url));
        return "upload/uploadFile";
    }



    @GetMapping("/files/{filename}")
    //@ResponseBody
    public String getPhoto(@PathVariable String filename,Model model) throws IOException {
        Photo photo = photoService.load(filename);
        model.addAttribute("url",  photo.getUrl());
        return "upload/uploadFile";
    }
}

//@GetMapping("/files/{filename:.+}")
//@ResponseBody
//public ResponseEntity<Resource> getFile(@PathVariable String filename) {
//    Resource file = photoService.load(filename);
//    return ResponseEntity.ok()
//            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);


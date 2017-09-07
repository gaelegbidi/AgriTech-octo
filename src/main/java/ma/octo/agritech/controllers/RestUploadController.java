//package ma.octo.agritech.controllers;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//public class RestUploadController {
//
//    private final Logger logger = LoggerFactory.getLogger(RestUploadController.class);
//
//    //Save the uploaded file to this folder
//    private static String UPLOADED_FOLDER = "F://temp//";
//
//    // 3.1.1 Single file upload
//    @PostMapping("/api/upload")
//    // If not @RestController, uncomment this
//    //@ResponseBody
//    public ResponseEntity<?> uploadFile(
//            @RequestParam("file") MultipartFile uploadfile) {
//
//        logger.debug("Single file upload!");
//
//        if (uploadfile.isEmpty()) {
//            return new ResponseEntity("please select a file!", HttpStatus.OK);
//        }
//
//        try {
//
//            saveUploadedFiles(Arrays.asList(uploadfile));
//
//        } catch (IOException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity("Successfully uploaded - " +
//                uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
//
//    }
//}
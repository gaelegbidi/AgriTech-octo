package ma.octo.agritech.controllers;

import ma.octo.agritech.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping(value = "api/storeImages")
@RestController
public class StoreImageApiController {

    @Autowired
    private StorageService storageService;


    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {

        return "/img/upload/" + storageService.store(file);
    }
}

package com.silverxmary.mowers.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.silverxmary.mowers.app.exception.ContentErrorException;
import com.silverxmary.mowers.app.exception.FileNameErrorException;
import com.silverxmary.mowers.app.service.IMowerService;
@RestController
public class MowersController {

	@Autowired
	private IMowerService mowerService;



    @PostMapping("/execute")
    public @ResponseBody byte[] postFile(@RequestParam("file") MultipartFile file) throws IOException, FileNameErrorException, ContentErrorException {
        return mowerService.execute(file);
    }
	
}

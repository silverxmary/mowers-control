package com.silverxmary.mowers.app.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.silverxmary.mowers.app.exception.ContentErrorException;
import com.silverxmary.mowers.app.exception.FileNameErrorException;
import com.silverxmary.mowers.app.model.Direction;
import com.silverxmary.mowers.app.model.Grass;
import com.silverxmary.mowers.app.model.Mower;

public interface IMowerService {
	byte[] execute(MultipartFile file) throws IOException, FileNameErrorException, ContentErrorException;

	List<String> getLines(MultipartFile file) throws IOException, ContentErrorException;

	Grass mowerGrassFactory(String line) throws ContentErrorException;


	List<Mower> createMower(Grass grass, List<String> instructions) throws ContentErrorException;

	Direction getDirectionFromString(String s) throws ContentErrorException;

	void moveMower(Mower mower, String line, Grass grass) throws ContentErrorException;

}

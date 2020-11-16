package com.silverxmary.mowers.app.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.silverxmary.mowers.app.exception.ContentErrorException;
import com.silverxmary.mowers.app.exception.FileNameErrorException;
import com.silverxmary.mowers.app.model.Direction;
import com.silverxmary.mowers.app.model.Grass;
import com.silverxmary.mowers.app.model.Mower;
import com.silverxmary.mowers.app.model.RectangleOfGrass;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MowerServiceImpl implements IMowerService{
	
	@Override
    public byte [] execute(MultipartFile file) throws IOException, FileNameErrorException, ContentErrorException {

        if(!Objects.requireNonNull(file.getOriginalFilename()).endsWith(".txt")){
            throw new FileNameErrorException("File with wrong format or extension.");
        }

        List<String> linesInFile = getLines(file);

        Grass grass = mowerGrassFactory(linesInFile.get(0));

        linesInFile.remove(0);

        List<Mower> mowers = createMower(grass, linesInFile);

        StringBuilder stringBuilder = new StringBuilder();

        for(Mower m : mowers) {
            stringBuilder.append(m.toString());
            stringBuilder.append(System.getProperty("line.separator"));
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(stringBuilder.toString().getBytes());

        return  byteArrayOutputStream.toByteArray();
    }
	@Override
    public List<String> getLines(MultipartFile file) throws IOException, ContentErrorException {
        InputStream inputStreamInitial = file.getInputStream();
        List<String> lines = new BufferedReader(new InputStreamReader(inputStreamInitial, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.toList());

        if(lines.size() %2 == 0) {
            throw new ContentErrorException("Wrong number of lines.");
        }

        return lines;
    }
	@Override
    public Grass mowerGrassFactory(String line) throws ContentErrorException {
        String[] grassArray = line.split(" ");

        if(grassArray.length == 2) {
            return new RectangleOfGrass(grassArray);
        } else {
            throw new ContentErrorException("The position is incorrect according to the grass coordinates.");
        }
    }
	@Override
    public List<Mower> createMower(Grass grass, List<String> instructions) throws ContentErrorException {
        List<Mower> mowers = new ArrayList<>();

        for(int i = 0; i < instructions.size(); i+=2) {
            String[] aStartPosition = instructions.get(i).split(" ");

            if(aStartPosition.length != 3)   {
                throw new ContentErrorException("Position regarding starting point incorrect.");
            }
            grass.setInitialPosition(instructions.get(i));

            Direction direction = getDirectionFromString(aStartPosition[2]);
            Mower mower = new Mower(grass, direction);

            moveMower(mower, instructions.get(i+1), grass);

            mowers.add(mower);
        }

        return mowers;
    }
    @Override
    public Direction getDirectionFromString(String s) throws ContentErrorException {
        return Direction.getDirectionFromString(s);
    }

    @Override
    public void moveMower(Mower mower, String line, Grass grass) throws ContentErrorException {
        char [] instructions = line.toCharArray();

        for(char c : instructions) {
            mower.move(c, grass);
        }
    }
}

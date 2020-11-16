package com.silverxmary.mowers.app.model;

import com.silverxmary.mowers.app.exception.ContentErrorException;
/**
 * 
 * @author soto lisber (silverxmary)
 *
 */
public class RectangleOfGrass extends Grass {

    public RectangleOfGrass(String[] dimensions) {
        int xMax = Integer.parseInt(dimensions[0]);
        int yMax = Integer.parseInt(dimensions[1]);

        this.dimensions = new int[] {xMax, yMax};
    }

    @Override
    public void setInitialPosition(String instruction) throws ContentErrorException {
        String[] aStartingPosition = instruction.split(" ");

        if(aStartingPosition.length != 3)   {
            throw new ContentErrorException("The line is incorrect according of the starting points, missing ine instrution.");
        }

        xMower = Integer.parseInt(aStartingPosition[0]);
        yMower = Integer.parseInt(aStartingPosition[1]);

        if(xMower > dimensions[0] || yMower > dimensions[1]) {
            throw new ContentErrorException("Position incorrect according the staring point.Out of grass size");
        }
    }

    @Override
    public int goEast(int z) {
        if(z < dimensions[0]) {
            return ++z;
        }
        return z;
    }

    @Override
    public int goWest(int z) {
        if(z > 0) {
            return --z;
        }
        return z;
    }

    @Override
    public int goNorth(int z) {
        if(z < dimensions[1]) {
            return ++z;
        }
        return z;
    }

    @Override
    public int goSouth(int z) {
        if(z > 0) {
            return --z;
        }
        return z;
    }
}

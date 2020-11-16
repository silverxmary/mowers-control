package com.silverxmary.mowers.app.model;

import com.silverxmary.mowers.app.exception.ContentErrorException;

/**
 * @author soto lisber (silverxmary)
 *
 */
public abstract class Grass {

    protected int[] dimensions;

    protected int xMower;

    protected int yMower;

    public abstract void setInitialPosition(String instruction) throws ContentErrorException;

    public abstract int goEast(int z);
    public abstract int goWest(int z);
    public abstract int goNorth(int z);
    public abstract int goSouth(int z);

	public int[] getDimensions() {
		return dimensions;
	}

	public void setDimensions(int[] dimensions) {
		this.dimensions = dimensions;
	}

	public int getxMower() {
		return xMower;
	}

	public void setxMower(int xMower) {
		this.xMower = xMower;
	}

	public int getyMower() {
		return yMower;
	}

	public void setyMower(int yMower) {
		this.yMower = yMower;
	}
    
    
    
    
    

}

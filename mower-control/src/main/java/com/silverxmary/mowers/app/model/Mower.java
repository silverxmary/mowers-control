package com.silverxmary.mowers.app.model;

import com.silverxmary.mowers.app.exception.ContentErrorException;
/**
 * 
 * @author soto lisber (silverxmary)
 *
 */
public class Mower {

    private int x;
    private int y;
    private Direction direction;

    public Mower(Grass grass, Direction direction) {
        this.x = grass.getxMower();
        this.y = grass.getyMower();
        this.direction = direction;
    }
    
    
      public Mower(int x, int y, Direction direction) {
		super();
		this.x = x;
		this.y = y;
		this.direction = direction;
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void move(char instruction, Grass grass) throws ContentErrorException {
        if(instruction == 'L') {
            goLeft();
        } else if(instruction == 'R') {
            goRight();
        } else if(instruction == 'M') {
            goForward(grass);
        } else {
            throw new ContentErrorException("Movement instruction incorrect.");
        }
    }

    private void goLeft() {
        direction = direction.goLeft();
    }

    private void goRight() {
        direction = direction.goRight();
    }

    private void goForward(Grass grass) {
        int [] array = direction.goForward(x, y, grass);

        x = array[0];
        y = array[1];
    }

    @Override
    public String toString() {
        return x + " " + y + " "+ direction.getDirectionCode();
    }
    
    
    
    
    
}

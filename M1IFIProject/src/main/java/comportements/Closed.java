package comportements;

import static java.lang.Math.PI;

import java.awt.Dimension;

import creatures.AbstractCreature;
import creatures.CustomCreature;

public class Closed implements IComportement{

	
	private AbstractCreature creature;
	
	private Closed(){};
	
	private static Closed instance = new Closed();
	
	public void behaviour(AbstractCreature creature, double x, double y) {
		setCreature(creature);
		Dimension s = creature.getEnvironment().getSize();

		double hw = s.getWidth() / 2;
		double hh = s.getHeight() / 2;
		
		if (x < -hw) {
			x = - 2*hw - x; 
			setDirectionBounceX();	
		} else if (x > hw) {
			x = 2*hw - x;
			setDirectionBounceX();
		}
		
		if (y < -hh) {
			y = - 2*hh - y;
			setDirectionBounceY();
		} else if (y > hh) {
			y = 2*hh - y;
			setDirectionBounceY();
		}
		creature.setPosition(x, y);
	}

	public static Closed getInstance(){return instance;}
	
	public void setCreature(CustomCreature creature){this.creature = creature;}
	
	public String getName() {
		return getClass().getName();
	}

	private void setDirectionBounceX() {
		if (creature.getDirection()>= PI)
			creature.setDirection(3*PI - creature.getDirection());
		else
			creature.setDirection(PI - creature.getDirection());
	}

	private void setDirectionBounceY() {
		creature.setDirection(PI * 2 - creature.getDirection());
	}
	
	public void setCreature(AbstractCreature creature){
		this.creature = creature;
	}
}
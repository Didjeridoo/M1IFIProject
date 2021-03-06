package deplacements;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import comportements.IComportement;

import creatures.AbstractCreature;

public class Stupid implements IDeplacement {

	private AbstractCreature creature;
	private IComportement comportement;

	public String getName() {
		// TODO Auto-generated method stub
		return getClass().getName();
	}

	public void act(AbstractCreature creature, IComportement comportement) {
		setCreature(creature);
		setComportement(comportement);
		move();
	}

	public void move() {
		Dimension s = creature.getEnvironment().getSize();
		double hw = s.getWidth() / 2;
		double hh = s.getHeight() / 2;
		double newX = creature.getPosition().getX() + creature.getSpeed() * cos(creature.getDirection());
		double newY = creature.getPosition().getY() + creature.getSpeed() * sin(creature.getDirection());
		
		if((newX < -hw)||(newX > hw)||(newY < -hh)||(newY > hh)){
			comportement.behaviour(creature, newX, newY);
		}else{
			creature.setPosition(new Point2D.Double(newX, newY));
		}
	}
	
	public void setCreature(AbstractCreature creature){
		this.creature = creature;
	}
	
	public void setComportement(IComportement comportement){
		this.comportement = comportement;
	}

}

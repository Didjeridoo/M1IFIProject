package comportements;

import static java.lang.Math.toRadians;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Point2D;

import org.junit.Before;
import org.junit.Test;

import comportement.Circular;
import creatures.BouncingCreature;
import creatures.Creature;
import creatures.visual.CreatureSimulator;

public class ToricComportementTest {

	CreatureSimulator environment = mock(CreatureSimulator.class);
	final double w = 200;
	final double h = 100;

	@Before
	public void setup() {
		when(environment.getSize()).thenReturn(new Dimension((int) w, (int) h));
	}

	@Test
	public void testDirectLeftUp() throws Exception {
		Creature creature = new Creature(environment, new Point2D.Double(
				-w / 2 + 1, 0), 10, toRadians(150), Color.RED, new Closed(),
				new Stupid());
		creature.move();

		assertEquals(toRadians(30), creature.getDirection(), 0.01);
		assertEquals(-w / 2 + 6, creature.getPosition().getX(), 2);
		assertEquals(-6, creature.getPosition().getY(), 2);
	}

	@Test
	public void testDirectLeftDown() throws Exception {
		Creature creature = new Creature(environment, new Point2D.Double(
				-w / 2 + 1, 0), 10, toRadians(210), Color.RED, new Closed(),
				new Stupid());
		creature.move();

		assertEquals(toRadians(330), creature.getDirection(), 0.01);
		assertEquals(-w / 2 + 6, creature.getPosition().getX(), 2);
		assertEquals(6, creature.getPosition().getY(), 2);
	}

	@Test
	public void testDirectRightUp() throws Exception {
		Creature creature = new Creature(environment,
				new Point2D.Double(w / 2 - 1, 0), 10, toRadians(30), Color.RED, new Closed(), new Stupid());
		creature.move();

		assertEquals(toRadians(150), creature.getDirection(), 0.01);
		assertEquals(w / 2 - 6, creature.getPosition().getX(), 2);
		assertEquals(-6, creature.getPosition().getY(), 2);
	}

	@Test
	public void testDirectRightDown() throws Exception {
		Creature creature = new Creature(environment,
				new Point2D.Double(w / 2 - 1, 0), 10, toRadians(330), Color.RED, new Closed(), new Stupid());
		creature.move();

		assertEquals(toRadians(210), creature.getDirection(), 0.01);
		assertEquals(w / 2 - 6, creature.getPosition().getX(), 2);
		assertEquals(6, creature.getPosition().getY(), 2);
	}

	// etc......

	public String getName() {
		return getClass().getName();
	}
}

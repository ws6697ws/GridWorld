/* �м�ʵѵStage2
 * RockHound������
 * Author���⿡Ω
 */
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import java.awt.Color;


public class RockHoundRunner
{
	//��������RockHound�����Լ�����ʯͷ�ͻ������͵Ķ���
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 8), new Rock());
        world.add(new Location(3, 3), new Rock());
        world.add(new Location(2, 8), new Flower(Color.BLUE));
        world.add(new Location(5, 5), new Rock());
        world.add(new Location(1, 5), new Rock());
        world.add(new Location(7, 2), new Flower(Color.YELLOW));
        world.add(new Location(4, 4), new RockHound());
        world.add(new Location(5, 8), new RockHound());
        world.show();
    }
} 

/* �м�ʵѵStage2
 * CircleBug������
 * Author���⿡Ω
 */
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;
import java.awt.Color;

public class CircleBugRunner
{
	//ʵ����һ����߳���Ϊ3��CircleBug�࣬��ʼλ��Ϊ��5��0��
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld(new UnboundedGrid());
        CircleBug bob = new CircleBug(3);
        world.add(new Location(5, 0), bob);
        world.show();
    }
} 

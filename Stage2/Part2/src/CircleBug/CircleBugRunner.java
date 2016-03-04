/* 中级实训Stage2
 * CircleBug测试类
 * Author：吴俊惟
 */
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;
import java.awt.Color;

public class CircleBugRunner
{
	//实例化一个侧边长度为3的CircleBug类，初始位置为（5，0）
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld(new UnboundedGrid());
        CircleBug bob = new CircleBug(3);
        world.add(new Location(5, 0), bob);
        world.show();
    }
} 

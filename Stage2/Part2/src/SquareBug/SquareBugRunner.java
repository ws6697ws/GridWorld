/* 中级实训Stage2
 * SquareBug测试类
 * Author：吴俊惟
 */
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

import java.awt.Color;

public class SquareBugRunner
{
	//实例化一个侧边长度为3的SquareBug类，位置随机
    public static void main(String[] args)
    {
    	//将地图设为无界限
        ActorWorld world = new ActorWorld(new UnboundedGrid());
        SquareBug alice = new SquareBug(3);
        //设为橙色
        alice.setColor(Color.ORANGE);
        //设置名字为alice
        world.add(alice);
        world.show();
    }
}  

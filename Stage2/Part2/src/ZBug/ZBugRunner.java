/* 中级实训Stage2
 * ZBug测试类
 * Author：吴俊惟
 */
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;
public class ZBugRunner
{
	//实例化一个侧边长度为100的ZBug类，初始位置为（0，0）
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        ZBug alice = new ZBug(100);
        //另外设置名字为alice，颜色为橙色
        alice.setColor(Color.ORANGE);
        world.add(new Location(0, 0), alice);
        world.show();
    }
}   

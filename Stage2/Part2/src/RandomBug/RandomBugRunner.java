/* 中级实训Stage2
 * RandomBug测试类
 * Author：吴俊惟
 */
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;
import java.awt.Color;
public class RandomBugRunner
{
	//实例化一个偏转角度列表为数组a的RandomBug类，其中a的元素为{1，3，2，2}；
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        int []a = {1, 3, 2, 2};
        RandomBug alice = new RandomBug(a);
        //另外设置颜色为橙色，名字为alice
        alice.setColor(Color.ORANGE);
        world.add(alice);
        
        world.show();
    }
}    

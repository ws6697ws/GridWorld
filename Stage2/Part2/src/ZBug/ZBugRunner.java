/* �м�ʵѵStage2
 * ZBug������
 * Author���⿡Ω
 */
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;
public class ZBugRunner
{
	//ʵ����һ����߳���Ϊ100��ZBug�࣬��ʼλ��Ϊ��0��0��
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        ZBug alice = new ZBug(100);
        //������������Ϊalice����ɫΪ��ɫ
        alice.setColor(Color.ORANGE);
        world.add(new Location(0, 0), alice);
        world.show();
    }
}   

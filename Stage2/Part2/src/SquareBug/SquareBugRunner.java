/* �м�ʵѵStage2
 * SquareBug������
 * Author���⿡Ω
 */
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

import java.awt.Color;

public class SquareBugRunner
{
	//ʵ����һ����߳���Ϊ3��SquareBug�࣬λ�����
    public static void main(String[] args)
    {
    	//����ͼ��Ϊ�޽���
        ActorWorld world = new ActorWorld(new UnboundedGrid());
        SquareBug alice = new SquareBug(3);
        //��Ϊ��ɫ
        alice.setColor(Color.ORANGE);
        //��������Ϊalice
        world.add(alice);
        world.show();
    }
}  

/* �м�ʵѵStage2
 * RandomBug������
 * Author���⿡Ω
 */
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;
import java.awt.Color;
public class RandomBugRunner
{
	//ʵ����һ��ƫת�Ƕ��б�Ϊ����a��RandomBug�࣬����a��Ԫ��Ϊ{1��3��2��2}��
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        int []a = {1, 3, 2, 2};
        RandomBug alice = new RandomBug(a);
        //����������ɫΪ��ɫ������Ϊalice
        alice.setColor(Color.ORANGE);
        world.add(alice);
        
        world.show();
    }
}    

/* �м�ʵѵStage2
 * RockHound��
 * Author���⿡Ω
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import java.util.ArrayList;
import java.awt.Color; 

public class RockHound extends Critter {
	//�������崦����
	public void processActors(ArrayList<Actor> actors) {
		//���ٽ�����Ϊʯͷ����Ļ��������Ƴ�
        for (Actor a : actors)
        {
            if (a instanceof Rock)
                a.removeSelfFromGrid();
        }
    }
}

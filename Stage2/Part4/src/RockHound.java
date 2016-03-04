/* 中级实训Stage2
 * RockHound类
 * Author：吴俊惟
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import java.util.ArrayList;
import java.awt.Color; 

public class RockHound extends Critter {
	//重载物体处理函数
	public void processActors(ArrayList<Actor> actors) {
		//若临近物体为石头对象的话，则将其移除
        for (Actor a : actors)
        {
            if (a instanceof Rock)
                a.removeSelfFromGrid();
        }
    }
}

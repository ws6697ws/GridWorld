/* 中级实训Stage2
 * ChameleonKid类
 * Author：吴俊惟
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.awt.Color;

public class ChameleonKid extends ChameleonCritter {
	//重载临近物体获取函数
    public ArrayList<Actor> getActors() {
    	//获取当前方向
    	int centerDir = getDirection();
    	ArrayList<Actor> neighbors = getGrid().getNeighbors(getLocation());
    	//新建对象列表，用于储存和返回
    	ArrayList<Actor> selects = new ArrayList<Actor>(); 
    	
    	//只选取前方或后方临近方格的物体加入到对象列表中
    	for (Actor act : neighbors) {
    		Location surrLoc = act.getLocation();
	        int surrDir = getLocation().getDirectionToward(surrLoc);
	        if (surrDir == centerDir || surrDir == centerDir % 180) {
	        	selects.add(act);
	        }
    	}
    	return selects;
    }
}
    


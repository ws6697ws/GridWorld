/* �м�ʵѵStage2
 * ChameleonKid��
 * Author���⿡Ω
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.awt.Color;

public class ChameleonKid extends ChameleonCritter {
	//�����ٽ������ȡ����
    public ArrayList<Actor> getActors() {
    	//��ȡ��ǰ����
    	int centerDir = getDirection();
    	ArrayList<Actor> neighbors = getGrid().getNeighbors(getLocation());
    	//�½������б����ڴ���ͷ���
    	ArrayList<Actor> selects = new ArrayList<Actor>(); 
    	
    	//ֻѡȡǰ������ٽ������������뵽�����б���
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
    


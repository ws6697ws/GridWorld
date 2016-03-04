/* 中级实训Stage2
 * KingCrab类
 * Author：吴俊惟
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Bug;
import java.util.ArrayList;
import java.awt.Color;

class KingCrab extends CrabCritter {
	
	//默认构造函数，默认颜色为黑色
    public KingCrab() {	
    	setColor(Color.BLACK);	
    }
    
    //临近物体处理函数
    public void processActors(ArrayList<Actor> actors)
    {
    	Grid g = getGrid();
        for (Actor a : actors) {
        	/*
        	System.out.println(actors.size());
        	
        	if (a instanceof Rock) {System.out.println("Rock");}
        	if (a instanceof Bug) {System.out.println("Bug");}
        	if (a instanceof Flower) {System.out.println("Flower");}
        	if (a instanceof Critter) {System.out.println("Critter");}
        	*/
        	
        	//若临近物体是石头或花朵类对象的话，将其从方格中移除
            if (a instanceof Rock || a instanceof Flower) {
            	a.removeSelfFromGrid();
            } else {
            	int dir = getLocation().getDirectionToward(a.getLocation());
            	Location loc = a.getLocation().getAdjacentLocation(dir);
            	//否则，若方格位置符合范围
            	if (getGrid().isValid(loc)) {
            		//若方格内无物体，则移动到该方格中
            		if (getGrid().get(loc) == null) {
            			a.moveTo(loc);		
            		}
            		//反之，将物体移除
            		else {
            			a.removeSelfFromGrid();
            		}
            	} 
            	//若方格位置超出范围，将自身对象移除
            	else {
            		a.removeSelfFromGrid();
            	}
            }
        }
    }
    
    //距离计算函数
    public int distanceBetween(Location a, Location b) {
    	//通过判断行与列距离的大小，得出两个位置之间的最短距离
		int rowLength = (int) (Math.abs(a.getRow() - b.getRow()));
	        int colLength = (int) (Math.abs(a.getCol() - b.getCol()));
	        return (rowLength > colLength ? rowLength : colLength);
	    }

}

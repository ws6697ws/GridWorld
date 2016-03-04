/* 中级实训Stage2
 * BlusterCritter类
 * Author：吴俊惟
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Rock;
import java.util.ArrayList;
import java.awt.Color; 

public class BlusterCritter extends Critter{
	//记录周围Critter对象数量的临界值
	int courage;
	//记录白化的速率
	private static final double LIGHTENING_FACTOR = 0.05;
	public BlusterCritter(int courage_) {
		courage = courage_;
	}
	
	//颜色变化函数
    public void processActors(ArrayList<Actor> actors) {
    	//得到周围Critter对象的数量
        int n = actors.size();
        //如果数量少于临界值，则进行白化
        if (n < courage) {
        	//获取当前的颜色，并将红、绿、蓝三种通道的值按照白化速率向255靠近
        	Color c = getColor();
        	int red = (int) c.getRed() + (int)((255-c.getRed()) * LIGHTENING_FACTOR) ;
        	int green = (int) c.getGreen() + (int) ((255-c.getGreen()) * LIGHTENING_FACTOR);
        	int blue = (int) c.getBlue() + (int) ((255-c.getBlue()) * LIGHTENING_FACTOR);
        	//重设白化后的颜色
        	setColor(new Color(red, green, blue));
        	
        //否则，对颜色进行黑化处理
	    } else {    
	    	//获取当前的颜色，并将红、绿、蓝三种通道的值按照白化速率向0靠近
	        Color c = getColor();
            int red = (int) (c.getRed() * (1 - LIGHTENING_FACTOR));
            int green = (int) (c.getGreen() * (1 - LIGHTENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 - LIGHTENING_FACTOR));
	        setColor(new Color(red, green, blue));   
	    }
    }
	
    //周围对象获取函数
    public ArrayList<Actor> getActors() {
    	//新建对象列表，用于储存和返回
        ArrayList<Actor> critters = new ArrayList<Actor>();
        Grid<Actor> g = getGrid();
        Location centerLoc = getLocation();
        //遍历两格以内的所有方格
        for (int row = centerLoc.getRow()-2; row <= (centerLoc.getRow()+2); row++) {
        	for (int col = centerLoc.getCol()-2; col <= (centerLoc.getCol()+2); col++) {
        		//当当前遍历方格不超出范围
            	if (g.isValid(new Location(row, col))) {
            		//且不为当前方格，同时方格内的物体不为Critter对象的话
            		if (g.get(new Location(row, col)) != this && g.get(new Location(row, col)) instanceof Critter) {
            			//加入到储存列表当中
            			critters.add(g.get(new Location(row, col)));
            		}
            	}
            }
        }
        return critters;
    }
} 

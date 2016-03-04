/* 中级实训Stage2
 * ChameleonCritter类
 * Author：吴俊惟
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.awt.Color;

public class ChameleonCritter extends Critter {
	//黑化速率
    private static final double DARKENING_FACTOR = 0.05;
    public void processActors(ArrayList<Actor> actors) {
    	//获取临近格子的物体数量
    	int n = actors.size();
    	//若数量为0，则当前对象按照黑化的速率进行黑化
        if (n == 0) {
        	Color c = getColor();
            int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
            int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
            setColor(new Color(red, green, blue));
        } 
        //否则，从任意选取一个临近物体的颜色作为自身的颜色
        else {    
        	int r = (int) (Math.random() * n);
        	Actor other = actors.get(r);
        	setColor(other.getColor());
        }
    }

    //移动函数
    public void makeMove(Location loc)
    {
    	//更改当前方向，并进行移动
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
}

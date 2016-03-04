/* 中级实训Stage2
 * QuickCrab类
 * Author：吴俊惟
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Rock;
import java.util.ArrayList;

class QuickCrab extends CrabCritter {
	
	//重载移动位置获取函数
    public ArrayList<Location> getMoveLocations()
    {
    	//新建可移动位置列表
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        //以左和右两个方向作为移动方向
        int[] dirs ={ Location.LEFT, Location.RIGHT };
        for (int dir : dirs) {
        	//获取沿当前遍历方向相差一步和相差两步的位置对象
        	Location neighborLoc = getLocation().getAdjacentLocation(getDirection() + dir);
        	Location twowayLoc = neighborLoc.getAdjacentLocation(getDirection() + dir);
        	
        	//若二者之中均无物体存在，则将相距两步的位置加入到位置列表中
            if (gr.isValid(neighborLoc) && gr.isValid(twowayLoc)) {
            	if (gr.get(neighborLoc) == null && gr.get(twowayLoc) == null) {
            		locs.add(twowayLoc);
            	}
            }
        }
        return locs;
    }    
}

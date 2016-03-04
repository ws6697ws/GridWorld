/* 中级实训Stage2
 * CrabCritter类
 * Author：吴俊惟
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

public class CrabCritter extends Critter
{
	//默认构造函数，默认颜色为红色
    public CrabCritter()
    {
        setColor(Color.RED);
    }

    //重载临近物体获取函数
    public ArrayList<Actor> getActors()
    {
    	//新建对象列表，用于储存和返回
        ArrayList<Actor> actors = new ArrayList<Actor>();
        
        //新建方向数组，记录要选取的方向
        int[] dirs = { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
        
        //只选取前方、左前方或右前方的物体加入到对象列表中
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }

        return actors;
    }

    //移动位置获取函数
    public ArrayList<Location> getMoveLocations()
    {
    	//新建下一步可移动位置列表
        ArrayList<Location> locs = new ArrayList<Location>();
        
        //从左方向和右方向的位置中选取，若位置不超出范围的话，则加入到可移动位置列表当中
        int[] dirs = { Location.LEFT, Location.RIGHT };
        for (Location loc : getLocationsInDirections(dirs))
            if (getGrid().get(loc) == null)
                locs.add(loc);

        return locs;
    }

    //移动函数
    public void makeMove(Location loc)
    {
    	//若目标位置为当前位置的话，则从左右两个方向中随机选取一个进行旋转
        if (loc.equals(getLocation()))
        {
            double r = Math.random();
            int angle;
            if (r < 0.5)
                angle = Location.LEFT;
            else
                angle = Location.RIGHT;
            setDirection(getDirection() + angle);
        }
        //否则直接移动
        else
            super.makeMove(loc);
    }
    
    //将方向列表转化为位置列表的函数
    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
    	//新建位置列表，用于储存和返回
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        //获取当前位置
        Location loc = getLocation();
    
        
        //遍历方向列表中的每个方向
        
        for (int d : directions)
        {
        	//如果以当前位置为中心，沿着遍历方向的临近位置没有超出范围的话，则加入到位置列表中
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc))
                locs.add(neighborLoc);
        }
        return locs;
    }    
}

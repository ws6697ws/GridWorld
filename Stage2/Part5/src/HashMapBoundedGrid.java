/* 中级实训Stage2
 * HashMapBoundedGrid类
 * Author：吴俊惟
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HashMapBoundedGrid<E> extends AbstractGrid<E> {
	//匹配每个位置及其对应信息的Map对象
    private Map<Location, E> occupantMap;
    //记录行数和列数
    private int rowSize;
    private int colSize;
    
    //构造函数，初始化行列数和Map对象
    public HashMapBoundedGrid(int rows, int cols) {
        rowSize = rows;
        colSize = cols;
        occupantMap = new HashMap<Location, E>();
    }

    
    //行数获取函数
    public int getNumRows()
    {
        return rowSize;
    }

    //列数获取函数
    public int getNumCols()
    {
        return colSize;
    }

    //判断位置是否有效函数
    public boolean isValid(Location loc)
    {
    	//若位置的行数和列数在范围以内则有效，反之无效
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    //已占用位置列表的获取函数
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();
        //将Map对象中的键值信息存放到新的列表当中
        for (Location a : occupantMap.keySet()) {
            theLocations.add(a);
        }
        return theLocations;
    }

    //已占用位置信息获取函数
    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return occupantMap.get(loc);
    }

    //已占用位置信息添加函数
    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");
        return occupantMap.put(loc, obj);
    }

    //已占用位置信息删除函数
    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return occupantMap.remove(loc);
    }
}

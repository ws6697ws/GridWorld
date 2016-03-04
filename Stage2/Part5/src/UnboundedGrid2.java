/* 中级实训Stage2
 * UnBoundedGrid2类
 * Author：吴俊惟
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;

import java.awt.Color;
import java.util.ArrayList;

public class UnboundedGrid2 <E> extends AbstractGrid<E> {
	//占用对象的二维数组
    private Object[][] occupantArray;
    //构造函数，占用对象数组的初始大小为16*16
    public UnboundedGrid2() {
        occupantArray = new Object[16][16];
    }

    //无行数和列数限制
    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
    }

    //判断位置是否有效函数
    public boolean isValid(Location loc)
    {
    	//若位置的行数和列数在范围以内则有效，反之无效
        return loc.getRow() >= 0 && loc.getCol() >= 0;
    }

    //已占用位置列表的获取函数
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> a = new ArrayList<Location>();
        
        /*遍历占用对象数组，将所有不为空下标对应的元素都以Location对象的
                     形式保存，并加入到列表中*/
        for (int r = 0; r < occupantArray.length; r++) {
            for (int c = 0; c < occupantArray[0].length; c++) {
                Location temp = new Location(r, c);
                if (get(temp) != null) {
                    a.add(temp);
                }
            }
        }
            
        return a;
    }

    //已占用位置信息获取函数
    public E get(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (loc.getRow() >= occupantArray.length || loc.getCol() >= occupantArray[0].length) {
            return null;
        }
        //直接通过下标访问，返回占用对象数组中对应行列的元素
        return (E) occupantArray[loc.getRow()][loc.getCol()];
    }

    //已占用位置信息添加函数
    public E put(Location loc, E obj)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (obj == null)
            throw new NullPointerException("obj == null");
       
        //当插入到行或列号大于当前范围时，对数组进行持续扩展操作
        while (loc.getRow() >= occupantArray.length || loc.getCol() >= occupantArray[0].length) {
            resize();
        }
        E old = remove(loc);
        //将对象添加到扩展后数组的对应位置当中
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return old;
    }

    //数组扩展操作
    public void resize() {
        int oldRow = occupantArray.length;
        int oldCol = occupantArray[0].length;
        //将行数和列数分别加倍
        Object[][] temp = new Object [oldRow*2][oldCol*2];
        
        //将原数组中的元素重新加入到扩展后的数组中
        for (int r = 0; r < oldRow; r++) {
            for (int c = 0; c < oldCol; c++) {
                Location loc = new Location(r, c);
                if (get(loc) != null) {
                    temp[r][c] = get(loc);                    
                }
            }
        }
        //更新二维数组为新的数组对象
        occupantArray = temp;
    }

    //已占用位置信息删除函数
    public E remove(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        E r = get(loc);
        //直接将二维数组中对应行列的位置置为null
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}

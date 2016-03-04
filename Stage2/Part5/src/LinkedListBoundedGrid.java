/* 中级实训Stage2
 * LinkedListBoundedGrid类
 * Author：吴俊惟
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;

import java.awt.Color;
import java.util.ArrayList;

public class LinkedListBoundedGrid<E> extends AbstractGrid<E> {
	//记录每个位置对应SparseGridNode对象的数组
    private SparseGridNode[] occupantArray;
    //记录行数和列数
    private int rowSize;
    private int colSize;
    
    //构造函数，初始化行列数和SparseGridNode数组
    public LinkedListBoundedGrid(int rows, int cols) {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        occupantArray = new SparseGridNode[rows];
        rowSize = rows;
        colSize = cols;
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
        //遍历SparseGridNode数组，以对应的行列信息为参数创建Location对象，并加入到列表中
        for (int r = 0; r < getNumRows(); r++) {
            SparseGridNode temp = occupantArray[r];
            while (temp != null) {
                int c = temp.getCol();
                theLocations.add(new Location(r, c));
                temp = temp.getNext();
            }
        }
        return theLocations;
    }

    //已占用位置信息获取函数
    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        //以行数作为索引中SparseGridNode数组中找到对应行的SparseGridNode对象
        SparseGridNode temp = occupantArray[loc.getRow()];
        //对当前行的SparseGridNode对象中所链接的所有列元素进行匹配，若匹配失败则返回null
        while (temp != null) {
            if (temp.getCol() == loc.getCol()) {
                return (E) temp.getOccupant();
            }
            temp = temp.getNext();
        }
        return null;
    }

    //已占用位置信息添加函数
    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");
        E old = get(loc);
        //记录列元素已存在和行对象为null的标志位
        boolean empty = false;
        boolean have = true;
        
        //以行数作为索引中SparseGridNode数组中找到对应行的SparseGridNode对象
        SparseGridNode temp = occupantArray[loc.getRow()]; 
        
        //若行对象为null，将标志位empty置为true
        if (temp == null) {
        	empty = true;
        }
        
        //若列元素为null，将标志位have置为false
        if (old == null) {
        	have = false;
        }

        //若已存在列元素，则对其信息进行更新
        if (have) {
        	while (temp != null) {
        		if (temp.getCol() == loc.getCol()) {
        			break;
        		}
        		temp = temp.getNext();
        	}    
        	temp.setOccupant(obj);
        } else {
        	/*若不存在列元素，且行对象也不存在，则要创建SparseGridNode对象，
        	并以当前Location对象信息为参数，加入到新建对象中*/
            if (empty) {
                occupantArray[loc.getRow()] = new SparseGridNode(loc.getCol(), obj, null);
            } 
            /*否则，则要以Location对象信息创建一个新的SparseGridNode实例，
                                并插入在在当前行对应的SparseGridNode链表的末尾*/
            else {
                while (temp.getNext() != null) {
                    temp = temp.getNext();
                    }
                temp.setNext(new SparseGridNode(loc.getCol(), obj, null));
                }
            }
        return old;
    }
    
    //已占用位置信息删除函数
    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");

        E r = get(loc);
        //若不存在列元素，则直接返回null
        if (r == null) {
        	return null;
        }
        //以行数作为索引中SparseGridNode数组中找到对应行的SparseGridNode对象
        SparseGridNode temp = occupantArray[loc.getRow()];
        
        /*若对应的列元素在链表头部，则直接将SparseGridNode数组中对应行对象的指针，
                      指向链表的第二个元素即可*/
        if (temp.getCol() == loc.getCol()) {
            occupantArray[loc.getRow()] = temp.getNext();
        } 
        
        /*否则，对链表进行遍历，当找到对应的列元素时，将其上一个元素的next指针
                     指向其下一个元素即可*/
        else {
        	SparseGridNode fast = temp.getNext();
        	while (fast != null) {
        		if (fast.getCol() == loc.getCol()) {
        			temp.setNext(fast.getNext());
        			break;
        		}
        		temp = temp.getNext();
        		fast = fast.getNext();
        	}
        }
        return r;
    }
}

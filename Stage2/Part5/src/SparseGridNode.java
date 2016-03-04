/* 中级实训Stage2
 * SparseGridNode类
 * Author：吴俊惟
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;

public class SparseGridNode
{
	//占用当前位置的对象
    private Object occupant;
    //当前位置的列号
    private int col;
    //符合当前行号的下一个SparseGridNode对象
    private SparseGridNode next;
    
    //构造函数，初始化列号、占用对象和下一SparseGridNode对象
    public SparseGridNode(int col_, Object occupant_, SparseGridNode next_) {
        col = col_;
        next = next_;
        occupant = occupant_;
    }
    //列数获取函数
    public int getCol() {
        return col;
    }
    //占用对象获取函数
    public Object getOccupant() {
        return occupant;
    }
    //下一SparseGridNode对象获取函数
    public SparseGridNode getNext() {
        return next;
    }
    //列数设置函数
    public void setCol(int col_) {
        col = col_;
    }
  //占用对象设置函数
    public void setOccupant(Object occupant_) {
        occupant = occupant_;
    }
  //下一SparseGridNode对象设置函数
    public void setNext(SparseGridNode next_) {
        next = next_;
    }

}

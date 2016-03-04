/* �м�ʵѵStage2
 * SparseGridNode��
 * Author���⿡Ω
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;

public class SparseGridNode
{
	//ռ�õ�ǰλ�õĶ���
    private Object occupant;
    //��ǰλ�õ��к�
    private int col;
    //���ϵ�ǰ�кŵ���һ��SparseGridNode����
    private SparseGridNode next;
    
    //���캯������ʼ���кš�ռ�ö������һSparseGridNode����
    public SparseGridNode(int col_, Object occupant_, SparseGridNode next_) {
        col = col_;
        next = next_;
        occupant = occupant_;
    }
    //������ȡ����
    public int getCol() {
        return col;
    }
    //ռ�ö����ȡ����
    public Object getOccupant() {
        return occupant;
    }
    //��һSparseGridNode�����ȡ����
    public SparseGridNode getNext() {
        return next;
    }
    //�������ú���
    public void setCol(int col_) {
        col = col_;
    }
  //ռ�ö������ú���
    public void setOccupant(Object occupant_) {
        occupant = occupant_;
    }
  //��һSparseGridNode�������ú���
    public void setNext(SparseGridNode next_) {
        next = next_;
    }

}

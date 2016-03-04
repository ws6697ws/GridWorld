/* �м�ʵѵStage2
 * LinkedListBoundedGrid��
 * Author���⿡Ω
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;

import java.awt.Color;
import java.util.ArrayList;

public class LinkedListBoundedGrid<E> extends AbstractGrid<E> {
	//��¼ÿ��λ�ö�ӦSparseGridNode���������
    private SparseGridNode[] occupantArray;
    //��¼����������
    private int rowSize;
    private int colSize;
    
    //���캯������ʼ����������SparseGridNode����
    public LinkedListBoundedGrid(int rows, int cols) {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        occupantArray = new SparseGridNode[rows];
        rowSize = rows;
        colSize = cols;
    }

    //������ȡ����
    public int getNumRows()
    {
        return rowSize;
    }

    //������ȡ����
    public int getNumCols()
    {
        return colSize;
    }

    //�ж�λ���Ƿ���Ч����
    public boolean isValid(Location loc)
    {
    	//��λ�õ������������ڷ�Χ��������Ч����֮��Ч
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    //��ռ��λ���б�Ļ�ȡ����
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();
        //����SparseGridNode���飬�Զ�Ӧ��������ϢΪ��������Location���󣬲����뵽�б���
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

    //��ռ��λ����Ϣ��ȡ����
    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        //��������Ϊ������SparseGridNode�������ҵ���Ӧ�е�SparseGridNode����
        SparseGridNode temp = occupantArray[loc.getRow()];
        //�Ե�ǰ�е�SparseGridNode�����������ӵ�������Ԫ�ؽ���ƥ�䣬��ƥ��ʧ���򷵻�null
        while (temp != null) {
            if (temp.getCol() == loc.getCol()) {
                return (E) temp.getOccupant();
            }
            temp = temp.getNext();
        }
        return null;
    }

    //��ռ��λ����Ϣ��Ӻ���
    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");
        E old = get(loc);
        //��¼��Ԫ���Ѵ��ں��ж���Ϊnull�ı�־λ
        boolean empty = false;
        boolean have = true;
        
        //��������Ϊ������SparseGridNode�������ҵ���Ӧ�е�SparseGridNode����
        SparseGridNode temp = occupantArray[loc.getRow()]; 
        
        //���ж���Ϊnull������־λempty��Ϊtrue
        if (temp == null) {
        	empty = true;
        }
        
        //����Ԫ��Ϊnull������־λhave��Ϊfalse
        if (old == null) {
        	have = false;
        }

        //���Ѵ�����Ԫ�أ��������Ϣ���и���
        if (have) {
        	while (temp != null) {
        		if (temp.getCol() == loc.getCol()) {
        			break;
        		}
        		temp = temp.getNext();
        	}    
        	temp.setOccupant(obj);
        } else {
        	/*����������Ԫ�أ����ж���Ҳ�����ڣ���Ҫ����SparseGridNode����
        	���Ե�ǰLocation������ϢΪ���������뵽�½�������*/
            if (empty) {
                occupantArray[loc.getRow()] = new SparseGridNode(loc.getCol(), obj, null);
            } 
            /*������Ҫ��Location������Ϣ����һ���µ�SparseGridNodeʵ����
                                ���������ڵ�ǰ�ж�Ӧ��SparseGridNode�����ĩβ*/
            else {
                while (temp.getNext() != null) {
                    temp = temp.getNext();
                    }
                temp.setNext(new SparseGridNode(loc.getCol(), obj, null));
                }
            }
        return old;
    }
    
    //��ռ��λ����Ϣɾ������
    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");

        E r = get(loc);
        //����������Ԫ�أ���ֱ�ӷ���null
        if (r == null) {
        	return null;
        }
        //��������Ϊ������SparseGridNode�������ҵ���Ӧ�е�SparseGridNode����
        SparseGridNode temp = occupantArray[loc.getRow()];
        
        /*����Ӧ����Ԫ��������ͷ������ֱ�ӽ�SparseGridNode�����ж�Ӧ�ж����ָ�룬
                      ָ������ĵڶ���Ԫ�ؼ���*/
        if (temp.getCol() == loc.getCol()) {
            occupantArray[loc.getRow()] = temp.getNext();
        } 
        
        /*���򣬶�������б��������ҵ���Ӧ����Ԫ��ʱ��������һ��Ԫ�ص�nextָ��
                     ָ������һ��Ԫ�ؼ���*/
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

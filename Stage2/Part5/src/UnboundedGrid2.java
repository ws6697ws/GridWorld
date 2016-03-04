/* �м�ʵѵStage2
 * UnBoundedGrid2��
 * Author���⿡Ω
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;

import java.awt.Color;
import java.util.ArrayList;

public class UnboundedGrid2 <E> extends AbstractGrid<E> {
	//ռ�ö���Ķ�ά����
    private Object[][] occupantArray;
    //���캯����ռ�ö�������ĳ�ʼ��СΪ16*16
    public UnboundedGrid2() {
        occupantArray = new Object[16][16];
    }

    //����������������
    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
    }

    //�ж�λ���Ƿ���Ч����
    public boolean isValid(Location loc)
    {
    	//��λ�õ������������ڷ�Χ��������Ч����֮��Ч
        return loc.getRow() >= 0 && loc.getCol() >= 0;
    }

    //��ռ��λ���б�Ļ�ȡ����
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> a = new ArrayList<Location>();
        
        /*����ռ�ö������飬�����в�Ϊ���±��Ӧ��Ԫ�ض���Location�����
                     ��ʽ���棬�����뵽�б���*/
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

    //��ռ��λ����Ϣ��ȡ����
    public E get(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (loc.getRow() >= occupantArray.length || loc.getCol() >= occupantArray[0].length) {
            return null;
        }
        //ֱ��ͨ���±���ʣ�����ռ�ö��������ж�Ӧ���е�Ԫ��
        return (E) occupantArray[loc.getRow()][loc.getCol()];
    }

    //��ռ��λ����Ϣ��Ӻ���
    public E put(Location loc, E obj)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (obj == null)
            throw new NullPointerException("obj == null");
       
        //�����뵽�л��кŴ��ڵ�ǰ��Χʱ����������г�����չ����
        while (loc.getRow() >= occupantArray.length || loc.getCol() >= occupantArray[0].length) {
            resize();
        }
        E old = remove(loc);
        //��������ӵ���չ������Ķ�Ӧλ�õ���
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return old;
    }

    //������չ����
    public void resize() {
        int oldRow = occupantArray.length;
        int oldCol = occupantArray[0].length;
        //�������������ֱ�ӱ�
        Object[][] temp = new Object [oldRow*2][oldCol*2];
        
        //��ԭ�����е�Ԫ�����¼��뵽��չ���������
        for (int r = 0; r < oldRow; r++) {
            for (int c = 0; c < oldCol; c++) {
                Location loc = new Location(r, c);
                if (get(loc) != null) {
                    temp[r][c] = get(loc);                    
                }
            }
        }
        //���¶�ά����Ϊ�µ��������
        occupantArray = temp;
    }

    //��ռ��λ����Ϣɾ������
    public E remove(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        E r = get(loc);
        //ֱ�ӽ���ά�����ж�Ӧ���е�λ����Ϊnull
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}

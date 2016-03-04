/* �м�ʵѵStage2
 * HashMapBoundedGrid��
 * Author���⿡Ω
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
	//ƥ��ÿ��λ�ü����Ӧ��Ϣ��Map����
    private Map<Location, E> occupantMap;
    //��¼����������
    private int rowSize;
    private int colSize;
    
    //���캯������ʼ����������Map����
    public HashMapBoundedGrid(int rows, int cols) {
        rowSize = rows;
        colSize = cols;
        occupantMap = new HashMap<Location, E>();
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
        //��Map�����еļ�ֵ��Ϣ��ŵ��µ��б���
        for (Location a : occupantMap.keySet()) {
            theLocations.add(a);
        }
        return theLocations;
    }

    //��ռ��λ����Ϣ��ȡ����
    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return occupantMap.get(loc);
    }

    //��ռ��λ����Ϣ��Ӻ���
    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");
        return occupantMap.put(loc, obj);
    }

    //��ռ��λ����Ϣɾ������
    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return occupantMap.remove(loc);
    }
}

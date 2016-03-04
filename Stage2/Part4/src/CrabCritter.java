/* �м�ʵѵStage2
 * CrabCritter��
 * Author���⿡Ω
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

public class CrabCritter extends Critter
{
	//Ĭ�Ϲ��캯����Ĭ����ɫΪ��ɫ
    public CrabCritter()
    {
        setColor(Color.RED);
    }

    //�����ٽ������ȡ����
    public ArrayList<Actor> getActors()
    {
    	//�½������б����ڴ���ͷ���
        ArrayList<Actor> actors = new ArrayList<Actor>();
        
        //�½��������飬��¼Ҫѡȡ�ķ���
        int[] dirs = { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
        
        //ֻѡȡǰ������ǰ������ǰ����������뵽�����б���
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }

        return actors;
    }

    //�ƶ�λ�û�ȡ����
    public ArrayList<Location> getMoveLocations()
    {
    	//�½���һ�����ƶ�λ���б�
        ArrayList<Location> locs = new ArrayList<Location>();
        
        //��������ҷ����λ����ѡȡ����λ�ò�������Χ�Ļ�������뵽���ƶ�λ���б���
        int[] dirs = { Location.LEFT, Location.RIGHT };
        for (Location loc : getLocationsInDirections(dirs))
            if (getGrid().get(loc) == null)
                locs.add(loc);

        return locs;
    }

    //�ƶ�����
    public void makeMove(Location loc)
    {
    	//��Ŀ��λ��Ϊ��ǰλ�õĻ�����������������������ѡȡһ��������ת
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
        //����ֱ���ƶ�
        else
            super.makeMove(loc);
    }
    
    //�������б�ת��Ϊλ���б�ĺ���
    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
    	//�½�λ���б����ڴ���ͷ���
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        //��ȡ��ǰλ��
        Location loc = getLocation();
    
        
        //���������б��е�ÿ������
        
        for (int d : directions)
        {
        	//����Ե�ǰλ��Ϊ���ģ����ű���������ٽ�λ��û�г�����Χ�Ļ�������뵽λ���б���
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc))
                locs.add(neighborLoc);
        }
        return locs;
    }    
}

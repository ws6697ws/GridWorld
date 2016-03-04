/* �м�ʵѵStage2
 * BlusterCritter��
 * Author���⿡Ω
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Rock;
import java.util.ArrayList;
import java.awt.Color; 

public class BlusterCritter extends Critter{
	//��¼��ΧCritter�����������ٽ�ֵ
	int courage;
	//��¼�׻�������
	private static final double LIGHTENING_FACTOR = 0.05;
	public BlusterCritter(int courage_) {
		courage = courage_;
	}
	
	//��ɫ�仯����
    public void processActors(ArrayList<Actor> actors) {
    	//�õ���ΧCritter���������
        int n = actors.size();
        //������������ٽ�ֵ������а׻�
        if (n < courage) {
        	//��ȡ��ǰ����ɫ�������졢�̡�������ͨ����ֵ���հ׻�������255����
        	Color c = getColor();
        	int red = (int) c.getRed() + (int)((255-c.getRed()) * LIGHTENING_FACTOR) ;
        	int green = (int) c.getGreen() + (int) ((255-c.getGreen()) * LIGHTENING_FACTOR);
        	int blue = (int) c.getBlue() + (int) ((255-c.getBlue()) * LIGHTENING_FACTOR);
        	//����׻������ɫ
        	setColor(new Color(red, green, blue));
        	
        //���򣬶���ɫ���кڻ�����
	    } else {    
	    	//��ȡ��ǰ����ɫ�������졢�̡�������ͨ����ֵ���հ׻�������0����
	        Color c = getColor();
            int red = (int) (c.getRed() * (1 - LIGHTENING_FACTOR));
            int green = (int) (c.getGreen() * (1 - LIGHTENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 - LIGHTENING_FACTOR));
	        setColor(new Color(red, green, blue));   
	    }
    }
	
    //��Χ�����ȡ����
    public ArrayList<Actor> getActors() {
    	//�½������б����ڴ���ͷ���
        ArrayList<Actor> critters = new ArrayList<Actor>();
        Grid<Actor> g = getGrid();
        Location centerLoc = getLocation();
        //�����������ڵ����з���
        for (int row = centerLoc.getRow()-2; row <= (centerLoc.getRow()+2); row++) {
        	for (int col = centerLoc.getCol()-2; col <= (centerLoc.getCol()+2); col++) {
        		//����ǰ�������񲻳�����Χ
            	if (g.isValid(new Location(row, col))) {
            		//�Ҳ�Ϊ��ǰ����ͬʱ�����ڵ����岻ΪCritter����Ļ�
            		if (g.get(new Location(row, col)) != this && g.get(new Location(row, col)) instanceof Critter) {
            			//���뵽�����б���
            			critters.add(g.get(new Location(row, col)));
            		}
            	}
            }
        }
        return critters;
    }
} 

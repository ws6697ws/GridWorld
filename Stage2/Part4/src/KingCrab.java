/* �м�ʵѵStage2
 * KingCrab��
 * Author���⿡Ω
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Bug;
import java.util.ArrayList;
import java.awt.Color;

class KingCrab extends CrabCritter {
	
	//Ĭ�Ϲ��캯����Ĭ����ɫΪ��ɫ
    public KingCrab() {	
    	setColor(Color.BLACK);	
    }
    
    //�ٽ����崦����
    public void processActors(ArrayList<Actor> actors)
    {
    	Grid g = getGrid();
        for (Actor a : actors) {
        	/*
        	System.out.println(actors.size());
        	
        	if (a instanceof Rock) {System.out.println("Rock");}
        	if (a instanceof Bug) {System.out.println("Bug");}
        	if (a instanceof Flower) {System.out.println("Flower");}
        	if (a instanceof Critter) {System.out.println("Critter");}
        	*/
        	
        	//���ٽ�������ʯͷ�򻨶������Ļ�������ӷ������Ƴ�
            if (a instanceof Rock || a instanceof Flower) {
            	a.removeSelfFromGrid();
            } else {
            	int dir = getLocation().getDirectionToward(a.getLocation());
            	Location loc = a.getLocation().getAdjacentLocation(dir);
            	//����������λ�÷��Ϸ�Χ
            	if (getGrid().isValid(loc)) {
            		//�������������壬���ƶ����÷�����
            		if (getGrid().get(loc) == null) {
            			a.moveTo(loc);		
            		}
            		//��֮���������Ƴ�
            		else {
            			a.removeSelfFromGrid();
            		}
            	} 
            	//������λ�ó�����Χ������������Ƴ�
            	else {
            		a.removeSelfFromGrid();
            	}
            }
        }
    }
    
    //������㺯��
    public int distanceBetween(Location a, Location b) {
    	//ͨ���ж������о���Ĵ�С���ó�����λ��֮�����̾���
		int rowLength = (int) (Math.abs(a.getRow() - b.getRow()));
	        int colLength = (int) (Math.abs(a.getCol() - b.getCol()));
	        return (rowLength > colLength ? rowLength : colLength);
	    }

}

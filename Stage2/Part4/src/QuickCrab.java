/* �м�ʵѵStage2
 * QuickCrab��
 * Author���⿡Ω
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Rock;
import java.util.ArrayList;

class QuickCrab extends CrabCritter {
	
	//�����ƶ�λ�û�ȡ����
    public ArrayList<Location> getMoveLocations()
    {
    	//�½����ƶ�λ���б�
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        //�����������������Ϊ�ƶ�����
        int[] dirs ={ Location.LEFT, Location.RIGHT };
        for (int dir : dirs) {
        	//��ȡ�ص�ǰ�����������һ�������������λ�ö���
        	Location neighborLoc = getLocation().getAdjacentLocation(getDirection() + dir);
        	Location twowayLoc = neighborLoc.getAdjacentLocation(getDirection() + dir);
        	
        	//������֮�о���������ڣ������������λ�ü��뵽λ���б���
            if (gr.isValid(neighborLoc) && gr.isValid(twowayLoc)) {
            	if (gr.get(neighborLoc) == null && gr.get(twowayLoc) == null) {
            		locs.add(twowayLoc);
            	}
            }
        }
        return locs;
    }    
}

/* �м�ʵѵStage2
 * ChameleonCritter��
 * Author���⿡Ω
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.awt.Color;

public class ChameleonCritter extends Critter {
	//�ڻ�����
    private static final double DARKENING_FACTOR = 0.05;
    public void processActors(ArrayList<Actor> actors) {
    	//��ȡ�ٽ����ӵ���������
    	int n = actors.size();
    	//������Ϊ0����ǰ�����պڻ������ʽ��кڻ�
        if (n == 0) {
        	Color c = getColor();
            int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
            int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
            setColor(new Color(red, green, blue));
        } 
        //���򣬴�����ѡȡһ���ٽ��������ɫ��Ϊ�������ɫ
        else {    
        	int r = (int) (Math.random() * n);
        	Actor other = actors.get(r);
        	setColor(other.getColor());
        }
    }

    //�ƶ�����
    public void makeMove(Location loc)
    {
    	//���ĵ�ǰ���򣬲������ƶ�
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
}

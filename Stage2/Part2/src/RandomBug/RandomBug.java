/* �м�ʵѵStage2
 * RandomBug��
 * Author���⿡Ω
 */
import info.gridworld.actor.Bug;
public class RandomBug extends Bug {
	//��¼�ƶ�����
    private int steps;
    //ƫת�Ƕ��б�
    private int[] turnList;

    //���캯������ʼ������Ϊ0��ƫת�Ƕ��б�Ϊ�����б����
    public RandomBug(int[] tlist)
    {
        int[] back = tlist.clone();
        steps = 0;
        turnList = back;
    }

    //ƫת��������������������ж�Ӧ������ƫת
    public void random(int times) {
		for (int i = 0; i < times; i++) {
			turn();
		}
    }
    
    //��Ϊ����
    public void act()
    {
    	//�����������ƫת�Ƕ��б�ĳ��ȣ���������
        if (steps == turnList.length) {
        	steps = 0;
        } 
        //����ƫת�������ж�Ӧƫת�����ҽ�������1
        random(turnList[steps]);
		steps++;
		
		//����ٵ��ø��౻�̳к���
		super.act();
    }
}  

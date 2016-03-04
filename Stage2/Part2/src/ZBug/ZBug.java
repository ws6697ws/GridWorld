/* �м�ʵѵStage2
 * ZBug��
 * Author���⿡Ω
 */
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;
public class ZBug extends Bug
{
	//��¼�ƶ�����
    private int steps;
    //��¼��ߵĳ���
    private int sideLength;
    //��¼��ǰ�ƶ��׶�
    private int stage;

    //���캯������ʼ���������ƶ��׶�Ϊ0����߳���Ϊ���������������
    public ZBug(int length)
    {
    	stage = 0;
        steps = 0;
        sideLength = length;
        setDirection(Location.EAST);
        //ֹͣ��־��Ϊfalse
        isStop = false;
    }

    //��Ϊ����
    public void act()
    {
    	
		if (!isStop) {
			//��δ�������ƶ��׶��Ҳ�����������߳��ȵĻ�
			if (stage <= 2 && steps < sideLength)
			{
			  //�統ǰ�����ƶ����������ƶ�
			  if (canMove()) {
			    move();
			    steps++;
			  } 
			  //���򣬽�ֹͣ��־��Ϊtrue
			  else {
			    isStop = true;
			  }
			}
			//����ǰ���ڵ�0�׶Σ������Ϊ���ϣ��׶���Ŀ��1����������
			else if (stage == 0)
			{
			    setDirection(Location.SOUTHWEST);
			    steps = 0;
			    stage++;
			}
		    //����ǰ���ڵ�1�׶Σ������Ϊ�����׶���Ŀ��1����������
			else if (stage == 1) {
			    setDirection(Location.EAST);
			    steps = 0;
			    stage++;
			}
		}
	
    }
}  

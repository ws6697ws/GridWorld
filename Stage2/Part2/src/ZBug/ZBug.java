/* 中级实训Stage2
 * ZBug类
 * Author：吴俊惟
 */
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;
public class ZBug extends Bug
{
	//记录移动步数
    private int steps;
    //记录侧边的长度
    private int sideLength;
    //记录当前移动阶段
    private int stage;

    //构造函数，初始化步数、移动阶段为0，侧边长度为输入参数，方向向东
    public ZBug(int length)
    {
    	stage = 0;
        steps = 0;
        sideLength = length;
        setDirection(Location.EAST);
        //停止标志设为false
        isStop = false;
    }

    //行为函数
    public void act()
    {
    	
		if (!isStop) {
			//若未达最终移动阶段且步数不超过侧边长度的话
			if (stage <= 2 && steps < sideLength)
			{
			  //如当前可以移动，则正常移动
			  if (canMove()) {
			    move();
			    steps++;
			  } 
			  //否则，将停止标志设为true
			  else {
			    isStop = true;
			  }
			}
			//若当前处于第0阶段，则方向变为西南，阶段数目加1，步数清零
			else if (stage == 0)
			{
			    setDirection(Location.SOUTHWEST);
			    steps = 0;
			    stage++;
			}
		    //若当前处于第1阶段，则方向变为东，阶段数目加1，步数清零
			else if (stage == 1) {
			    setDirection(Location.EAST);
			    steps = 0;
			    stage++;
			}
		}
	
    }
}  

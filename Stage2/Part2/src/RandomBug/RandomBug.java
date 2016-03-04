/* 中级实训Stage2
 * RandomBug类
 * Author：吴俊惟
 */
import info.gridworld.actor.Bug;
public class RandomBug extends Bug {
	//记录移动步数
    private int steps;
    //偏转角度列表
    private int[] turnList;

    //构造函数，初始化步数为0，偏转角度列表为输入列表参数
    public RandomBug(int[] tlist)
    {
        int[] back = tlist.clone();
        steps = 0;
        turnList = back;
    }

    //偏转函数，按照输入参数进行对应次数的偏转
    public void random(int times) {
		for (int i = 0; i < times; i++) {
			turn();
		}
    }
    
    //行为函数
    public void act()
    {
    	//如果步数等于偏转角度列表的长度，则将其置零
        if (steps == turnList.length) {
        	steps = 0;
        } 
        //调用偏转函数进行对应偏转，并且将步数加1
        random(turnList[steps]);
		steps++;
		
		//最后再调用父类被继承函数
		super.act();
    }
}  

/* 中级实训Stage2
 * CircleBug类
 * Author：吴俊惟
 */
import info.gridworld.actor.Bug;
public class CircleBug extends Bug
{
    //记录移动步数
    private int steps;
    //记录当前侧边的长度
    private int sideLength;

    //构造函数，初始化步数为0，侧边长度为输入参数
    public CircleBug(int length)
    {
        steps = 0;
        sideLength = length;
    }

    //行为函数
    public void act()
    {
        //如果未达侧边长度以及可以移动的话，继续移动
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        //否则侧转45度，继续判断
        else
        {   
            turn();
            steps = 0;
        }
    }
} 

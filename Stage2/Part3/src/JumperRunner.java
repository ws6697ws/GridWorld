/* 中级实训Stage2
 * Jumper测试类
 * Author：吴俊惟
 */
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

public class JumperRunner {
	public static void main(String args[]) {
		//初始化世界环境
		ActorWorld world = new ActorWorld();
		//添加Jumper、花朵、石头和Bug类对象到任意位置中
		world.add(new Jumper());
		world.add(new Flower());
		world.add(new Rock());
		world.add(new Bug());
		//进行显示
		world.show();
	}
}

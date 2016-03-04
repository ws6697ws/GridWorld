/* 中级实训Stage2
 * Jumper类
 * Author：吴俊惟
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
//Jumper类是Actor类的子类
public class Jumper extends Actor {
	//记录目标位置是否为花朵的变量，初始化为false
	boolean isFlower = false;
	//记录目标位置是否为石头的变量，初始化为false
	boolean isRock = false;
	//记录目标位置物体颜色的Color类变量，初始化为null
	Color layColor = null;
	
	//默认构造函数，默认颜色为橙色
	public Jumper() {
		setColor(Color.ORANGE);
	}
	//构造函数，输入参数为Color类，可设置颜色
	public Jumper(Color JumperColor) {
		setColor(JumperColor);
	}
	
	//偏转函数，将当前方向右转45度
	public void turn() {
		int dir = getDirection() + Location.HALF_RIGHT;
        setDirection(dir);
    }
	
	//行为函数
	public void act() {
		//如果可以跳跃，则执行跳跃动作
		if (canJump()) {
			jump();
		}
		//否则，执行偏转动作
		else {
			turn();
		}	
	}
	
	//跳跃函数
	public void jump() {
		//获得当前位置
		Location loc = getLocation();
		//获得沿当前跳跃方向前一步位置
		Location next = loc.getAdjacentLocation(getDirection());
		//获得沿当前跳跃方向前两步位置
		Location dest = next.getAdjacentLocation(getDirection());
		
		//保留当前位置的原物体
		Actor temp = null;
		
		//若原物体为花朵，则将temp初始化为Flower对象
		if (isFlower) {
		    temp = new Flower(layColor);		
		}
		//若原物体为石头，则将temp初始化为Rock对象
		if (isRock) {
		    temp = new Rock(layColor);
		}
		
		//如果沿当前跳跃方向前两步位置的物体为石头的话
		if (getGrid().get(dest) instanceof Rock) {
			//设置石头标志为true，并记录其颜色
		    layColor = getGrid().get(dest).getColor();
		    isRock = true;
		    isFlower = false;
        } 
		
		//如果沿当前跳跃方向前两步位置的物体为花朵的话
		else if (getGrid().get(dest) instanceof Flower) {
			//设置花朵标志为true，并记录其颜色
		    layColor = getGrid().get(dest).getColor();
		    isFlower = true;
		    isRock = false;
		} 
		//否则，则将花朵标志和石头标志都设为false
		else {
		    isFlower = false;
		    isRock = false;
		}
		//进行移动并对原物体执行放回操作 
		moveTo(dest);
		if (temp != null) {
		    temp.putSelfInGrid(getGrid(), loc);
		}
	}
	
	//判断是否可跳跃函数
	public boolean canJump() {
		//获得当前位置，前一步位置以及前两步位置
		Location loc = getLocation();
		Location next = loc.getAdjacentLocation(getDirection());
		Location dest = next.getAdjacentLocation(getDirection());
		
		//如果前一步或前两步位置是无效的话，返回false
		if (!getGrid().isValid(next) || !getGrid().isValid(dest)) {
			return false;
		}
		
		//前一步位置、前两步位置的原物体
		Actor neighbor = getGrid().get(next);
		Actor far = getGrid().get(dest);
		
		//判断前一步位置是否可穿过，前两步位置是否可达，只有当位置上位花朵、石头或空时才能穿过和到达
		boolean canThrough;
		boolean canReach;
		canThrough = (neighbor == null || neighbor instanceof Flower || neighbor instanceof Rock);
		canReach = (far == null) || (far instanceof Flower) || (far instanceof Rock);
		if (!canThrough) {
			return false;
		}
		if (canReach) {
            return true;
        } else {
			return false;
		}
		 
	}
} 

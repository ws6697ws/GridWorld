/* 中级实训Stage2
 * HashMapBoundedGrid测试类
 * Author：吴俊惟
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

public class HashMapBoundedGridRunner
{
  //创建一个HashMapBoundedGrid对象和Citter对象，进行对正常运行的测试
  public static void main(String[] args)
  {
    ActorWorld world = new ActorWorld();
    world.addGridClass("HashMapBoundedGrid");
    world.add(new Location(2, 2), new Critter());
    world.show();
  }
}

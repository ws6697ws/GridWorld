/* �м�ʵѵStage2
 * HashMapBoundedGrid������
 * Author���⿡Ω
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

public class HashMapBoundedGridRunner
{
  //����һ��HashMapBoundedGrid�����Citter���󣬽��ж��������еĲ���
  public static void main(String[] args)
  {
    ActorWorld world = new ActorWorld();
    world.addGridClass("HashMapBoundedGrid");
    world.add(new Location(2, 2), new Critter());
    world.show();
  }
}

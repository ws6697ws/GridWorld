/* �м�ʵѵStage2
 * UnboundedGrid2������
 * Author���⿡Ω
 */
import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

public class UnboundedGrid2Runner
{
  //����һ��UnboundedGrid2�����Citter���󣬽��ж��������еĲ���
  public static void main(String[] args)
  {
    ActorWorld world = new ActorWorld();
    world.addGridClass("UnboundedGrid2");
    world.add(new Location(2, 2), new Critter());
    world.show();
  }
}

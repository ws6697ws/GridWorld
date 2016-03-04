import java.io.IOException;

//八数码问题测试类
public class PuzzleRunner {

	public static void main(String[] args) throws IOException {

		//最终状态
		JigsawNode destNode = new JigsawNode(new int[]{9,1,2,3,4,5,6,7,8,0});
		//在这里可以设置测试的次数
		int testTimes = 5000;
		//在这里可以设置打乱的步数
		int scatterTimes = 50;
		
		//代表成功次数，失败的次数，总共搜索节点数
		int success = 0, failure = 0, totalSearchNum = 0;
		

		for (int i = 0; i < testTimes; i++) {
			
			//按照打乱次数打乱终止节点作为起始节点
			JigsawNode startNode = Jigsaw.scatter(destNode, scatterTimes);
			
			Jigsaw j = new Jigsaw(startNode, destNode);
			
			//四种搜索分别方法的测试
			//j.steepAscent();  
			//j.firstChoice();
			//j.advancedFirstChoice();
			j.simulatedAnnealing();
			
			//每测试一次根据搜索成功或失败对应的添加其状态的次数
			if (j.isCompleted()) {
				success++;
			} else {
				failure++;
			}
			//同时将当次的搜索节点是加入测试搜索节点的总数中
			totalSearchNum += j.getSearchedNodesNum();
		}
		//计算搜索成功率
		double all = success + failure;
		double s = success;
		double percentage = s / all;
		//计算平均搜索的节点数
		double total = totalSearchNum;
		double average = total / testTimes;
	  //输出结果到屏幕中
	  System.out.println("\nAverage search node number: " + average + "\n");
	  System.out.println("\nSuccess percentage: " + percentage + "\n");
	}
}
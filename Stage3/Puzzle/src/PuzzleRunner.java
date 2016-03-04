import java.io.IOException;

//���������������
public class PuzzleRunner {

	public static void main(String[] args) throws IOException {

		//����״̬
		JigsawNode destNode = new JigsawNode(new int[]{9,1,2,3,4,5,6,7,8,0});
		//������������ò��ԵĴ���
		int testTimes = 5000;
		//������������ô��ҵĲ���
		int scatterTimes = 50;
		
		//����ɹ�������ʧ�ܵĴ������ܹ������ڵ���
		int success = 0, failure = 0, totalSearchNum = 0;
		

		for (int i = 0; i < testTimes; i++) {
			
			//���մ��Ҵ���������ֹ�ڵ���Ϊ��ʼ�ڵ�
			JigsawNode startNode = Jigsaw.scatter(destNode, scatterTimes);
			
			Jigsaw j = new Jigsaw(startNode, destNode);
			
			//���������ֱ𷽷��Ĳ���
			//j.steepAscent();  
			//j.firstChoice();
			//j.advancedFirstChoice();
			j.simulatedAnnealing();
			
			//ÿ����һ�θ��������ɹ���ʧ�ܶ�Ӧ�������״̬�Ĵ���
			if (j.isCompleted()) {
				success++;
			} else {
				failure++;
			}
			//ͬʱ�����ε������ڵ��Ǽ�����������ڵ��������
			totalSearchNum += j.getSearchedNodesNum();
		}
		//���������ɹ���
		double all = success + failure;
		double s = success;
		double percentage = s / all;
		//����ƽ�������Ľڵ���
		double total = totalSearchNum;
		double average = total / testTimes;
	  //����������Ļ��
	  System.out.println("\nAverage search node number: " + average + "\n");
	  System.out.println("\nSuccess percentage: " + percentage + "\n");
	}
}
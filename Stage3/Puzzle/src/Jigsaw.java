

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Vector;
import java.util.Random;
//����������Ĳ�����
public class Jigsaw {
	JigsawNode beginJNode;		// ƴͼ����ʼ״̬�ڵ�
	JigsawNode endJNode;		// ƴͼ��Ŀ��״̬�ڵ�
	JigsawNode currentJNode;	// ƴͼ�ĵ�ǰ״̬�ڵ�
	private boolean isCompleted;	// ��ɱ�ǣ���ʼΪfalse;�����ɹ�ʱ�����ñ����Ϊtrue
	private int searchedNodesNum;	// �ѷ��ʽڵ����� ���Լ�¼���з��ʹ��Ľڵ������

    //���캯������ʼ���ҵ��յ�ΪFalse�������ڵ���Ϊ0��������ʼ����ֹ״̬���ҵ�ǰ״̬��ʼ��Ϊ��ʼ״̬
	public Jigsaw(JigsawNode bNode, JigsawNode eNode) {
		this.beginJNode = new JigsawNode(bNode);
		this.endJNode = new JigsawNode(eNode);
		this.currentJNode = new JigsawNode(bNode);
		this.isCompleted = false;
		this.searchedNodesNum = 0;
	}

    //����������������ĺ���������Ϊ���ô���ǰ�Ľڵ�ʹ��ҵĲ���
	public static JigsawNode scatter(JigsawNode jNode, int len) {
		int randomDirection;
		len += (int) (Math.random() * 2);
		JigsawNode jigsawNode = new JigsawNode(jNode);
		for (int t = 0; t < len; t++) {
			int[] movable = jigsawNode.canMove();
			do{randomDirection = (int) (Math.random() * 4);}
			while(0 == movable[randomDirection]);
			jigsawNode.move(randomDirection);
		}
		jigsawNode.setInitial();
		return jigsawNode;
	}

    //��õ�ǰ�ڵ��״̬
	public JigsawNode getCurrentJNode() {
		return currentJNode;
	}

	//������ʼ״̬
	public void setBeginJNode(JigsawNode jNode) {
		beginJNode = jNode;
	}

	//�����ʼ״̬
	public JigsawNode getBeginJNode() {
		return beginJNode;
	}

	//������ֹ״̬
	public void setEndJNode(JigsawNode jNode) {
		this.endJNode = jNode;
	}

	//�����ֹ״̬
	public JigsawNode getEndJNode() {
		return endJNode;
	}

	//��ȡ�Ƿ��ҵ��յ�
	public boolean isCompleted() {
		return isCompleted;
	}

	//�õ�����ʱ�����ڵ������
	public int getSearchedNodesNum() {
		return searchedNodesNum;
	}
	
	//�����չ�ڵ��б�ĺ���
	private Vector<JigsawNode> findFollowJNodes(JigsawNode jNode) {
		Vector<JigsawNode> followJNodes = new Vector<JigsawNode>();
		JigsawNode tempJNode;
		//���㵱ǰ�ڵ�Ĺ���ֵ
		estimateValue(jNode);
		//�����������ƶ��հ׿�ķ�ʽ��չ�ڵ�
		for(int i=0; i<4; i++){
			tempJNode = new JigsawNode(jNode);
			//�ж��ƶ������Ƿ�Ϸ������Ϸ�������ƶ������ص��Ƿ�Ϸ��Ľ��
			boolean flag = tempJNode.move(i);
			if(flag) {
				//�������ֵ�����뵽����չ�ڵ���б���
				estimateValue(tempJNode);
				followJNodes.addElement(tempJNode);
			}
		}
		return followJNodes;
	}
	
	//��������������
	public boolean steepAscent()  {
		//����ǰ��ʼ��
		searchedNodesNum = 0;
		isCompleted = false;
		while (true) {
			//����ǰ�ڵ�����սڵ���ͬ�����ҵ��յ㣬�˳�����
			if (this.currentJNode.equals(this.endJNode)) {
				isCompleted = true;
				break;
			}
			estimateValue(this.currentJNode);
			//�õ���ǰ�ڵ����չ�ڵ���б�
			Vector<JigsawNode> followJNodes = new Vector<JigsawNode>(); 
			followJNodes = this.findFollowJNodes(this.currentJNode);
			
			
			int min = this.currentJNode.getEstimatedValue();
			JigsawNode tempNode = new JigsawNode(this.currentJNode);
			//��������չ�ڵ��б�Ѱ�����ڵ�ǰ�ڵ�Ŀ���չ�ڵ������ŵĽڵ�
			for (int i = 0; i < followJNodes.size(); i++) {
				if (followJNodes.elementAt(i).getEstimatedValue() < min) {
					min = followJNodes.elementAt(i).getEstimatedValue();
					tempNode  = new JigsawNode(followJNodes.elementAt(i));
				}
				//ÿ����һ���ڵ���й��ۣ��������ڵ�����1
				searchedNodesNum++;
			}
			//���ҵ��ĸ��õĽڵ��뵱ǰ�ڵ���ͬ��֤�������˾ֲ����Ž⣬�˳�����
			if (tempNode.equals(this.currentJNode)) {
				isCompleted = false;
				break;
			}
			//������չ���õĽڵ�
			this.currentJNode = new JigsawNode(tempNode);
		}
		//������������������Ļ�������������������Ľڵ���
	    String result = isCompleted ? "Yes" : "No";
		System.out.println("Number of searched nodes: " + searchedNodesNum + "\n");
		System.out.println("Result: " + result + "\n");
		return isCompleted;
	}
	
	//��ѡ������
	public boolean firstChoice()  {
		//����ǰ��ʼ��
		searchedNodesNum = 0;
		isCompleted = false;
		while (true) {
			//����ǰ�ڵ�����սڵ���ͬ�����ҵ��յ㣬�˳�����
			if (this.currentJNode.equals(this.endJNode)) {
				isCompleted = true;
				break;
			}
			estimateValue(this.currentJNode);
			//�õ���ǰ�ڵ�Ŀ���չ�ڵ��б�
			Vector<JigsawNode> followJNodes = new Vector<JigsawNode>(); 
			followJNodes = this.findFollowJNodes(this.currentJNode);
			
			//���ڵõ�����չ�ڵ��б�ʱ�Ѿ�������ÿ���ڵ�����˹��ۣ���������ڵ���ֱ�Ӽ����б��С
			searchedNodesNum += followJNodes.size();
			//�½�����չ�ڵ������ڵ�ǰ�ڵ�Ľڵ��б�
			Vector<JigsawNode> betterJNodes = new Vector<JigsawNode>(); 
			int min = this.currentJNode.getEstimatedValue();
			
			//����һ�����չ�ڵ㣬�ӿ���չ�ڵ�����ȡ���ڵ�ǰ�ڵ�ĵ�
			for (int i = 0; i < followJNodes.size(); i++) {
				if (followJNodes.elementAt(i).getEstimatedValue() < min) {
					betterJNodes.addElement(followJNodes.elementAt(i));
				}
			}
			JigsawNode tempNode = new JigsawNode(this.currentJNode);
			
			//�ӽ��Žڵ��б���ͨ������������ѡȡһ���ڵ���չ
			if (betterJNodes.size() != 0) {
				Random rand = new Random();
				int ra = rand.nextInt(betterJNodes.size());
				tempNode = new JigsawNode(betterJNodes.elementAt(ra));
			}
			//���ҵ��ĸ��õĽڵ��뵱ǰ�ڵ���ͬ��֤�������˾ֲ����Ž⣬�˳�����
			if (tempNode.equals(this.currentJNode)) {
				isCompleted = false;
				break;
			}
			//������չ���õĽڵ�
			this.currentJNode = new JigsawNode(tempNode);
		}
		//������������������Ļ�������������������Ľڵ���
	    String result = isCompleted ? "Yes" : "No";
		System.out.println("Number of searched nodes: " + searchedNodesNum + "\n");
		System.out.println("Result: " + result + "\n");
		return isCompleted;
	}
	
	//�Ľ�����ѡ������
	public boolean advancedFirstChoice()  {
		//����ǰ��ʼ��
		searchedNodesNum = 0;
		isCompleted = false;
		int maxCount = 80; //�����������Ѱ�ҵȼ���չ�ڵ����
		int sameCount = 0; //��¼��ǰ����Ѱ�ҵȼ���չ�ڵ����
		while (true) {
			//����ǰ�ڵ�����սڵ���ͬ�����ҵ��յ㣬�˳�����
			if (this.currentJNode.equals(this.endJNode)) {
				isCompleted = true;
				break;
			}
			estimateValue(this.currentJNode);
			//�õ���ǰ�ڵ�Ŀ���չ�ڵ��б�
			Vector<JigsawNode> followJNodes = new Vector<JigsawNode>(); 
			followJNodes = this.findFollowJNodes(this.currentJNode);
			
			//���ڵõ�����չ�ڵ��б�ʱ�Ѿ�������ÿ���ڵ�����˹��ۣ���������ڵ���ֱ�Ӽ����б��С
			searchedNodesNum += followJNodes.size();
			//�½�����չ�ڵ������ڵ�ǰ�ڵ�Ľڵ��б�
			Vector<JigsawNode> betterJNodes = new Vector<JigsawNode>(); 
			int min = this.currentJNode.getEstimatedValue();
			
			//����һ�����չ�ڵ㣬�ӿ���չ�ڵ�����ȡ���ڻ��ߵ��ڵ�ǰ�ڵ�ĵ�
			for (int i = 0; i < followJNodes.size(); i++) {
				if (followJNodes.elementAt(i).getEstimatedValue() < min || 
					(followJNodes.elementAt(i).getEstimatedValue() == min && sameCount > maxCount)) {
					betterJNodes.addElement(followJNodes.elementAt(i));
				}
			}
			JigsawNode tempNode = new JigsawNode(this.currentJNode);
			
			//�ӽ��Žڵ��б���ͨ������������ѡȡһ���ڵ���չ
			if (betterJNodes.size() != 0) {
				Random rand = new Random();
				int ra = rand.nextInt(betterJNodes.size());
				tempNode = new JigsawNode(betterJNodes.elementAt(ra));
			}
			//���ҵ��ĸ��õĽڵ��뵱ǰ�ڵ���ͬ��֤�������˾ֲ����Ž⣬�˳�����
			if (tempNode.equals(this.currentJNode)) {
				isCompleted = false;
				break;
			}
			//���統ǰѡ�����չ�ڵ����ڵ�ǰ�ڵ㣬��sameCount��������
			if (currentJNode.getEstimatedValue() > tempNode.getEstimatedValue()) {
				sameCount = 0;
			} else {//���統ǰѡ�����չ�ڵ����ֵ�뵱ǰ�ڵ����ͬ����sameCount��1
				sameCount++;
			}
			
			//��չ���õĽڵ�
			this.currentJNode = new JigsawNode(tempNode);
			
		}
		//������������������Ļ�������������������Ľڵ���
	    String result = isCompleted ? "Yes" : "No";
		System.out.println("Number of searched nodes: " + searchedNodesNum + "\n");
		System.out.println("Result: " + result + "\n");
		return isCompleted;
	}

	//ģ���˻��㷨����
	public boolean simulatedAnnealing()  {
		searchedNodesNum = 0;
		isCompleted = false;
		double startTemperature = 20000; //��ʼ�¶�
		double rate = 0.999; //��ȴ����
		double endTemperature = 0.00001; //��ֹ����ʱ���¶�
		double currentTemperature = startTemperature; //��ǰ�¶ȣ���ʼ��Ϊ��ʼ�¶�
		
		while (currentTemperature > endTemperature) {
			//����ǰ�ڵ�����սڵ���ͬ�����ҵ��յ㣬�˳�����
			if (this.currentJNode.equals(this.endJNode)) {
				isCompleted = true;
				break;
			}
			
			estimateValue(this.currentJNode);
			
			//�õ���ǰ�ڵ�Ŀ���չ�ڵ��б�
			Vector<JigsawNode> followJNodes = new Vector<JigsawNode>(); 
			followJNodes = this.findFollowJNodes(this.currentJNode);
			int min = this.currentJNode.getEstimatedValue();
			
			//ͨ����������ӿ���չ�ڵ������ѡ��һ���ڵ�
			Random rand = new Random();
			int ra = rand.nextInt(followJNodes.size());
			estimateValue(followJNodes.elementAt(ra));
			//�������ѡ�Ľڵ�͵�ǰ�ڵ�Ĳ��
			int temp = followJNodes.elementAt(ra).getEstimatedValue();
			double difference = min - temp;
			//��ѡ��Ľڵ����ڵ�ǰ�ڵ㣬��������������ĸ���С���Բ����Ե�ǰ�¶�Ϊָ���Ķ���ֵʱ����ѡ�����������ڵ���չ
			if (difference > 0 || (difference <= 0 && Math.exp((difference)/currentTemperature) > rand.nextDouble())) {
				this.currentJNode = new JigsawNode(followJNodes.elementAt(ra));
			}
			//ÿѡ��һ������ڵ㣬�����ڵ�����1
			searchedNodesNum++;
			//ͬʱ��ǰ�¶Ȱ����ʵݼ�
			currentTemperature *= rate;

		}
		//������������������Ļ�������������������Ľڵ���
	    String result = isCompleted ? "Yes" : "No";
		System.out.println("Number of searched nodes: " + searchedNodesNum + "\n");
		System.out.println("Result: " + result + "\n");
		return isCompleted;
	}
	


	

	//�ڵ�Ĺ��ۺ���
	private void estimateValue(JigsawNode jNode) {
		int dimension = 3; //����������ά��Ϊ3
		int follow = 0; // �����ڵ㲻��ȷ���������
		
		//����ÿ��������ĺ����鲻��ȷ�ĸ���
		for(int i =1 ; i<dimension*dimension; i++){
			//��ͬ��֮�䲻���бȽ�
			if (i % dimension == 0) {
				continue;
			}
			//����ǰ��ĺ�������鲻�Ƕ�Ӧ��ֵ����follow��ֵ��1
			if (jNode.getNodesState()[i] != 0 && jNode.getNodesState()[i+1] != 0) {
				if(jNode.getNodesState()[i]+1!=jNode.getNodesState()[i+1]) {
					follow++;
				}
			}
		}
		//����ÿ���������ĺ����鲻��ȷ�ĸ���
		for (int i = 1; i <= dimension; i++) {
			for (int j = 0; j < dimension-1; j++) {
				//����ǰ����ݺ����鲻�Ƕ�Ӧ��ֵ����follow��ֵ��1
				if (jNode.getNodesState()[j * dimension + i] != 0 && jNode.getNodesState()[(j + 1) * dimension + i] != 0) {
					if (jNode.getNodesState()[j*dimension+i]+dimension != jNode.getNodesState()[(j+1)*dimension+i]) {
						follow++;
					}
				}
			}
		}
		int manhattan = 0; //�����پ���
		for (int i = 1; i <= dimension*dimension; i++) {
			for (int j = 1; j <= dimension*dimension; j++) {
				if (jNode.getNodesState()[i] != 0 && endJNode.getNodesState()[j] == jNode.getNodesState()[i]) {
					//��ȡ�кź��к�
					int iy = (i-1) / dimension;
					int ix = (i+2) % dimension;
					int jy = (j-1) / dimension;
					int jx = (j+2) % dimension;
					//����ÿ����鵱ǰλ��������Ӧ������λ��֮��������پ���
					int manhattan_x = (int)Math.abs(ix - jx); 
					int manhattan_y = (int)Math.abs(iy - jy);
					//��x��yֵ֮��
					manhattan += (manhattan_x + manhattan_y);
					break;
				}
			}
		}
		
		
		int euclid = 0; //ŷ����þ���
		for (int i = 1; i <= dimension*dimension; i++) {
			for (int j = 1; j <= dimension*dimension; j++) {
				if (jNode.getNodesState()[i] != 0 && endJNode.getNodesState()[j] == jNode.getNodesState()[i]) {
					//��ȡ�кź��к�
					int iy = (i-1) / dimension;
					int ix = (i+2) % dimension;
					int jy = (j-1) / dimension;
					int jx = (j+2) % dimension;
					//����ÿ����鵱ǰλ��������Ӧ������λ��֮���ŷ����þ���
					int distance_x = (int)Math.abs(ix - jx); 
					int distance_y = (int)Math.abs(iy - jy);
					//��x��y��ƽ����
					euclid += Math.pow(distance_x, 2.0) + Math.pow(distance_y, 2.0);
					break;
				}
			}
		}
		//�Ҳ�������֮��14��12��3����ȣ��ܽϺõĿ͹۵Ĺ��۳���ǰ�ڵ�Ĺ���
		int output = (int)(14 * follow + 12 * manhattan + 3 * euclid);
		//���ù���ֵ
		jNode.setEstimatedValue(output);
	}


}
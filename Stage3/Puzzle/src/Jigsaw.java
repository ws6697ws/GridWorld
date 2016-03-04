

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Vector;
import java.util.Random;
//八数码问题的测试类
public class Jigsaw {
	JigsawNode beginJNode;		// 拼图的起始状态节点
	JigsawNode endJNode;		// 拼图的目标状态节点
	JigsawNode currentJNode;	// 拼图的当前状态节点
	private boolean isCompleted;	// 完成标记：初始为false;当求解成功时，将该标记至为true
	private int searchedNodesNum;	// 已访问节点数： 用以记录所有访问过的节点的数量

    //构造函数，初始化找到终点为False，搜索节点数为0并设置起始和终止状态，且当前状态初始化为起始状态
	public Jigsaw(JigsawNode bNode, JigsawNode eNode) {
		this.beginJNode = new JigsawNode(bNode);
		this.endJNode = new JigsawNode(eNode);
		this.currentJNode = new JigsawNode(bNode);
		this.isCompleted = false;
		this.searchedNodesNum = 0;
	}

    //用于随机打乱数码板的函数，参数为设置打乱前的节点和打乱的步数
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

    //获得当前节点的状态
	public JigsawNode getCurrentJNode() {
		return currentJNode;
	}

	//设置起始状态
	public void setBeginJNode(JigsawNode jNode) {
		beginJNode = jNode;
	}

	//获得起始状态
	public JigsawNode getBeginJNode() {
		return beginJNode;
	}

	//设置终止状态
	public void setEndJNode(JigsawNode jNode) {
		this.endJNode = jNode;
	}

	//获得终止状态
	public JigsawNode getEndJNode() {
		return endJNode;
	}

	//获取是否找到终点
	public boolean isCompleted() {
		return isCompleted;
	}

	//得到结束时搜索节点的数量
	public int getSearchedNodesNum() {
		return searchedNodesNum;
	}
	
	//获得扩展节点列表的函数
	private Vector<JigsawNode> findFollowJNodes(JigsawNode jNode) {
		Vector<JigsawNode> followJNodes = new Vector<JigsawNode>();
		JigsawNode tempJNode;
		//计算当前节点的估计值
		estimateValue(jNode);
		//以上下左右移动空白块的方式扩展节点
		for(int i=0; i<4; i++){
			tempJNode = new JigsawNode(jNode);
			//判断移动操作是否合法，若合法则进行移动。返回的是否合法的结果
			boolean flag = tempJNode.move(i);
			if(flag) {
				//计算估计值并加入到可扩展节点的列表当中
				estimateValue(tempJNode);
				followJNodes.addElement(tempJNode);
			}
		}
		return followJNodes;
	}
	
	//最速上升法函数
	public boolean steepAscent()  {
		//搜索前初始化
		searchedNodesNum = 0;
		isCompleted = false;
		while (true) {
			//若当前节点和最终节点相同，则找到终点，退出函数
			if (this.currentJNode.equals(this.endJNode)) {
				isCompleted = true;
				break;
			}
			estimateValue(this.currentJNode);
			//得到当前节点可扩展节点得列表
			Vector<JigsawNode> followJNodes = new Vector<JigsawNode>(); 
			followJNodes = this.findFollowJNodes(this.currentJNode);
			
			
			int min = this.currentJNode.getEstimatedValue();
			JigsawNode tempNode = new JigsawNode(this.currentJNode);
			//遍历可扩展节点列表，寻找优于当前节点的可扩展节点中最优的节点
			for (int i = 0; i < followJNodes.size(); i++) {
				if (followJNodes.elementAt(i).getEstimatedValue() < min) {
					min = followJNodes.elementAt(i).getEstimatedValue();
					tempNode  = new JigsawNode(followJNodes.elementAt(i));
				}
				//每当对一个节点进行估价，则搜索节点数加1
				searchedNodesNum++;
			}
			//若找到的更好的节点与当前节点相同，证明陷入了局部最优解，退出函数
			if (tempNode.equals(this.currentJNode)) {
				isCompleted = false;
				break;
			}
			//否则扩展更好的节点
			this.currentJNode = new JigsawNode(tempNode);
		}
		//搜索函数结束后在屏幕上输出搜索结果和搜索的节点数
	    String result = isCompleted ? "Yes" : "No";
		System.out.println("Number of searched nodes: " + searchedNodesNum + "\n");
		System.out.println("Result: " + result + "\n");
		return isCompleted;
	}
	
	//首选法函数
	public boolean firstChoice()  {
		//搜索前初始化
		searchedNodesNum = 0;
		isCompleted = false;
		while (true) {
			//若当前节点和最终节点相同，则找到终点，退出函数
			if (this.currentJNode.equals(this.endJNode)) {
				isCompleted = true;
				break;
			}
			estimateValue(this.currentJNode);
			//得到当前节点的可扩展节点列表
			Vector<JigsawNode> followJNodes = new Vector<JigsawNode>(); 
			followJNodes = this.findFollowJNodes(this.currentJNode);
			
			//由于得到可扩展节点列表时已经对其中每个节点进行了估价，因此搜索节点数直接加上列表大小
			searchedNodesNum += followJNodes.size();
			//新建可扩展节点中优于当前节点的节点列表
			Vector<JigsawNode> betterJNodes = new Vector<JigsawNode>(); 
			int min = this.currentJNode.getEstimatedValue();
			
			//遍历一遍可扩展节点，从可扩展节点中提取优于当前节点的点
			for (int i = 0; i < followJNodes.size(); i++) {
				if (followJNodes.elementAt(i).getEstimatedValue() < min) {
					betterJNodes.addElement(followJNodes.elementAt(i));
				}
			}
			JigsawNode tempNode = new JigsawNode(this.currentJNode);
			
			//从较优节点列表中通过随机函数随机选取一个节点扩展
			if (betterJNodes.size() != 0) {
				Random rand = new Random();
				int ra = rand.nextInt(betterJNodes.size());
				tempNode = new JigsawNode(betterJNodes.elementAt(ra));
			}
			//若找到的更好的节点与当前节点相同，证明陷入了局部最优解，退出函数
			if (tempNode.equals(this.currentJNode)) {
				isCompleted = false;
				break;
			}
			//否则扩展更好的节点
			this.currentJNode = new JigsawNode(tempNode);
		}
		//搜索函数结束后在屏幕上输出搜索结果和搜索的节点数
	    String result = isCompleted ? "Yes" : "No";
		System.out.println("Number of searched nodes: " + searchedNodesNum + "\n");
		System.out.println("Result: " + result + "\n");
		return isCompleted;
	}
	
	//改进后首选法函数
	public boolean advancedFirstChoice()  {
		//搜索前初始化
		searchedNodesNum = 0;
		isCompleted = false;
		int maxCount = 80; //定义最大连续寻找等价扩展节点次数
		int sameCount = 0; //记录当前连续寻找等价扩展节点次数
		while (true) {
			//若当前节点和最终节点相同，则找到终点，退出函数
			if (this.currentJNode.equals(this.endJNode)) {
				isCompleted = true;
				break;
			}
			estimateValue(this.currentJNode);
			//得到当前节点的可扩展节点列表
			Vector<JigsawNode> followJNodes = new Vector<JigsawNode>(); 
			followJNodes = this.findFollowJNodes(this.currentJNode);
			
			//由于得到可扩展节点列表时已经对其中每个节点进行了估价，因此搜索节点数直接加上列表大小
			searchedNodesNum += followJNodes.size();
			//新建可扩展节点中优于当前节点的节点列表
			Vector<JigsawNode> betterJNodes = new Vector<JigsawNode>(); 
			int min = this.currentJNode.getEstimatedValue();
			
			//遍历一遍可扩展节点，从可扩展节点中提取优于或者等于当前节点的点
			for (int i = 0; i < followJNodes.size(); i++) {
				if (followJNodes.elementAt(i).getEstimatedValue() < min || 
					(followJNodes.elementAt(i).getEstimatedValue() == min && sameCount > maxCount)) {
					betterJNodes.addElement(followJNodes.elementAt(i));
				}
			}
			JigsawNode tempNode = new JigsawNode(this.currentJNode);
			
			//从较优节点列表中通过随机函数随机选取一个节点扩展
			if (betterJNodes.size() != 0) {
				Random rand = new Random();
				int ra = rand.nextInt(betterJNodes.size());
				tempNode = new JigsawNode(betterJNodes.elementAt(ra));
			}
			//若找到的更好的节点与当前节点相同，证明陷入了局部最优解，退出函数
			if (tempNode.equals(this.currentJNode)) {
				isCompleted = false;
				break;
			}
			//假如当前选择的扩展节点优于当前节点，将sameCount计数清零
			if (currentJNode.getEstimatedValue() > tempNode.getEstimatedValue()) {
				sameCount = 0;
			} else {//假如当前选择的扩展节点估计值与当前节点的相同，则将sameCount加1
				sameCount++;
			}
			
			//扩展更好的节点
			this.currentJNode = new JigsawNode(tempNode);
			
		}
		//搜索函数结束后在屏幕上输出搜索结果和搜索的节点数
	    String result = isCompleted ? "Yes" : "No";
		System.out.println("Number of searched nodes: " + searchedNodesNum + "\n");
		System.out.println("Result: " + result + "\n");
		return isCompleted;
	}

	//模拟退火算法函数
	public boolean simulatedAnnealing()  {
		searchedNodesNum = 0;
		isCompleted = false;
		double startTemperature = 20000; //起始温度
		double rate = 0.999; //冷却速率
		double endTemperature = 0.00001; //终止搜索时的温度
		double currentTemperature = startTemperature; //当前温度，初始化为起始温度
		
		while (currentTemperature > endTemperature) {
			//若当前节点和最终节点相同，则找到终点，退出函数
			if (this.currentJNode.equals(this.endJNode)) {
				isCompleted = true;
				break;
			}
			
			estimateValue(this.currentJNode);
			
			//得到当前节点的可扩展节点列表
			Vector<JigsawNode> followJNodes = new Vector<JigsawNode>(); 
			followJNodes = this.findFollowJNodes(this.currentJNode);
			int min = this.currentJNode.getEstimatedValue();
			
			//通过随机函数从可扩展节点中随机选择一个节点
			Random rand = new Random();
			int ra = rand.nextInt(followJNodes.size());
			estimateValue(followJNodes.elementAt(ra));
			//计算随机选的节点和当前节点的差距
			int temp = followJNodes.elementAt(ra).getEstimatedValue();
			double difference = min - temp;
			//若选择的节点优于当前节点，或者随机数产生的概率小于以差距除以当前温度为指数的对数值时，则选择随机的这个节点扩展
			if (difference > 0 || (difference <= 0 && Math.exp((difference)/currentTemperature) > rand.nextDouble())) {
				this.currentJNode = new JigsawNode(followJNodes.elementAt(ra));
			}
			//每选择一个随机节点，搜索节点数加1
			searchedNodesNum++;
			//同时当前温度按速率递减
			currentTemperature *= rate;

		}
		//搜索函数结束后在屏幕上输出搜索结果和搜索的节点数
	    String result = isCompleted ? "Yes" : "No";
		System.out.println("Number of searched nodes: " + searchedNodesNum + "\n");
		System.out.println("Result: " + result + "\n");
		return isCompleted;
	}
	


	

	//节点的估价函数
	private void estimateValue(JigsawNode jNode) {
		int dimension = 3; //设置数码版的维数为3
		int follow = 0; // 后续节点不正确的数码个数
		
		//计算每个板块横向的后续块不正确的个数
		for(int i =1 ; i<dimension*dimension; i++){
			//不同行之间不进行比较
			if (i % dimension == 0) {
				continue;
			}
			//若当前块的横向后续块不是对应的值，则follow的值加1
			if (jNode.getNodesState()[i] != 0 && jNode.getNodesState()[i+1] != 0) {
				if(jNode.getNodesState()[i]+1!=jNode.getNodesState()[i+1]) {
					follow++;
				}
			}
		}
		//计算每个板块纵向的后续块不正确的个数
		for (int i = 1; i <= dimension; i++) {
			for (int j = 0; j < dimension-1; j++) {
				//若当前块的纵后续块不是对应的值，则follow的值加1
				if (jNode.getNodesState()[j * dimension + i] != 0 && jNode.getNodesState()[(j + 1) * dimension + i] != 0) {
					if (jNode.getNodesState()[j*dimension+i]+dimension != jNode.getNodesState()[(j+1)*dimension+i]) {
						follow++;
					}
				}
			}
		}
		int manhattan = 0; //曼哈顿距离
		for (int i = 1; i <= dimension*dimension; i++) {
			for (int j = 1; j <= dimension*dimension; j++) {
				if (jNode.getNodesState()[i] != 0 && endJNode.getNodesState()[j] == jNode.getNodesState()[i]) {
					//获取行号和列号
					int iy = (i-1) / dimension;
					int ix = (i+2) % dimension;
					int jy = (j-1) / dimension;
					int jx = (j+2) % dimension;
					//计算每个板块当前位置与它对应的最终位置之间的曼哈顿距离
					int manhattan_x = (int)Math.abs(ix - jx); 
					int manhattan_y = (int)Math.abs(iy - jy);
					//即x与y值之和
					manhattan += (manhattan_x + manhattan_y);
					break;
				}
			}
		}
		
		
		int euclid = 0; //欧几里得距离
		for (int i = 1; i <= dimension*dimension; i++) {
			for (int j = 1; j <= dimension*dimension; j++) {
				if (jNode.getNodesState()[i] != 0 && endJNode.getNodesState()[j] == jNode.getNodesState()[i]) {
					//获取行号和列号
					int iy = (i-1) / dimension;
					int ix = (i+2) % dimension;
					int jy = (j-1) / dimension;
					int jx = (j+2) % dimension;
					//计算每个板块当前位置与它对应的最终位置之间的欧几里得距离
					int distance_x = (int)Math.abs(ix - jx); 
					int distance_y = (int)Math.abs(iy - jy);
					//即x与y的平方和
					euclid += Math.pow(distance_x, 2.0) + Math.pow(distance_y, 2.0);
					break;
				}
			}
		}
		//我采用三者之间14：12：3的配比，能较好的客观的估价出当前节点的估价
		int output = (int)(14 * follow + 12 * manhattan + 3 * euclid);
		//设置估价值
		jNode.setEstimatedValue(output);
	}


}
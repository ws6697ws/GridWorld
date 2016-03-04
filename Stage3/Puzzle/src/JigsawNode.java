

import java.io.IOException;

//���ְ���������λ�õĽڵ���
public class JigsawNode {

	private static final int dimension = 3; 	// ƴͼ��ά�� 
	private int[] nodesState; 			// ƴͼ״̬����һλ�洢�հ׸��λ�ã��������洢��Ӧ�����е���ֵ��
	private int estimatedValue; 		// ���۹���ֵ

	//������ڵ㹹�캯��������ÿ�����������հ׿��λ��
	public JigsawNode(int[] data) {
		if(data.length == this.dimension*dimension+1){
			this.nodesState = new int[data.length];
			for (int i = 0; i < this.nodesState.length; i++)
				this.nodesState[i] = data[i];

			this.estimatedValue = 0;
		} else
			System.out.println("����������󣺵�ǰ�Ľڵ�ά��Ϊ3.�봫�볤��Ϊ" + (dimension * dimension + 1)
					+ "�Ľڵ�״̬���飬���ߵ���Jigsaw���еĽڵ�ά��dimension");
	}

	/* JigsawNode�������캯��������һ�������������ͬ�Ľڵ�
	 * jNode�������Ը��ƵĽڵ�
	 */
	public JigsawNode(JigsawNode jNode) {
		this.nodesState = new int[dimension * dimension + 1];
		this.nodesState = (int[]) jNode.nodesState.clone();
		this.estimatedValue = jNode.estimatedValue;
	}

	/* ��ȡƴͼά��
	 * dimension����ǰƴͼά��
	 */
	public static int getDimension() {
		return dimension;
	}

	/* ��ȡ�ڵ�״̬����һ��N*N+1��һά���顣��1λ����հ׸�����λ�ã�����N*Nλ�ֱ����ÿһ�������ŷ������ֵ���������к������򣩡�
	 * return nodesState����ڵ�״̬����
	 */
	public int[] getNodesState() {
		return nodesState;
	}



	/* ��ȡ�ڵ�Ĵ��۹���ֵ
	 * return estimatedValue����ڵ�Ĵ��۹���ֵ
	 */
	public int getEstimatedValue() {
		return estimatedValue;
	}

	/* ���ýڵ�Ĵ��۹���ֵ
	 * estimatedValue��������Ĵ��۹���ֵ
	 */
	public void setEstimatedValue(int estimatedValue) {
		this.estimatedValue = estimatedValue;
	}

	//��ʼ���ڵ�Ĵ��۹�ֵestimatedValue
	public void setInitial() {
		this.estimatedValue = 0;

	}

	/* �Ƚ�����ƴͼ״̬�����ڼ��һ���ڵ��Ƿ�ΪĿ��ڵ�
	 * obj����JigsawNode��ʵ���������뵱ǰ�ڵ���бȽϵĽڵ�
	 * ״̬��ͬ�򷵻�true�����򷵻�false
	 */
	public boolean equals(Object obj) {
		for (int i = 0; i < this.nodesState.length; i++) {
			if (this.nodesState[i] != ((JigsawNode) obj).nodesState[i]) {
				return false;
			}
		}
		return true;
	}
 
	/* ��ȡ��ʾ��ǰƴͼ״̬���ַ����ı�����һά������ʽ��ʾ
	 * ����str��ʾ��ǰƴͼ״̬���ַ����ı���һά������ʽ��
	 */
	public String toString() {
		String str = new String();
		str += "{" + this.nodesState[0];
		for(int index = 1; index <= dimension * dimension; index++)
			str += "," + this.nodesState[index];
		str += "}";
		return str;
	}
	
	/* ��ȡ��ʾ��ǰƴͼ״̬���ַ����ı��������о�����ʽ��ʾ
	 * ���ص�String ��ʾ��ǰƴͼ״̬���ַ����ı������о�����ʽ��
	 */
	public String toMatrixString() {
		String str = new String();
		for (int x = 1,index = 1; x <= dimension; x++) {
			for (int y = 1; y <= dimension; y++,index++){
				str += this.nodesState[index] + "  ";
			}
			str += "\n";
		}
		return str;
	}

	/*
	 * ̽�⵱ǰ״̬�пհ׸�Ŀ��ƶ���λ
	 * 
	 * ����һ����λ���飬1��4λ�ֱ����հ׸��Ƿ������ϡ��¡������ƶ��� ֵΪ1ʱ����÷�����ƶ���ֵΪ0ʱ����÷��򲻿��ƶ���
	 */
	public int[] canMove() {
		int[] movable = new int[] { 0, 0, 0, 0 };
		if (this.nodesState[0] > dimension
				&& this.nodesState[0] <= dimension * dimension)
			movable[0] = 1; // �հ׸��������
		if (this.nodesState[0] >= 1
				&& this.nodesState[0] <= dimension * (dimension - 1))
			movable[1] = 1; // �հ׸��������
		if (this.nodesState[0] % dimension != 1)
			movable[2] = 1; // �հ׸��������
		if (this.nodesState[0] % dimension != 0)
			movable[3] = 1; // �հ׸��������
		return movable;
	}
	
	/*
	 * ̽�⵱ǰ״̬�пհ׸��ܷ������ƶ�
	 * �������ƶ��򷵻�true,���򷵻�false
	 */
	public boolean canMoveEmptyUp() {
		return (this.nodesState[0] > dimension && this.nodesState[0] <= dimension
				* dimension);
		// ����հ׸��ڵ�һ����������ƶ�
	}

	/*
	 * ̽�⵱ǰ״̬�пհ׸��ܷ������ƶ�
	 * �������ƶ��򷵻�true,���򷵻�false
	 */
	public boolean canMoveEmptyDown() {
		return (this.nodesState[0] >= 1 && this.nodesState[0] <= dimension
				* (dimension - 1));
		// ����հ׸������һ����������ƶ�
	}

	/* ̽�⵱ǰ״̬�пհ׸��ܷ������ƶ�
	 * �������ƶ��򷵻�true,���򷵻�false
	 */
	public boolean canMoveEmptyLeft() {
		return (this.nodesState[0] % dimension != 1);
		// ����հ׸��ڵ�һ����������ƶ�
	}

	/* ̽�⵱ǰ״̬�пհ׸��ܷ������ƶ�
	 * �������ƶ��򷵻�true,���򷵻�false
	 */
	public boolean canMoveEmptyRight() {
		return (this.nodesState[0] % dimension != 0);
		// ����հ׸������һ����������ƶ�
	}

	/* ��ĳһ�����ƶ���ǰƴͼ״̬�еĿհ׸�
	 * direction�������ǣ�0Ϊ���ϣ�1Ϊ���£�2Ϊ����3Ϊ����
	 * �ƶ��ɹ�����true��ʧ�ܷ���false
	 */
	public boolean move(int direction) {
		switch (direction) {
		case 0:
			return this.moveEmptyUp();
		case 1:
			return this.moveEmptyDown();
		case 2:
			return this.moveEmptyLeft();
		case 3:
			return this.moveEmptyRight();
		default:
			return false;
		}
	}

	/*�����ƶ���ǰƴͼ״̬�еĿհ׸�
	 *�ƶ��ɹ�����true��ʧ�ܷ���false
	 */
	public boolean moveEmptyUp() {
		int emptyPos = this.nodesState[0];
		int emptyValue = this.nodesState[emptyPos];
		if (this.nodesState[0] > dimension
				&& this.nodesState[0] <= dimension * dimension) {

			
			this.nodesState[emptyPos] = this.nodesState[emptyPos - dimension];
			this.nodesState[emptyPos - dimension] = emptyValue;
			this.nodesState[0] = emptyPos - dimension;

			return true;
		}
		return false;
	}

	/*�����ƶ���ǰƴͼ״̬�еĿհ׸�
	 *�ƶ��ɹ�����true��ʧ�ܷ���false
	 */
	public boolean moveEmptyDown() {
		int emptyPos = this.nodesState[0];
		int emptyValue = this.nodesState[emptyPos];
		if (this.nodesState[0] >= 1
				&& this.nodesState[0] <= dimension * (dimension - 1)) {

			;


			this.nodesState[emptyPos] = this.nodesState[emptyPos + dimension];
			this.nodesState[emptyPos + dimension] = emptyValue;
			this.nodesState[0] = emptyPos + dimension;
			return true;
		}
		return false;
	}

	/*�����ƶ���ǰƴͼ״̬�еĿհ׸�
	 *�ƶ��ɹ�����true��ʧ�ܷ���false
	 */
	public boolean moveEmptyLeft() {
		int emptyPos = this.nodesState[0];
		int emptyValue = this.nodesState[emptyPos];
		if (this.nodesState[0] % dimension != 1) {
			;
			this.nodesState[emptyPos] = this.nodesState[emptyPos - 1];
			this.nodesState[emptyPos - 1] = emptyValue;
			this.nodesState[0] = emptyPos - 1;
			return true;
		}
		return false;
	}

	/* �����ƶ���ǰƴͼ״̬�еĿհ׸�
	 * �ƶ��ɹ�����true��ʧ�ܷ���false
	 */
	public boolean moveEmptyRight() {
		int emptyPos = this.nodesState[0];
		int emptyValue = this.nodesState[emptyPos];
		if (this.nodesState[0] % dimension != 0) {
			;
			this.nodesState[emptyPos] = this.nodesState[emptyPos + 1];
			this.nodesState[emptyPos + 1] = emptyValue;
			this.nodesState[0] = emptyPos + 1;
			return true;
		}
		return false;
	}

}
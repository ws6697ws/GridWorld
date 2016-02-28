/* �м�ʵѵStage1
 * ���׼�����
 * Author���⿡Ω
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.DecimalFormat;  
class EasyCalculator extends JFrame implements ActionListener {
	//��Ա����
	DecimalFormat  df   = new DecimalFormat("######0.00");   
	JTextField input1;
	JTextField input2;
	JTextField display_operator;
	JTextField display_result;
	JTextField equal;
	//����������
	String[] operators = {"+", "-", "*", "/", "ok"};
	JButton ok;
	JButton[] operator_b = new JButton[operators.length];	
	String op = "+";
	double number = 0;
	//������������ʽ
	String str = "^(\\-|\\+)?\\d+(\\.\\d+)?$";
	
	//���캯��
	public EasyCalculator() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = this.getContentPane();
		//��ʼ����������UI��������
		
		JPanel head = new JPanel(new GridLayout(2, 5, 10, 10));
		//�����������������
		input1 = new JTextField();
		input2 = new JTextField();
		
		//���������������͵��ںŵ���ʾ��
		display_operator = new JTextField("+");
		display_result = new JTextField();
		equal = new JTextField("=");
		
		//λ��ȫ����Ϊˮƽ����
		equal.setHorizontalAlignment(JTextField.CENTER);
		input1.setHorizontalAlignment(JTextField.CENTER);
		input2.setHorizontalAlignment(JTextField.CENTER);
		display_operator.setHorizontalAlignment(JTextField.CENTER);
		display_result.setHorizontalAlignment(JTextField.CENTER);
		
		//���ñ߿�
		equal.setBorder(new EmptyBorder(1, 1, 1, 1));
		display_operator.setBorder(new EmptyBorder(1, 1, 1, 1));
		display_result.setBorder(new EmptyBorder(1, 1, 1, 1));
		
		//�����úõĿؼ����뵽UI������
		head.add(input1);
		head.add(display_operator);
		head.add(input2);
		head.add(equal);
		head.add(display_result);
		c.add(head);
		
		//�����������ť
		for (int i = 0; i < operators.length; i++) {
			operator_b[i] = new JButton(operators[i]);
			operator_b[i].addActionListener(this);
			head.add(operator_b[i]);
		}
		
		//���ô�С�����ֺͿɼ���
		this.setTitle("Easy Caculator");
		this.setVisible(true);
		this.setSize(400, 200);
	}
	
	//������������
	public static void main(String[] args) {
		new EasyCalculator();
	}
	
	//�����ж���ѡ����
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		String label = e.getActionCommand();
		//�����������Ϊ�Ⱥ�ʱ�����������������������Ӧ�ļ���
		if (ob == operator_b[operators.length-1] ) {
			handleOut();
		} else {
			handleOperator(label);
		}
        }
	
	//������������
	public void handleOperator(String label) {
		op = label;		
		display_operator.setText(op);        
	}
	
	//������㺯��
	public void handleOut() {
	  //����������е������������Ƿ����ʵ���ĸ�ʽ
	  Pattern p = Pattern.compile(str);
	  Matcher m1 = p.matcher(input1.getText());
	  Matcher m2 = p.matcher(input2.getText());
	  if (m1.matches() && m2.matches())	{	
		//��������������ͣ�ִ����Ӧ�ļ������
		if (op== "+") {
			number = Double.valueOf(input1.getText()) + Double.valueOf(input2.getText());		
		} else if (op == "-") {
			number = Double.valueOf(input1.getText()) - Double.valueOf(input2.getText());	
		} else if (op== "*") {
			number = Double.valueOf(input1.getText()) * Double.valueOf(input2.getText());
		} 
		//�������Ϊ����ʱ����Ҫ�Գ����Ƿ�Ϊ0���м��飬���Ϊ0���׳��������
		else if (op== "/") {
			 if (String.valueOf(df.format(Double.valueOf(input2.getText()))).equals("0.00") && op.equals("/")) {
					System.out.println("error:can't divide zero");
					input1.setText("0.0");
					input2.setText("0.0");
					display_result.setText("error");
					return;
				  }
			number = Double.valueOf(input1.getText()) / Double.valueOf(input2.getText());	
		}
		display_result.setText(String.valueOf(df.format(number)));	
	  } 
	  //�����������������������ʽʱ���׳���ʽ��������
	  else {
		System.out.println("error:input must be numbers");
		input1.setText("0.0");
		input2.setText("0.0");
		display_result.setText("error");
	  }
	}
} 

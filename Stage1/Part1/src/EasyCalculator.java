/* 中级实训Stage1
 * 简易计算器
 * Author：吴俊惟
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
	//成员变量
	DecimalFormat  df   = new DecimalFormat("######0.00");   
	JTextField input1;
	JTextField input2;
	JTextField display_operator;
	JTextField display_result;
	JTextField equal;
	//操作符数组
	String[] operators = {"+", "-", "*", "/", "ok"};
	JButton ok;
	JButton[] operator_b = new JButton[operators.length];	
	String op = "+";
	double number = 0;
	//操作符正则表达式
	String str = "^(\\-|\\+)?\\d+(\\.\\d+)?$";
	
	//构造函数
	public EasyCalculator() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = this.getContentPane();
		//初始化计算器的UI界面容器
		
		JPanel head = new JPanel(new GridLayout(2, 5, 10, 10));
		//左右运算量的输入框
		input1 = new JTextField();
		input2 = new JTextField();
		
		//操作符、计算结果和等于号的显示框
		display_operator = new JTextField("+");
		display_result = new JTextField();
		equal = new JTextField("=");
		
		//位置全部设为水平居中
		equal.setHorizontalAlignment(JTextField.CENTER);
		input1.setHorizontalAlignment(JTextField.CENTER);
		input2.setHorizontalAlignment(JTextField.CENTER);
		display_operator.setHorizontalAlignment(JTextField.CENTER);
		display_result.setHorizontalAlignment(JTextField.CENTER);
		
		//设置边框
		equal.setBorder(new EmptyBorder(1, 1, 1, 1));
		display_operator.setBorder(new EmptyBorder(1, 1, 1, 1));
		display_result.setBorder(new EmptyBorder(1, 1, 1, 1));
		
		//将设置好的控件加入到UI容器中
		head.add(input1);
		head.add(display_operator);
		head.add(input2);
		head.add(equal);
		head.add(display_result);
		c.add(head);
		
		//加入操作符按钮
		for (int i = 0; i < operators.length; i++) {
			operator_b[i] = new JButton(operators[i]);
			operator_b[i].addActionListener(this);
			head.add(operator_b[i]);
		}
		
		//设置大小、名字和可见度
		this.setTitle("Easy Caculator");
		this.setVisible(true);
		this.setSize(400, 200);
	}
	
	//计算器主函数
	public static void main(String[] args) {
		new EasyCalculator();
	}
	
	//动作判断与选择函数
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		String label = e.getActionCommand();
		//若输入运算符为等号时，输出结果，否则继续进行相应的计算
		if (ob == operator_b[operators.length-1] ) {
			handleOut();
		} else {
			handleOperator(label);
		}
        }
	
	//操作符处理函数
	public void handleOperator(String label) {
		op = label;		
		display_operator.setText(op);        
	}
	
	//结果计算函数
	public void handleOut() {
	  //检验输入框中的左右运算量是否符合实数的格式
	  Pattern p = Pattern.compile(str);
	  Matcher m1 = p.matcher(input1.getText());
	  Matcher m2 = p.matcher(input2.getText());
	  if (m1.matches() && m2.matches())	{	
		//根据运算符的类型，执行相应的计算操作
		if (op== "+") {
			number = Double.valueOf(input1.getText()) + Double.valueOf(input2.getText());		
		} else if (op == "-") {
			number = Double.valueOf(input1.getText()) - Double.valueOf(input2.getText());	
		} else if (op== "*") {
			number = Double.valueOf(input1.getText()) * Double.valueOf(input2.getText());
		} 
		//若运算符为除号时，还要对除数是否为0进行检验，如果为0，抛出除零错误
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
	  //若任意运算量不符合正则格式时，抛出格式不符错误
	  else {
		System.out.println("error:input must be numbers");
		input1.setText("0.0");
		input2.setText("0.0");
		display_result.setText("error");
	  }
	}
} 

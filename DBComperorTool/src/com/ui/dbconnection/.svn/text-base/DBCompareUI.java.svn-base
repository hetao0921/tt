package com.ui.dbconnection;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DBCompareUI extends JFrame {
	private static final long serialVersionUID = 1L;
	Frame ff = new Frame();
	GridBagLayout g = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();

	int screenWidth = ((int) java.awt.Toolkit.getDefaultToolkit()
			.getScreenSize().width);
	int screenHeight = ((int) java.awt.Toolkit.getDefaultToolkit()
			.getScreenSize().height);
	
	
	JLabel noteInformation,userName,password;
	JLabel sex,birthday;
	JTextField textUserName,textUserPassword;

	DBCompareUI(String str) {
		super(str);
		int uiWidth = Integer.valueOf((int) (screenWidth * 0.8));
		int uiHeight = Integer.valueOf((int) (screenHeight * 0.8));
		ff.setSize(uiWidth, uiHeight);
		ff.setLayout(g);
		// 调用方法
		addComponent();
		// submit.addActionListener(new AddListener());
		// delete.addActionListener(new delListener());
		ff.setVisible(true);
		ff.setLocationRelativeTo(null);// 设居中显示;
		
		ff.addWindowListener(new WindowAdapter()  //为了关闭窗口
		  {
		   public void windowClosing(WindowEvent e)
		   {
		       System.exit(0);
		   }
		  }); 
	}

	public void add(GridBagLayout g, GridBagConstraints c, JComponent jc,
			int x, int y, int gw, int gh,int weightx,int weighty) {
//		c.gridx = x;
//		c.gridy = y;
//		c.fill=GridBagConstraints.BOTH;
//		c.anchor = GridBagConstraints.NORTH;
//		c.gridwidth = gw;
//		c.gridheight = gh;
		c.weightx=weightx;
		c.weighty=weighty;
		g.setConstraints(jc, c);
		
		ff.add(jc);
	}

	// 在这个方法中将会添加所有的组件;
	// 使用的网格包布局;希望楼主能看懂;
	public void addComponent() {
		//个人信息登记
		noteInformation=new JLabel("个人信息登记：");
		add(g,c,noteInformation,0,0,1,1,11,11);
		//用户名
		userName=new JLabel("用户名：");
		add(g,c,userName,0,1,1,1,22,22);
		//用户名输入框
		textUserName=new JTextField(10);
		add(g,c,textUserName,1,1,2,1,11,33);
		//密码：
		password=new JLabel("密码：");
		add(g,c,password,0,2,1,1,11,11);
		//密码输入框
		textUserPassword=new JTextField(10);
		c.gridwidth=GridBagConstraints.REMAINDER;
		add(g,c,textUserPassword,1,2,2,1,11,22);
		

		
		JButton button=new JButton("测试");
		add(g,c,button,1,2,2,1,11,22);
	}

	public static void main(String[] args) {
		new DBCompareUI("数据库对比工具V1.0");
	}
}

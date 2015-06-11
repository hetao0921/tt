package com.ui.base;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;

public class NumberPad {
	  private static final Insets insets = new Insets(0, 0, 0, 0);
	  public static void main(final String args[]) {
	    final JFrame frame = new JFrame("NumberPad");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗口操作
	    frame.setLayout(new GridBagLayout());//框架布局
	    JButton button;
	    //下面利用设立的类对按键进行布局
	    //第一行
	    button = new JButton("Num");
	    addComponent(frame, button, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    button = new JButton("/");
	    addComponent(frame, button, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    button = new JButton("*");
	    addComponent(frame, button, 2, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    button = new JButton("-");
	    addComponent(frame, button, 3, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    //第二行
	    button = new JButton("1");
	    addComponent(frame, button, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    button = new JButton("2");
	    addComponent(frame, button, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    button = new JButton("3");
	    addComponent(frame, button, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    button = new JButton("+");
	    addComponent(frame, button, 3, 1, 1, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    // 第三行
	    button = new JButton("4");
	    addComponent(frame, button, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    button = new JButton("5");
	    addComponent(frame, button, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    button = new JButton("6");
	    addComponent(frame, button, 2, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    //第四行
	    button = new JButton("7");
	    addComponent(frame, button, 0, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    button = new JButton("8");
	    addComponent(frame, button, 1, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    button = new JButton("9");
	    addComponent(frame, button, 2, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    button = new JButton("Enter");
	    addComponent(frame, button, 3, 3, 1, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    //第五行
	    button = new JButton("0");
	    addComponent(frame, button, 0, 4, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	    button = new JButton(".");
	    addComponent(frame, button, 2, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);  
	    frame.setSize(250,250);
	    frame.setVisible(true);
	  }
	 
	 
	 
	  private static void addComponent(Container container, Component component, int gridx, int gridy,
	      int gridwidth, int gridheight, int anchor, int fill) {
	    GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
	        anchor, fill, insets, 0, 0);//建立网格包对象
	    container.add(component, gbc);//添加到容器中
	  }
}

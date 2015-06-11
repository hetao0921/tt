package com.ui.dbconnection;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class test {
	
	public static void main(String[] args) {
		 Frame ff = new Frame();

		 GridBagLayout gr = new GridBagLayout();

		 GridBagConstraints gc = new GridBagConstraints();  //创建一个名为gc的约束对象

		           ff.setLayout(gr);    //将容器ff的布局设为GridBagLayout

		 //创建一组按钮组件

		 Button bb1 = new Button("bb1");   Button bb2 = new Button("bb2");   Button bb3 = new Button("bb3");

		 Button bb4 = new Button("bb4");   Button bb5 = new Button("bb5");   Button bb6 = new Button("bb6");

		 Button bb7 = new Button("bb7");   Button bb8 = new Button("bb8");

		  

//		 gc.fill =  GridBagConstraints.BOTH;//设置约束的fill参数,该参数表示当组件的大小小于网格单元的大小时在水平和垂直方向都填充，

		 gc.weightx =11; //设置x方向的加权值为11。

		 gc.weighty = 11;//设置y方向的加权值为11。

		 gr.setConstraints(bb1, gc); //将以上gc所设置的约束应用到按钮组件bb1

		  

		 gc.weightx = 22;//设置x方向的加权值为22，如果不设置weightx则以下的组件都将自动应用上面所设置的weightx值11。

		 gr.setConstraints(bb2, gc);   //将以上所设置的约束应用到按钮组件bb2。

		 //gc.weighty=111; //注意如果不注释掉该行，则以后使用gc约束的按钮组件在y方向的加权值将为111，而在前面设置的y方向的加权值11将失去作用。

		 gc.weightx =33;

		 gc.gridwidth = GridBagConstraints.REMAINDER;//设置gridwidth参数的值为REMAINDER这样在后面使用该约束的组件将是该行的最后一个组件。

		 gr.setConstraints(bb3, gc); //第一行添加了三个按钮组件bb1,bb2,bb3，且这3个按钮的宽度按weightx设置的值11,22,33按比例设置宽度

		     GridBagConstraints gc1 = new GridBagConstraints();//创建第二个约束gc1

		  

//		 gc1.fill = GridBagConstraints.BOTH;

		 gc1.weighty = 22;  //将第2行的y方向加权值设为22

		 gr.setConstraints(bb4, gc1);

		     gr.setConstraints(bb5, gc1);

		  

		 gc1.gridwidth = GridBagConstraints.REMAINDER;

		 gr.setConstraints(bb6, gc1);  //第二行添加了三个按钮组件bb4,bb5,bb6

		  

		 gc1.weighty =33;

		     gc1.gridwidth = GridBagConstraints.REMAINDER;

		 gr.setConstraints(bb7, gc1);//第三行添加了一个按钮组件bb7

		 gc1.weighty=0;

		 gr.setConstraints(bb8, gc1); //第四行添加了一个按钮组件bb8，bb8并没有添加到bb7的后面，因为bb8使用了bb7前面的gridwidth参数设置的值，所以bb8也是单独的一行。

		  

		 ff.setSize(500, 300);

		 ff.add(bb1); ff.add(bb2);ff.add(bb3);  ff.add(bb4); ff.add(bb5); ff.add(bb6);  ff.add(bb7); ff.add(bb8);

		 ff.setVisible(true);}
	}



package com.kl.autocalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.*;

public class MainJF extends JFrame{

    int score=0;
    int power_form=2;
    int time =20;
    JLabel jltime = new JLabel();//倒计时
    JLabel jlformula = new JLabel();//生成公式
    JRadioButton jrb1 = new JRadioButton("乘方：**");//乘方选择
    JRadioButton jrb2 = new JRadioButton("^");


    public MainJF() {
        Container container = getContentPane();
        getContentPane().setLayout(new FlowLayout(1, 70, 25));//居中对齐，横纵间距30
        setTitle("四则运算闯关游戏");

        JLabel jlscore = new JLabel("得分：" + score);//得分
        jlscore.setFont(new java.awt.Font("Dialog", 1, 25));


        JTextField jt = new JTextField();//输入框
        jt.setColumns(30);
        jt.setFont(new java.awt.Font("Dialog", 1, 20));

        JButton jb = new JButton("提交");
        jb.setFont(new java.awt.Font("Dialog", 1, 15));

        jltime.setFont(new java.awt.Font("Dialog", 1, 25));
        jlformula.setFont(new java.awt.Font("Times", 1, 35));
        jrb1.setFont(new java.awt.Font("Dialog", 1, 15));
        jrb2.setFont(new java.awt.Font("Dialog", 1, 15));
        ButtonGroup bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);
        jrb1.setSelected(true);

        add(jlscore);
        add(jltime);
        add(Box.createHorizontalStrut(30000));
        add(jrb1);
        add(jrb2);
        add(Box.createHorizontalStrut(30000));
        add(jlformula);
        add(Box.createHorizontalStrut(30000));
        add(jt);
        add(Box.createHorizontalStrut(30000));
        add(jb);

        setVisible(true);
        setBackground(Color.white);
        setSize(800, 500);
        setLocationRelativeTo(null);

        jrb1.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (jrb1.isSelected()) {
                            power_form = 2;
                            Create cf = new Create(power_form);
                            jlformula.setText(cf.formula);
                            jltime.setText("20");
                            time = 20;
                        }
                    }
                }
        );
        jrb2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (jrb2.isSelected()) {
                            power_form = 1;
                            Create cf = new Create(power_form);
                            jlformula.setText(cf.formula);
                            jltime.setText("20");
                            time = 20;
                        }
                    }
                }
        );
        jb.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String submit = jt.getText();
                        Calculator calculator = new Calculator(jlformula.getText(), power_form);
                        String answer = calculator.cal();
                        if (answer.equals(submit)) {
                            System.out.println("ac");
                            score++;
                            jltime.setText("20");
                            time = 20;
                            jlscore.setText("得分" + score);
                            int i = score;
                            String formula = null;
                            try {
                                FileReader freader = new FileReader("formula.txt");
                                BufferedReader reader = new BufferedReader(freader);
                                while ((formula = reader.readLine()) != null) {
                                    i--;
                                    if (i == 0)
                                        break;
                                }
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            jlformula.setText(formula);
                        } else {
                            jltime.setText("20");
                            time = 20;
                            JOptionPane.showMessageDialog(null, answer, "正确答案", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
        );
    }

    public void end()
    {


    }

    public void init()
    {
        Create cf= new Create(power_form);
        jlformula.setText(cf.formula);

        while (time >= 0) {
            jltime.setText( "" + time );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time--;
        }
    }

    public static void main(String[] args) {
        new MainJF ().init();
    }
}
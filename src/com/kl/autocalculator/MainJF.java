package com.kl.autocalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainJF extends JFrame{

    int i=0;
    int life =3;
    int score=0;
    int power_form=2;
    int time =20;
    static String[] formulas = new String[2000];
    JLabel jltime = new JLabel();//倒计时
    JLabel jlformula = new JLabel();//生成公式
    JLabel jllife = new JLabel("生命：" + life);//生命
    JRadioButton jrb1 = new JRadioButton("乘方：**");//乘方选择
    JRadioButton jrb2 = new JRadioButton("^");
    File scorefile = new File("score.txt");

    static {
        try {
            File file = new File("formula.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int i=0;
            String s;
            while((s=reader.readLine())!=null){
                formulas[i++]=s;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public MainJF() throws IOException {
        Container container = getContentPane();
        getContentPane().setLayout(new FlowLayout(1, 70, 25));//居中对齐，横纵间距30
        setTitle("四则运算闯关游戏");

        JLabel jlscore = new JLabel("得分：" + score);//得分
        jlscore.setFont(new java.awt.Font("Dialog", 1, 25));
        jllife.setFont(new java.awt.Font("Dialog", 1, 25));


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
        add(jllife);
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
                        }
                        else {
                            jltime.setText("20");
                            time = 20;
                            life--;
                            jllife.setText("生命："+life);
                            JOptionPane.showMessageDialog(null, answer, "正确答案", JOptionPane.INFORMATION_MESSAGE);
                        }
                        if(life==0)
                            end();
                        //刷新题目
                        jlformula.setText(formulas[i++]);
                    }
                }
        );
    }

    public void end(){
        try {
            Calendar c = Calendar.getInstance();
            FileWriter writer = new FileWriter(scorefile, true);
            writer.write(c.get(Calendar.MONTH)+1+"月"+c.get(Calendar.DAY_OF_MONTH)+"日"
                    +c.get(Calendar.HOUR_OF_DAY)+"时"+c.get(Calendar.MINUTE)+"分" +
                    "  得分:  "+score+"\n");
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            time=-2;
            showscores();
            System.exit(0);
            } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showscores() throws IOException {
        BufferedReader bfr = new BufferedReader(new FileReader(scorefile));
        String score[]= new String[1000];
        String s,ans="";
        int pos=0;
        while ((s=bfr.readLine())!=null){
            score[pos++]=s;
        }
        for(i=pos-10;i<pos;i++)
            if(i>=0){
            ans+=score[i]+"\n";
            }
        JOptionPane.showMessageDialog(null, ans, "历史记录", JOptionPane.INFORMATION_MESSAGE);

    }


    public void init()
    {
        Create cf= new Create(power_form);
        jlformula.setText(cf.formula);
//        jlformula.setText("113**184-490-219");
        while (time >= 0) {
            jltime.setText( "" + time );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time--;
            if(time==-1) {
                time=20;
                life--;
                jllife.setText("生命"+life);
                jltime.setText("时间"+time);
                jlformula.setText(formulas[i++]);
                if(life==-1)
                {
                    life=0;
                    jllife.setText("生命"+life);
                    break;

            }}
        }
    }

    public static void main(String[] args) throws IOException {
        //new MainJF ().init();
        MainJF A=new MainJF();
        A.init();

    }
}
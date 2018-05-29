package com.kl.autocalculator;

import javax.swing.*;
import java.io.*;
import java.util.Objects;

public class Create {

    File file;
    String formula;
    BufferedWriter writer;
    int power_form;
    String[] symbol={"+","-","*","÷","/","^"};

    public Create(int power_form) {
        this.power_form=power_form;
        if(power_form==2)
            symbol[5]="**";
        try {
            file=new  File("formula.txt");
            writer =new BufferedWriter(new FileWriter(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        randomNum();
    }

    public  void save(String formula) {
        try {
            writer.write(formula+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void randomNum(){
        for(int i=0;i<2000;i++)
        {
            formula="";
            int j=3;
            boolean power=false;
            boolean fraction=false;
            while(j>=0)
            {
<<<<<<< HEAD
                int num =(int)(Math.random()*500+1);
                formula=formula+num;
                    if(j>0) {
                        if((num%6==4&&(power||fraction))||(num%6==5&&power)) //乘方分数
                            formula = formula + symbol[(num % 6) - 3];
                        else
                        {
                                if(num%6==4&&!fraction)fraction=true;
                                if(num%6==5&&!power)power=true;
                                formula = formula + symbol[num % 6];
                        }
                    }
                j--;
                    if(j==-1)
                    {
                        Calculator calculator = new Calculator(formula, power_form);
                        if(calculator.cal().equals("wrong!"))
                        {
                            calculator.cal();
                            fraction=false;
                            power=false;
                            formula="";
                            j=3;
=======
                int num =(int)(Math.random()*4000);
                boolean flag=true;
                for(int k=0;k<4*(i+1);k++)
                {
                    if(num==done[k])
                    {
                        flag=false;
                        break;
                    }
                }
                if(flag)
                {
                    formula=formula+num;
                    if(j>0) {
                        if (num % 6 == 5)//乘方
                            power = true;
                        if(num%6==4&&(power||fraction)) //乘方分数
                            formula = formula + symbol[(num % 6) - 1];
                        else
                        {
                                if(num%6==4&&!fraction)fraction=true;
                                formula = formula + symbol[num % 6];
>>>>>>> d3cd08c15d3b1bc9999f9cfd707a90f7338d8fae
                        }
                    }
            }
            save(formula);
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

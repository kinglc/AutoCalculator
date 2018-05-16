package com.kl.autocalculator;

import javax.swing.*;
import java.io.*;

public class Create {

    File file;
    String formula;
    BufferedWriter writer;
    int power_form;
    String[] symbol={"+","-","*","÷","/","^"};

    public Create(int power_form) {
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
        int[] done=new int[4000];
        for(int i=0;i<1000;i++)
        {
            formula="";
            int j=3;
            while(j>=0)
            {
                int num =(int)(Math.random()*4000);
                boolean flag=true;
                boolean power=false;
                boolean divide=false;
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
                        if (num % 6 == 5) power = true;
                        if(num%6==4&&(power||divide)) {
                            formula = formula + symbol[num % 6 - 1];
                            power=false;
                        }
                        else
                        {
                            formula = formula + symbol[num % 6];
                            if(num%6==4&&divide==false)divide=true;
                            power=false;
                        }
                    }
                    j--;
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

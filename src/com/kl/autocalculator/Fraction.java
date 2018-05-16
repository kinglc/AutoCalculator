package com.kl.autocalculator;

public class Fraction {
    int nume;//分数的分子
    int deno;//分数的分母

    public Fraction()
    {

    }

    public Fraction(String s)
    {
        int frac=s.indexOf('/');
        if(frac==-1)//若是整数，分母按1算
        {
            this.nume=Integer.parseInt(s);
            this.deno=1;
        }
        else{
            this.nume=Integer.parseInt(s.substring(0,frac));
            this.deno=Integer.parseInt(s.substring(frac+1));
        }
    }

    public String add(Fraction f)
    {
        Fraction ans=new Fraction();
        ans.deno=this.deno*f.deno/gcd(this.deno,f.deno);//通分
        this.nume*=ans.deno/this.deno;
        f.nume*=ans.deno/f.deno;
        ans.nume=this.nume+f.nume;
        return reduction(ans);
    }

    public String substract(Fraction f)
    {
        Fraction ans=new Fraction();
        ans.deno=this.deno*f.deno/gcd(this.deno,f.deno);//通分
        this.nume*=ans.deno/this.deno;
        f.nume*=ans.deno/f.deno;
        ans.nume=this.nume-f.nume;
        return reduction(ans);
    }

    public String multiply(Fraction f)
    {
        Fraction ans=new Fraction();
        ans.deno=this.deno*f.nume;
        ans.nume=this.nume*f.deno;
        return reduction(ans);
    }

    public String divide(Fraction f)
    {
        Fraction ans=new Fraction();
        ans.deno=this.deno*f.deno;
        ans.nume=this.nume*f.nume;
        return reduction(ans);
    }

    static String reduction(Fraction f)//约分
    {
        int div=gcd(f.deno,f.nume);
        f.deno/=div;
        f.nume/=div;
        if(f.deno==1)
            return ""+f.nume;
        else if(f.nume==0)
            return "0";
        else return f.nume+"/"+f.deno;
    }

    static int gcd( int x , int y){//最大公约数
        if( y == 0 )
            return x;
        else
            return gcd(y,x%y);
    }
}

package com.kl.autocalculator;

import java.math.BigDecimal;

public class Calculator {
    String formula;
    int power_form;//关于乘方符号的形式：1代表^,2代表**

    Calculator(String formula, int power_form) {
        this.power_form = power_form;
        this.formula = formula;
    }

    public String cal() {
        String s = getAns(formula);
        return s;
    }

    public String getAns(String s) {
        try {
            while (s.indexOf("(") != -1 && s.indexOf(")") != -1) {//小括号
                int left;
                int right;
                String front;
                String behind;
                left = s.lastIndexOf("(");
                front = s.substring(0, left);
                s = s.substring(left + 1);
                right = s.indexOf(")");
                behind = s.substring(right + 1);
                s = s.substring(0, right);
                s = front + getAns(s) + behind;
            }

            while (s.indexOf("^") != -1 || s.indexOf("**") != -1) {//乘方
                int mul = (s.indexOf("^") + s.indexOf("**"));
                int num = mul++;
                while (num > 0) {
                    if (s.charAt(num) == '+' || s.charAt(num) == '-' || s.charAt(num) == '*' || s.charAt(num) == '÷')
                        break;
                    num--;
                }
                String a = s.substring(num + 1, mul);//底数
                num = mul + power_form;
                while (num < s.length()) {
                    if (s.charAt(num) == '+' || s.charAt(num) == '-' || s.charAt(num) == '*' || s.charAt(num) == '÷')
                        break;
                    num++;
                }
                String b = s.substring(mul + power_form, num);//幂
                int b1 = Integer.parseInt(b);

                String c="1";
                if(a.indexOf('/')!=-1)
                {
                    Fraction f1 = new Fraction(a);
                    for (int i = 0; i < b1; i++)
                    {
                        Fraction ft = new Fraction(c);
                        c = ft.multiply(f1);
                    }
                }
                else {
                    BigDecimal a1 = new BigDecimal(a);
                    BigDecimal ans = new BigDecimal(1);
                    for (int i = 0; i < b1; i++)
                        ans = ans.multiply(a1);
                    c = "" + ans;
                }
                s = s.substring(0, mul - 1) + c + s.substring(mul + 1 + power_form);
            }


            while (s.indexOf("*") != -1 || s.indexOf("÷") != -1) {
                int multiply = s.indexOf("*");
                int divide = s.indexOf("÷");
                int way;
                if (multiply == -1)
                    multiply = 0xfffffff;
                if (divide == -1)
                    divide = 0xfffffff;
                if (multiply < divide)//从左向右计算
                    way = multiply;
                else way = divide;
                int front = way - 1;
                while (front > 0) {
                    if (s.charAt(front) == '+' || s.charAt(front) == '-') {
                        front++;
                        break;
                    }
                    front--;
                }
                String a = s.substring(front, way);
                int behind = way + 1;
                while (behind<s.length()) {
                    if (s.charAt(behind) == '+' || s.charAt(behind) == '-' || s.charAt(behind) == '*' || s.charAt(behind) == '÷')
                        break;
                    behind++;
                }
                String b = s.substring(way + 1, behind);
                String c;

                if(a.indexOf('/')==-1&&b.indexOf('/')==-1) {
                    BigDecimal a1 = new BigDecimal(a);
                    BigDecimal b1 = new BigDecimal(b);
                    if (divide == way)
                        c = a1.divide(b1,3, BigDecimal.ROUND_HALF_UP).toString();
                    else c = a1.multiply(b1).toString();
                }
                else{
                    Fraction f1 = new Fraction(a);
                    Fraction f2 = new Fraction(b);
                    if (divide == way)
                        c = f1.divide(f2);
                    else c = f1.multiply(f2);
                }
                s = s.substring(0, front) + c + s.substring(behind);
            }


            while (s.indexOf("+") != -1 || s.indexOf("-") != -1) {
                int plus = s.indexOf("+");
                int minus = s.indexOf("-");
                int way;
                if (plus == -1)
                    plus = 0xfffffff;
                if (minus == -1)
                    minus = 0xfffffff;
                if (plus < minus)
                    way = plus;
                else way = minus;
                int front = way - 1;
                while (front > 0) {
                    if (s.charAt(front) == '+' || s.charAt(front) == '-') {
                        front++;
                        break;
                    }
                    front--;
                }
                String a = s.substring(front, way);
                int behind = way + 1;
                while (s.substring(behind).length() > 0) {
                    if (s.charAt(behind) == '+' || s.charAt(behind) == '-')
                        break;
                    behind++;
                }
                String b = s.substring(way + 1, behind);
                String c;

                if(a.indexOf('/')==-1&&b.indexOf('/')==-1) {
                    BigDecimal a1 = new BigDecimal(a);
                    BigDecimal b1 = new BigDecimal(b);
                    if (minus == way)
                        c = a1.subtract(b1).toString();
                    else c = a1.add(b1).toString();
                }
                else{
                    Fraction f1 = new Fraction(a);
                    Fraction f2 = new Fraction(b);
                    if (minus == way)
                        c = f1.substract(f2);
                    else c = f1.add(f2);
                }
                s = s.substring(0, front) + c + s.substring(behind);
            }

            if(s.indexOf('.')!=-1)
                s=s.substring(0,s.length()-1);
            return s;

        } catch (Exception e) {
            return "计算部分出错";
        }
    }
}
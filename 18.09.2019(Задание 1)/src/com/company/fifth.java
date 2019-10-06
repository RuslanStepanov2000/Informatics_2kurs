package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.Math.PI;
import static java.lang.Math.cos;

public class fifth {
    public static double Function1(double x) //Подынтегральная функция
    {
        return 2*Math.sin(x)+1;
    }
    public static double Function2(double x) //Подынтегральная функция
    {
        return -(x/PI)*(x/PI)-2*x+5*PI;
    }
    public static double Function3(double x) //Подынтегральная функция
    {
        return 1/2*cos(x)*cos(x)+1;
    }


    public static double[] CalcIntegral(double a[], double b[], int n) //Делим отрезок на n равных частей
    {
        double result[] = new double [3];
        double[]h=new double [3];


        for(int i=0; i<3; i++) {
            h[i]=(b[i] - a[i]) / n; //шаг разбиения отрезка [a;b].

            for (int j = 0; j < n; j++) {
                result[0] += Function1(a[0] + h[0] * (j + 0.5)); //y(i) в каждом отрезке деления
                result[1] += Function2(a[1] + h[1] * (j + 0.5));
                result[2] += Function3(a[2] + h[2] * (j + 0.5));
            }
            result[i] *= h[i];



        }
        return result;
    }

    public static void main(String[] args) throws Exception
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите кол-во интервалов разбиения:" );
        int n = Integer.parseInt(reader.readLine());
        double a[] = {PI,PI,PI};
        double b[] = {-PI,-PI,-PI};
        double ans[]=CalcIntegral(a,b,n);
        System.out.println("Integral 1 ="+ans[0]);
        System.out.println("Integral 2 ="+ans[1]);
        System.out.println("Integral 3 ="+ans[2]);

    }
}


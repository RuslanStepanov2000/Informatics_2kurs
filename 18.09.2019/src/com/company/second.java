package com.company;
import java.util.Scanner;
import static java.lang.Math.*;


    public class second{


        public static void main(String[] args) {


            b1();
            b2();

        }

        public static void b1() {

            int k=0,k1=0,k2=0,k3=0,k4=0;

            double x,y1,y2,y3,y4;

            for (x=-2* PI; x<=2* PI; x+= PI/6) {
                y1=2* sin(x)+1;
                y2= pow((x/ PI)-1,2);
                y3=(-pow(x/ PI,2))-2*x+5* PI;
                y4=(pow(cos(x), 2)/2)+1;

                if (y1<0|y2<0|y3<0|y4<0) {
                    k+=1;
                }
                if (y1<0) {
                    k1+=1;
                }
                if (y2<0) {
                    k2+=1;
                }
                if (y3<0) {
                    k3+=1;
                }
                if (y4<0) {
                    k4+=1;
                }
            }

            System.out.println("Общее количество отрицательных значений ="+k);
            System.out.println("Количество отрицательных значений для каждой функции");
            System.out.println("Для y1 "+k1);
            System.out.println("Для y2 "+k2);
            System.out.println("Для y3 "+k3);
            System.out.println("Для y4 "+k4);


        }

        public static void b2(){
            System.out.println("Введите кол-во разбиейний отрезка ");
            Scanner scn=new Scanner(System.in);
            int i;
            int n=scn.nextInt();
            double [] x=new double [n];
            double []y1=new double [n];
            double []y2=new double [n];
            double []y3=new double [n];
            double []y4=new double [n];
            for( i=0; i<n;i++) {
                x[i]=Math.round(Math.random() * 21- 10);

            }
            for (i=0;i<n;i++) {
                y1[i] = 2 * Math.sin(x[i]) + 1;
                y2[i] = Math.pow((x[i] / Math.PI) - 1, 2);
                y3[i] = (-Math.pow(x[i] / Math.PI, 2)) - 2 * x[i] + 5 * Math.PI;
            }
            System.out.println("MAX y1="+getMAX(y1));
            System.out.println("MIN y1="+getMin(y1));
            System.out.println("MAX y2="+getMAX(y2));
            System.out.println("MIN y2="+getMin(y2));
            System.out.println("MAX y3="+getMAX(y3));
            System.out.println("MIN y3="+getMin(y3));


        }





        public static double getMAX (double[] Func){
            double maxValue=Func[0];
            for (int r=1;r<Func.length;r++){
                if (Func[r]>maxValue){
                    maxValue=Func[r];
                }
            }
            return maxValue;
        }





        public static double getMin(double[]Func ){
            double minValue=Func[0];
            for (int z=1;z<Func.length;z++){
                if(Func[z]<minValue){
                    minValue=Func[z];
                }
            }
            return minValue;
        }


    }


package sample;

public class Solver{
    public void solver(double a, double b, double c){
        double ans1=0,ans2=0;
        double discr=b*b-4*a*c;
        if (discr<0){
            ans1=-b+Math.sqrt(Math.abs(discr));
            ans2=-b-Math.sqrt(Math.abs(discr));

            System.out.println("Первый ответ "+ ans1 +"Второй отввет "+ans2);
        }
        else {
            ans1=-b+Math.sqrt(discr);
            ans2=-b-Math.sqrt(discr);
            System.out.println("Первый ответ "+ ans1 +"Второй отввет "+ans2);
        }
    }

}

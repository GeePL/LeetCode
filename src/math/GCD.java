package math;

public class GCD {
    public static void main(String[] args){
        System.out.println(ThreeGC(1,3,6));
    }
    public static int TwoGCD(int x, int y){
        if(x<y){
            int z = x;
            x = y;
            y = z;
        }
        int i = x%y;
        while(i!=0){
            x=y;
            y=i;
            i=x%y;
        }
        return y;
    }
    public static int ThreeGC(int a, int b, int c) {
        int i=a;
        for(;(a%i!=0)|(b%i!=0)|(c%i!=0);i--) {

        }
        return i;
    }
}

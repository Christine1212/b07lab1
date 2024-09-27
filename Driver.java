import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;


public class Driver {
    
    public static void main(String [] args) {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));

        double [] c1 = {6,5};
        int [] e1= {0,2};
        Polynomial p1 = new Polynomial(c1,e1);
        double [] c2 = {3,-2,-9};
        int[] e2 = {0, 1, 4};
        Polynomial p2 = new Polynomial(c2, e2);

        Polynomial s = p1.add(p2);
        System.out.println("s(2) = " + s.evaluate(2));

        if(s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");

        Polynomial p3 = p1.mutiply(p2);
        System.out.println(p3.evaluate(2));

        try{
            File file = new File("p.txt");
            Polynomial p4 = new Polynomial(file);
            System.out.println(p4.evaluate(2));
        }catch(IOException e){
            throw new RuntimeException(e);
        }

        try{
            p2.saveToFile("output.txt");
        }catch(FileNotFoundException e_){
            throw new RuntimeException(e_);
        }

    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.FileReader;
import java.io.BufferedReader;

public class Polynomial{

    private double[] coefficients;
    private int[] exponents;
    
    public Polynomial() {
        coefficients = new double[]{0};
        exponents = new int[]{0};
    }
    
    public Polynomial (double[] coefficients, int[] exponents){
        this.coefficients = coefficients;
        this.exponents = exponents;
    }

    public Polynomial (File file) throws IOException{
        FileReader filer_eader = new FileReader(file);
        BufferedReader buffered_reader = new BufferedReader(filer_eader);
        String line = buffered_reader.readLine();
        buffered_reader.close();

        line = line.replace("-", "+-");
        String[] s = line.split("\\+");
        
        this.coefficients = new double[s.length];
        this.exponents = new int[s.length];

        for(int i=0; i<s.length; i++){
            String[] coe_ex = s[i].split("x");
            if(coe_ex.length == 1){
                this.coefficients[i] = Double.parseDouble(coe_ex[0]);
                this.exponents[i] = 0;
            }else{
                this.coefficients[i] = Double.parseDouble(coe_ex[0]);
                this.exponents[i] = Integer.parseInt(coe_ex[1]);
            }
        }
    }
    
    public Polynomial add(Polynomial other){
        
        int len1 = this.coefficients.length;
        int len2 = other.coefficients.length;

        if(len1 == 0){
            return new Polynomial(other.coefficients, other.exponents);
        }
        if(len2 == 0){
            return new Polynomial(this.coefficients, this.exponents);
        }
        
        int ThisMax = this.exponents[len1-1];
        int OtherMax = other.exponents[len2-1];
        int MaxExp = Math.max(ThisMax, OtherMax);
        
        double[] Temp = new double[MaxExp + 1];

        for(int i=0; i<len1; i++){
            Temp[this.exponents[i]] += this.coefficients[i];
        }
        for(int i=0; i<len2; i++){
            Temp[other.exponents[i]] += other.coefficients[i];
        }

        int len = 0;
        for(int i=0; i<Temp.length; i++){
            if(Temp[i] != 0)
                len++;
        }

        double[] NewCoe = new double[len];
        int[] NewEx = new int[len];

        int j=0;

        for(int i=0; i<Temp.length; i++){
            if(Temp[i] != 0){
                NewCoe[j] = Temp[i];
                NewEx[j] = i;
                j++;
            }
        }

        return new Polynomial(NewCoe, NewEx);
    }
    
    public double evaluate(double x){
        int len = this.coefficients.length;
        double res = 0.0;
        
        for(int i = 0; i < len; i++){
            res += this.coefficients[i] * Math.pow(x,this.exponents[i]);
        }

        return res;
    }
    
    public boolean hasRoot(double y){
        if (evaluate(y) == 0.0)
            return true;
        else
            return false;
    }

    public Polynomial mutiply(Polynomial other){
        int len1 = this.exponents.length;
        int len2 = other.exponents.length;
        Polynomial result = new Polynomial(new double[]{0}, new int[]{0});

        for(int i=0; i<len1; i++){
            for(int j=0; j<len2; j++){
                double TempCoe = this.coefficients[i] * other.coefficients[j];
                int TempEx = this.exponents[i] + other.exponents[j];

                double[] tempCoeArray = new double[1];
                int[] tempExArray = new int[1];
                tempCoeArray[0] = TempCoe;
                tempExArray[0] = TempEx;
                
                Polynomial temp = new Polynomial(tempCoeArray, tempExArray);

                result = result.add(temp);
            }
        }

        return result;
    }

    public void saveToFile(String fileName) throws FileNotFoundException{
        PrintStream printStream = new PrintStream(fileName);

        for(int i=0; i<this.coefficients.length; i++){
            double coefficients = this.coefficients[i];
            int exponents = this.exponents[i];

            if (coefficients == 0) continue;

            if(coefficients < 0) printStream.print(+ this.coefficients[i]);
            if(i==0 && coefficients > 0) printStream.print(this.coefficients[i]);
            if(i!=0 && coefficients > 0) printStream.print("+" + this.coefficients[i]);

            if(exponents == 1) {
                printStream.print("x");
        }else if(exponents == 0){
            continue;
        }else{
            printStream.print("x"+this.exponents[i]);
        }
    }
    printStream.close();
    }

}

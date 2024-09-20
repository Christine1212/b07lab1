public class Polynomial{
    private double[] coefficients;
    
    public Polynomial() {
        coefficients = new double[]{0};
    }
    
    public Polynomial (double[] coefficients){
        this.coefficients = coefficients;
    }
    
    public Polynomial add(Polynomial other){
        double result[];
        
        int len1 = this.coefficients.length;
        int len2 = other.coefficients.length;
        
        if (len1 > len2)
            result = new double[len1];
        if (len1 < len2)
            result = new double[len2];
        else
            result = new double[len1];
        
        for (int i=0; i<len1; i++)
            result[i] += this.coefficients[i];
        
        for (int i=0; i<len2; i++)
            result[i] += other.coefficients[i];
        
        return new Polynomial(result);
    }
    
    public double evaluate(double x){
        int len = this.coefficients.length;
        double res = 0.0;
        
        for(int i = 0; i < len; i++)
            res += this.coefficients[i] * Math.pow(x,i);
        
        return res;
    }
    
    public boolean hasRoot(double y){
        if (evaluate(y) == 0.0)
            return true;
        else
            return false;
    }
}

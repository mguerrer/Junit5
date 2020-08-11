package cl.set.junit5;

public class Calculadora {
    public Calculadora(){
    }
    public double Suma( final double x, final double y) {
        return x + y;
    }

    public double Resta(final double x, final double y) {
        return x - y;
    }

    public double Multiplica(final double x, final double y) {
        return x * y;
    }

    public double Divide(final double x, final double y) {
        return x/y;
    }
    public double Divide(int x, int y) {
        return x/y;
    }
}

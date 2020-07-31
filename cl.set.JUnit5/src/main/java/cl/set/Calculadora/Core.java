package cl.set.Calculadora;

public class Core {
    public Core(){
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
        if ( y != 0 )
            return x/y;
        else
            return Double.POSITIVE_INFINITY;
    }
}
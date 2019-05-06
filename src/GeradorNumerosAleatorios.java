public class GeradorNumerosAleatorios {

    private Integer ultimoValor;

    public GeradorNumerosAleatorios(int semente) {
        this.ultimoValor = semente;
    }

    public Integer distribuicaoUniformeMCG(int a, int c, int min, int max) {
        int mod = (max - min) + 1;
        if ( a > 0 && a < mod && c >= 0 && c < mod && mod > 0){
            int xn = this.ultimoValor;
            int xn1 = ((a * xn) + c) % mod;
            this.ultimoValor = xn1;
        }
        return this.ultimoValor + min;
    }

    public void setSemente(int novaSemente){
        this.ultimoValor = novaSemente;
    }
}
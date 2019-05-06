public class GeradorNumerosAleatorios {

    private List<Integer> valores;
    private int semente;

    public GeradorNumerosAleatorios(int semente) {
        this.valores = new ArrayList<Integer>();
        this.semente = semente;
    }

    public List<Integer> metodoMultiplicativo(int k, int m) {
        if (this.semente > 0 && k > 0 && m > 0 && k < m){
            this.valores.add(this.semente);
            for (int i = 0; i < m - 1; i++) {
                int xn = this.valores.get(i);
                int xn1 = (a * xn) % m;
                this.valores.add(xn1);
            }
        }
        return this.valores;
    }

    public List<Integer> metodoAditivo(List<Integer> sequenciaInicial, int mod) {
        this.valores.clear();
        this.valores.addAll(sequenciaInicial);
        for (int i = 0; i <= sequenciaInicial.size(); i++) {
            int novoNum = (this.valores.get(this.valores.size() - 1) + this.valores.get(i)) % mod;
            this.valores.add(novoNum);
        }
        return this.valores;
    }

    public void setSemente(int novaSemente){
        this.semente = novaSemente;
    }
}
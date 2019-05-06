public class Main {
    public static void main(String[] args) {

        Escalonador escalonador = new Escalonador(1000);
        escalonador.run();
        /*
        GeradorNumerosAleatorios gna1 = new GeradorNumerosAleatorios(3);
        GeradorNumerosAleatorios gna2 = new GeradorNumerosAleatorios(3);
        GeradorNumerosAleatorios gna3 = new GeradorNumerosAleatorios(3);
        for (int i = 0; i < 12; i++ ) {
            System.out.print(gna1.distribuicaoUniformeMCG(1, 5, 1, 12)+ " ");
        }
        System.out.println();
        for (int i = 0; i < 4; i++ ) {
            System.out.print(gna2.distribuicaoUniformeMCG(1, 3, 1, 4)+ " ");
        }
        System.out.println();

        for (int i = 0; i < 3; i++ ) {
            System.out.print(gna1.distribuicaoUniformeMCG(1, 2, 2, 4)+ " ");
        }
        */
    }
}

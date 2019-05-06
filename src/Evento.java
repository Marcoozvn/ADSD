import java.util.Random;

public class Evento {
    private TipoEvento tipoEvento;
    private String nome;

    public Evento (TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
        String[] aux = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
        "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        Random rand = new Random();
        this.nome = aux[rand.nextInt(25)] + aux[rand.nextInt(25)];
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getNome() {
        return nome;
    }
}

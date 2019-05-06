import javafx.util.Pair;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Escalonador extends Thread{
    private GeradorNumerosAleatorios geradorChegada1;
    private GeradorNumerosAleatorios geradorChegada2;
    private GeradorNumerosAleatorios tempoServico;
    private int tempoMax;
    private String elementoEmServico;
    private PriorityQueue<Pair<Integer, Evento>> cronologiaEventos;
    private Queue<Evento> fila1;
    private Queue<Evento> fila2;
    private Estado estado;

    public Escalonador(int tempo) {
        this.tempoMax = tempo;
        this.estado = Estado.Livre;
        this.elementoEmServico = "Nenhum";
        this.geradorChegada1 = new GeradorNumerosAleatorios(3);
        this.geradorChegada2 = new GeradorNumerosAleatorios(3);
        this.tempoServico = new GeradorNumerosAleatorios(3);
        this.cronologiaEventos = new PriorityQueue<>(new PairComparator());
        this.fila1 = new LinkedList<>();
        this.fila2 = new LinkedList<>();

        this.escalonaChegada1(0);
        this.escalonaChegada2(0);
    }

    public void run() {
        int segundos = 0;
        while (segundos < this.tempoMax) {
            try {
                this.sleep(1);
                segundos++;
                checaEventos(segundos);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void escalonaChegada1(int segundos){
        Evento evento = new Evento(TipoEvento.chegada1);
        int tempo = segundos + this.geradorChegada1.distribuicaoUniformeMCG(1, 5, 1, 12);
        cronologiaEventos.add(new Pair<>(tempo, evento));
    }

    public void escalonaChegada2(int segundos){
        Evento evento = new Evento(TipoEvento.chegada2);
        int tempo = segundos + this.geradorChegada2.distribuicaoUniformeMCG(1, 3, 1, 4);
        cronologiaEventos.add(new Pair<>(tempo, evento));
    }

    public void escalonaSaida(Evento evento, int segundos){
        evento.setTipoEvento(TipoEvento.fim_servico);
        int tempo = segundos + this.tempoServico.distribuicaoUniformeMCG(1, 2, 2, 4);
        cronologiaEventos.add(new Pair<>(tempo, evento));
    }

    public void checaEventos(int segundos) {
        if (segundos != this.cronologiaEventos.peek().getKey()) {
            return;
        }
        Pair<Integer, Evento> evento = this.cronologiaEventos.poll();
        if (evento.getValue().getTipoEvento() == TipoEvento.chegada1) {
            System.out.println("Tipo de Evento: Chegada em 1, Momento do evento: " + segundos + "s");
            this.escalonaChegada1(segundos);
            if (this.estado == Estado.Livre) {
                this.elementoEmServico = evento.getValue().getNome();
                this.estado = Estado.Ocupado;
                this.escalonaSaida(evento.getValue(), segundos);
            } else {
                this.fila1.add(evento.getValue());
            }
        } else if (evento.getValue().getTipoEvento() == TipoEvento.chegada2) {
            System.out.println("Tipo de Evento: Chegada em 2, Momento do evento: " + segundos + "s");
            this.escalonaChegada2(segundos);
            if (this.estado == Estado.Livre) {
                this.elementoEmServico = evento.getValue().getNome();
                this.estado = Estado.Ocupado;
                this.escalonaSaida(evento.getValue(), segundos);
            } else {
                this.fila2.add(evento.getValue());
            }
        } else if (evento.getValue().getTipoEvento() == TipoEvento.fim_servico) {
            System.out.println("Tipo de Evento: Saída, Momento do evento: " + segundos + "s");
            checaFilas(segundos);
        }
        System.out.println("Elementos na Fila 1: " + this.fila1.size());
        System.out.println("Elementos na Fila 2: " + this.fila2.size());
        System.out.println("Elemento no servico: " + this.elementoEmServico);
        checaEventos(segundos);
    }

    public void checaFilas(int segundos) {
        if (this.fila1.isEmpty() && this.fila2.isEmpty()){
            this.elementoEmServico = "Nenhum";
            this.estado = Estado.Livre;
        } else if (!this.fila1.isEmpty()){
            Evento evento = this.fila1.poll();
            this.elementoEmServico = evento.getNome();
            escalonaSaida(evento, segundos);
        } else if (!this.fila2.isEmpty()){
            Evento evento = this.fila2.poll();
            this.elementoEmServico = evento.getNome();
            escalonaSaida(evento, segundos);
        }
    }

}

enum Estado {
    Livre, Ocupado
}

class PairComparator implements Comparator<Pair<Integer, Evento>> {

    @Override
    public int compare(Pair<Integer, Evento> o1, Pair<Integer, Evento> o2) {
        if (o1.getKey() < o2.getKey()) {
            return -1;
        } else if (o1.getKey() > o2.getKey()) {
            return 1;
        } else {
            if (o1.getValue().getTipoEvento() == TipoEvento.chegada1 && o2.getValue().getTipoEvento() == TipoEvento.chegada2){
                return -1;
            }
            else if (o2.getValue().getTipoEvento() == TipoEvento.chegada2 && o2.getValue().getTipoEvento() == TipoEvento.chegada1){
                return 1;
            } else {
                return 0;
            }
        }
    }
}

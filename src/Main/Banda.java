package Main;

import java.util.Date;
import tools.CaixaDeFerramentas;

/**
 *
 * @author thamyris
 */
public class Banda {

    CaixaDeFerramentas cf = new CaixaDeFerramentas();
    private String banda;
    private String musica;
    private int idadeBanda;
    private boolean fim;
    private Date dataLancamento;

    public Banda() {
    }

    public Banda(String banda, String musica, int idadeBanda, boolean fim, Date dataLancamento) {
        this.banda = banda;
        this.musica = musica;
        this.idadeBanda = idadeBanda;
        this.fim = fim;        
        this.dataLancamento = dataLancamento;        
    }

    public String getBanda() {
        return banda;
    }

    public void setBanda(String banda) {
        this.banda = banda;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public boolean isFim() {
        return fim;
    }

    public void setFim(boolean fim) {
        this.fim = fim;
    }

    public int getIdadeBanda() {
        return idadeBanda;
    }

    public void setIdadeBanda(int idadeBanda) {
        this.idadeBanda = idadeBanda;
    }

    @Override
    public String toString() {
        String saida = banda + ";" + musica + ";" + idadeBanda  + ";" + (isFim() ? "Sim" : "Não") + ";" + cf.converteDeDateParaString(dataLancamento);
        return saida;
    }

}

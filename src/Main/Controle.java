package Main;

import java.util.ArrayList;
import java.util.List;
import tools.CaixaDeFerramentas;
import tools.ManipulaArquivo;

/**
 *
 * @author thamyris
 */
public class Controle {

    private List<Banda> lista = new ArrayList<>();
     CaixaDeFerramentas cf = new CaixaDeFerramentas();
    public Controle() { //esse construtor é usado para adicionar alguns dados na lista e 

    }

    public void limparLista() {
        lista.clear();//zera a lista
    }

    public void adicionar(Banda banda) {
        lista.add(banda);
    }

    public List<Banda> listar() {
        return lista;
    }

    public Banda buscar(String banda) {
        if (banda != null) {
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getBanda().equals(banda)) {
                    return lista.get(i);
                }
            }
        }  
        return null;
    }

    public void alterar(Banda banda, Banda bandaAntigo) {
        lista.set(lista.indexOf(bandaAntigo), banda);

    }

    public void excluir(Banda banda) {
        lista.remove(banda);
    }

    public void gravarLista(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> listaDeString = new ArrayList<>();
        for (Banda banda : lista) {
            listaDeString.add(banda.toString());
        }
        manipulaArquivo.salvarArquivo(caminho, listaDeString);
    }

    void carregarDados(String caminho) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        if (!manipulaArquivo.existeOArquivo(caminho)) {
            manipulaArquivo.criarArquivoVazio(caminho);
        }

        List<String> listaDeString = manipulaArquivo.abrirArquivo(caminho);
        //converter de CSV para Trabalhador
        Banda banda;
        for (String string : listaDeString) {
            String aux[] = string.split(";");
            banda = new Banda(aux[0], aux[1], Integer.valueOf(aux[2]),(aux[3].equals("Sim")), cf.converteDeStringParaDate(aux[4]));
            lista.add(banda);
        }
    }
}

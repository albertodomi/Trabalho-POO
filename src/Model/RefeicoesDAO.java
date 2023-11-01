package Model;

public class RefeicoesDAO {
    
    private Refeicoes vetorDeRefeicao[] = new Refeicoes[5];
    
    public RefeicoesDAO() {        

    }
    
    public boolean inserirRefeicao(Refeicoes refeicao) {
        for (int i = 0; i < vetorDeRefeicao.length; i++) {

            if (vetorDeRefeicao[i] == null) {
                vetorDeRefeicao[i] = refeicao;

                return true;
            }
        }
        return false;
    }

    public void exibirRefeicao() {
        for (Refeicoes refeicao : vetorDeRefeicao) {

            if (refeicao != null) {
                System.out.println(refeicao + "\n");
            }
        }
    }
    
    public void exibirMinhaRefeicao(Pessoa pessoa) {
        for (Refeicoes refeicao : vetorDeRefeicao) {

            if (refeicao != null && refeicao.getDieta().getPessoa().equals(pessoa)) {
                System.out.println(refeicao + "\n");
            }
        }
    }
    
    public Refeicoes buscaRefeicaoPorID(int id)
    {
        for (Refeicoes refeicao : vetorDeRefeicao) {
            if(refeicao != null && refeicao.getId() == id)
            {
                return refeicao;
            }
        }
        return null;
    }
    
    public boolean alterarIDRefeicao(int id, int novoID) {
        for (Refeicoes refeicao : vetorDeRefeicao) {
            if (refeicao != null && refeicao.getId() == id) {
                refeicao.setId(novoID);
                return true;
            }

        }
        return false;

    }
}

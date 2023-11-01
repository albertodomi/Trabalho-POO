package Model;

public class AlimentoRefeicoesDAO {
    
    public AlimentoRefeicoesDAO(){
    
    
    
    }
    
    private AlimentoRefeicoes vetorDeAlimentosRefeicoes[] = new AlimentoRefeicoes[50];
    
    public boolean inserirAlimentoRefeicao(AlimentoRefeicoes alimentoRefeicoes) {
        for (int i = 0; i < vetorDeAlimentosRefeicoes.length; i++) {

            if (vetorDeAlimentosRefeicoes[i] == null) {
                vetorDeAlimentosRefeicoes[i] = alimentoRefeicoes;

                return true;
            }
        }
        return false;
    }

    public void exibirAlimentoRefeicoes() {
        for (AlimentoRefeicoes alimentoRefeicoes : vetorDeAlimentosRefeicoes) {

            if (alimentoRefeicoes != null) {
                System.out.println(alimentoRefeicoes + "\n");
            }
        }
    }
    
    public void exibirMeuAlimentoRefeicoes(Pessoa pessoa) {
        for (AlimentoRefeicoes alimentoRefeicoes : vetorDeAlimentosRefeicoes) {

            if (alimentoRefeicoes != null && alimentoRefeicoes.getRefeicao().getDieta().getPessoa().equals(pessoa)) {
                System.out.println(alimentoRefeicoes + "\n");
            }
        }
    }
    
    public void exibirMinhaDietaPersonalizada (Pessoa pessoa)
    {
        for (AlimentoRefeicoes vetorDeAlimentosRefeicoe : vetorDeAlimentosRefeicoes) {
            
            if(vetorDeAlimentosRefeicoe != null && vetorDeAlimentosRefeicoe.getRefeicao().getDieta().getPessoa().equals(pessoa))
            {
                System.out.println(vetorDeAlimentosRefeicoe + "\n");
            }
            
        }
    }
    
}

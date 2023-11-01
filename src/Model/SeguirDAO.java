package Model;

public class SeguirDAO {

    private Seguir vetorDeSeguidores[] = new Seguir[50];

    public boolean inserirSeguir(Seguir seguir) {
        
        for (int i = 0; i < vetorDeSeguidores.length; i++) {

            if (vetorDeSeguidores[i] == null) {
                vetorDeSeguidores[i] = seguir;
                return true;
            }

        }
        return false;
    }

    public void verSolicitacoesParaSeguirFeitaPeloDonoDaConta(Pessoa pessoa) {
        for (Seguir seguir : vetorDeSeguidores) {

            if (seguir != null && seguir.getDonoDaConta().equals(pessoa)
                    && seguir.isSeguindo() == false) {
                System.out.println(seguir + "\n");
            }
        }
    }

    public void verSolicitacoesParaSeguirFeitaPeloSolicitanteParaDonodaConta(Pessoa pessoa) {
        for (Seguir seguir : vetorDeSeguidores) {

            if (seguir != null && seguir.getPessoaSeguidaPeloDonoDaConta().equals(pessoa)
                    && seguir.isSeguindo() == false) {
                System.out.println(seguir + "\n");
            }
        }
    }

    public void verPessoasQueAlguemSegue(Pessoa pessoa) {
        for (Seguir seguir : vetorDeSeguidores) {

            if (seguir != null && seguir.getDonoDaConta().equals(pessoa)
                    && seguir.isSeguindo() == true
                    || seguir != null && seguir.getPessoaSeguidaPeloDonoDaConta().equals(pessoa)
                    && seguir.isSeguindo() == true) {
                
                if (seguir.getDonoDaConta().equals(pessoa)) {
                    
                    System.out.println("Seguindo: " + seguir.getPessoaSeguidaPeloDonoDaConta().getNome()
                            + "," + " " + "Nome de Login: " + seguir.getPessoaSeguidaPeloDonoDaConta().getLogin());
                } else {
                    
                    System.out.println("Seguindo: " + seguir.getDonoDaConta().getNome()
                            + "," + " " + "Nome de Login: " + seguir.getDonoDaConta().getLogin());
                }
            }

        }
    }
    
    public void buscaPessoasQueAlguemSegue(Pessoa pessoa, PostDAO postdao) {
        for (Seguir vetorDeSeguidore : vetorDeSeguidores) {
            if(vetorDeSeguidore != null && vetorDeSeguidore.getDonoDaConta().equals(pessoa) 
                && vetorDeSeguidore.isSeguindo() == true)
            {
                postdao.verMeusPosts(vetorDeSeguidore.getPessoaSeguidaPeloDonoDaConta());
            }
            if (vetorDeSeguidore != null && vetorDeSeguidore.getPessoaSeguidaPeloDonoDaConta().equals(pessoa)
                && vetorDeSeguidore.isSeguindo() == true)
            {
                postdao.verMeusPosts(vetorDeSeguidore.getDonoDaConta());
            }
        }
    }

    public Seguir buscaSolicitcaoPorNomeDoSolicitante(Pessoa pessoa, String nomeSolicitante) {
        for (Seguir seguir : vetorDeSeguidores) {

            if (seguir != null && seguir.getPessoaSeguidaPeloDonoDaConta().equals(pessoa)
                    && seguir.getDonoDaConta().getNome().equals(nomeSolicitante)
                    && seguir.isSeguindo() == false) {
                return seguir;
            }
        }
        return null;

    }

    public boolean responderSolicitacaoDeSeguir(Seguir seguir, boolean aceitou) {
        if (aceitou == true) {
            seguir.setSeguindo(true);
            return true;
        } else {
            return false;
        }
    }
    
    public Pessoa buscaSeguidorPorNome (String nomeSeguidor)
    {
        for (Seguir seguir : vetorDeSeguidores) {
            
            if(seguir!= null && seguir.getDonoDaConta().getNome().equals(nomeSeguidor))
            {
                return seguir.getDonoDaConta();
            }
            else if (seguir!= null && seguir.getPessoaSeguidaPeloDonoDaConta().getNome().equals(nomeSeguidor))
            {
                return seguir.getPessoaSeguidaPeloDonoDaConta();
            }
        }
        return null;
    }
    
    public Pessoa verPessoasQueEstaoSendoSeguidas(Pessoa pessoa) {
        for (Seguir seguir : vetorDeSeguidores) {

            if (seguir != null && seguir.getDonoDaConta().equals(pessoa)
                    && seguir.isSeguindo() == true
                    || seguir != null && seguir.getPessoaSeguidaPeloDonoDaConta().equals(pessoa)
                    && seguir.isSeguindo() == true) {
                
                return pessoa;
            }

        }
        return null;
    }
}

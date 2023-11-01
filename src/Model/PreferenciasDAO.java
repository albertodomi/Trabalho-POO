package Model;

import java.time.LocalDate;

public class PreferenciasDAO {

    public PreferenciasDAO(PessoaDAO pessoaDAO, AlimentoDAO alimentoDAO) {
        //Pessoa pessoa = pessoaDAO.buscaPessoaPorNome("Joao");
        Pessoa pessoa = pessoaDAO.buscaPessoaPorNome("alberto");

        Alimento alimento1 = alimentoDAO.buscaAlimentoPorNome("arroz branco");
        Alimento alimento2 = alimentoDAO.buscaAlimentoPorNome("feijao carioca");
        Alimento alimento3 = alimentoDAO.buscaAlimentoPorNome("bife de vaca");

        Preferencias preferencia1 = new Preferencias(pessoa, alimento1);
        Preferencias preferencia2 = new Preferencias(pessoa, alimento2);
        Preferencias preferencia3 = new Preferencias(pessoa, alimento3);

        inserirPreferencias(preferencia1);
        inserirPreferencias(preferencia2);
        inserirPreferencias(preferencia3);
    }

    private Preferencias vetorPreferencias[] = new Preferencias[50];

    public boolean inserirPreferencias(Preferencias preferencias) {
        for (int i = 0; i < vetorPreferencias.length; i++) {

            if (vetorPreferencias[i] == null) {
                vetorPreferencias[i] = preferencias;

                return true;
            }
        }
        return false;
    }

    public void exibirPreferencias() {
        for (Preferencias preferencias : vetorPreferencias) {

            if (preferencias != null) {
                System.out.println(preferencias + "\n");
            }
        }
    }

    public Preferencias buscaPreferenciaPorNomeDaPessoa(String nomePessoa) {
        for (Preferencias preferencias : vetorPreferencias) {

            if (preferencias != null && preferencias.getPessoa().getNome().equals(nomePessoa)) {
                return preferencias;
            }
        }
        return null;
    }

    public void verMinhasPreferencias(Pessoa pessoa) {
        boolean encontrouPreferencias = false;

        for (Preferencias preferencias : vetorPreferencias) {
            if (preferencias != null && preferencias.getPessoa().equals(pessoa)) {
                System.out.println(preferencias);
                encontrouPreferencias = true;
            }
        }
        if (!encontrouPreferencias) {
            System.out.println("Você ainda não possui nenhuma preferência!");
        }
    }

    public boolean alterarIDPreferencia(int id, int novoID) {
        for (Preferencias preferencias : vetorPreferencias) {
            if (preferencias != null && preferencias.getId() == id) {
                preferencias.setId(novoID);
                return true;
            }

        }
        return false;

    }

    public boolean removerPreferenciaPorID(int ID) {
        for (int i = 0; i < vetorPreferencias.length; i++) {
            if (vetorPreferencias[i] != null && vetorPreferencias[i].getId() == ID) {
                vetorPreferencias[i] = null;
                return true;
            }
        }
        return false;

    }

    public boolean gerarDietaComPreferencia(Refeicoes refeicao, AlimentoRefeicoesDAO alimentoRefeicoesDAO) {
        double quantidadeCalorias = 0;

        for (int i = 0; i < vetorPreferencias.length; i++) {

            if (vetorPreferencias[i] != null && vetorPreferencias[i].getPessoa().equals(refeicao.getDieta().getPessoa())) {
                
                quantidadeCalorias += vetorPreferencias[i].getAlimento().getCalorias();

                if (vetorPreferencias[i] != null && vetorPreferencias[i].getPessoa().equals(refeicao.getDieta().getPessoa())
                        && quantidadeCalorias <= refeicao.getCalorias()) {

                    AlimentoRefeicoes alimentoRefeicoes = new AlimentoRefeicoes(refeicao, vetorPreferencias[i].getAlimento(),
                            vetorPreferencias[i].getAlimento().getPorcao(), vetorPreferencias[i].getAlimento().getProteinas(),
                            vetorPreferencias[i].getAlimento().getGorduras(), vetorPreferencias[i].getAlimento().getCalorias());

                    System.out.println(alimentoRefeicoes + "\n");

                    alimentoRefeicoesDAO.inserirAlimentoRefeicao(alimentoRefeicoes);

                    System.out.println("\nCalorias somada = " + quantidadeCalorias);

                }

            } else {

                return true;

            }

        }

        if (quantidadeCalorias <= refeicao.getCalorias()) {
            return true;
        }
        return false;
    }

}

package Model;

public class AvaliacaoFisicaDAO {

    private AvaliacaoFisica vetorDeAvaliacaoFisica[] = new AvaliacaoFisica[5];

    public AvaliacaoFisicaDAO(PessoaDAO pessoaDAO) {
        Pessoa pessoa1 = pessoaDAO.buscaPessoaPorNome("Joao");
        Pessoa pessoa2 = pessoaDAO.buscaPessoaPorNome("Alberto");
        AvaliacaoFisica avaliacaoFisica1 = new AvaliacaoFisica(pessoa1, 70, 180, 21, 38, 80, 95);
        AvaliacaoFisica avaliacaoFisica2 = new AvaliacaoFisica(pessoa2, 150, 160, 20, 40, 83, 100);
        inserirAvaliacaoFisica(avaliacaoFisica1);
        inserirAvaliacaoFisica(avaliacaoFisica2);
    }

    public boolean inserirAvaliacaoFisica(AvaliacaoFisica avaliacaoFisica) {
        for (int i = 0; i < vetorDeAvaliacaoFisica.length; i++) {

            if (vetorDeAvaliacaoFisica[i] == null) {
                vetorDeAvaliacaoFisica[i] = avaliacaoFisica;

                return true;
            }
        }
        return false;
    }

    public void exibirAvaliacoesFisicas() {
        for (AvaliacaoFisica avaliacaoFisica : vetorDeAvaliacaoFisica) {

            if (avaliacaoFisica != null) {
                System.out.println(avaliacaoFisica + "\n");
            }
        }
    }

    public AvaliacaoFisica buscaAvaliacaoFisicaPorNomeDaPessoa(String nomePessoa) {
        for (AvaliacaoFisica avaliacaoFisica : vetorDeAvaliacaoFisica) {

            if (avaliacaoFisica != null && avaliacaoFisica.getPessoa().getNome().equals(nomePessoa)) {
                return avaliacaoFisica;
            }
        }
        return null;
    }
    
    public AvaliacaoFisica buscaAvaliacaoFisicaPorPessoa(Pessoa pessoa) {
        for (AvaliacaoFisica avaliacaoFisica : vetorDeAvaliacaoFisica) {

            if (avaliacaoFisica != null && avaliacaoFisica.getPessoa().equals(pessoa)) {
                return avaliacaoFisica;
            }
        }
        return null;
    }

    public void verMinhaAvaliacaoFisica(Pessoa pessoa) {
        boolean encontrouAvaliacao = false;

        for (AvaliacaoFisica avaliacaoFisica : vetorDeAvaliacaoFisica) {
            if (avaliacaoFisica != null && avaliacaoFisica.getPessoa().equals(pessoa)) {
                System.out.println(avaliacaoFisica);
                encontrouAvaliacao = true;
            }
        }
        if (!encontrouAvaliacao) {
            System.out.println("Você ainda não possui uma avaliacao fisica!");
        }
    }

    public void verMeuRelatorio(Pessoa pessoa) {
        for (AvaliacaoFisica avaliacaoFisica : vetorDeAvaliacaoFisica) {
            if (avaliacaoFisica != null && avaliacaoFisica.getPessoa().equals(pessoa) && avaliacaoFisica.getPessoa().getSexo().equals("Masculino") || avaliacaoFisica != null && avaliacaoFisica.getPessoa().getSexo().equals("masculino")) {
                if (avaliacaoFisica.getBF() < 11 && avaliacaoFisica.getIdade() <= 29
                        || avaliacaoFisica.getBF() < 12 && avaliacaoFisica.getIdade() >= 30 && avaliacaoFisica.getIdade() <= 39
                        || avaliacaoFisica.getBF() < 14 && avaliacaoFisica.getIdade() >= 40 && avaliacaoFisica.getIdade() <= 49
                        || avaliacaoFisica.getBF() < 15 && avaliacaoFisica.getIdade() >= 50 && avaliacaoFisica.getIdade() <= 59) {
                    System.out.println("De acordo com a sua avaliacao fisica voce tem o fisico de um atleta, parabens!!!");
                } else if (avaliacaoFisica.getBF() >= 11 && avaliacaoFisica.getBF() <= 13 && avaliacaoFisica.getIdade() <= 29
                        || avaliacaoFisica.getBF() >= 12 && avaliacaoFisica.getBF() <= 14 && avaliacaoFisica.getIdade() >= 30 && avaliacaoFisica.getIdade() <= 39
                        || avaliacaoFisica.getBF() >= 14 && avaliacaoFisica.getBF() <= 16 && avaliacaoFisica.getIdade() >= 40 && avaliacaoFisica.getIdade() <= 49
                        || avaliacaoFisica.getBF() >= 15 && avaliacaoFisica.getBF() <= 17 && avaliacaoFisica.getIdade() >= 50 && avaliacaoFisica.getIdade() <= 59) {
                    System.out.println("De acordo com a sua avaliacao fisica voce tem um fisico considerado bom, parabens!!!");
                } else if (avaliacaoFisica.getBF() >= 14 && avaliacaoFisica.getBF() <= 20 && avaliacaoFisica.getIdade() <= 29
                        || avaliacaoFisica.getBF() >= 15 && avaliacaoFisica.getBF() <= 21 && avaliacaoFisica.getIdade() >= 30 && avaliacaoFisica.getIdade() <= 39
                        || avaliacaoFisica.getBF() >= 17 && avaliacaoFisica.getBF() <= 23 && avaliacaoFisica.getIdade() >= 40 && avaliacaoFisica.getIdade() <= 49
                        || avaliacaoFisica.getBF() >= 18 && avaliacaoFisica.getBF() <= 24 && avaliacaoFisica.getIdade() >= 50 && avaliacaoFisica.getIdade() <= 59) {
                    System.out.println("De acordo com a sua avaliacao fisica voce tem um fisico considerado normal, parabens!!!");
                } else if (avaliacaoFisica.getBF() >= 21 && avaliacaoFisica.getBF() <= 23 && avaliacaoFisica.getIdade() <= 29
                        || avaliacaoFisica.getBF() >= 22 && avaliacaoFisica.getBF() <= 24 && avaliacaoFisica.getIdade() >= 30 && avaliacaoFisica.getIdade() <= 39
                        || avaliacaoFisica.getBF() >= 24 && avaliacaoFisica.getBF() <= 26 && avaliacaoFisica.getIdade() >= 40 && avaliacaoFisica.getIdade() <= 49
                        || avaliacaoFisica.getBF() >= 25 && avaliacaoFisica.getBF() <= 27 && avaliacaoFisica.getIdade() >= 50 && avaliacaoFisica.getIdade() <= 59) {
                    System.out.println("De acordo com a sua avaliacao fisica voce tem um fisico com um peso considerado elevado, procure um especialista por favor!");
                } else if (avaliacaoFisica.getBF() > 23 && avaliacaoFisica.getIdade() <= 29
                        || avaliacaoFisica.getBF() > 24 && avaliacaoFisica.getIdade() >= 30 && avaliacaoFisica.getIdade() <= 39
                        || avaliacaoFisica.getBF() > 26 && avaliacaoFisica.getIdade() >= 40 && avaliacaoFisica.getIdade() <= 49
                        || avaliacaoFisica.getBF() > 27 && avaliacaoFisica.getIdade() >= 50 && avaliacaoFisica.getIdade() <= 59) {
                    System.out.println("De acordo com a sua avaliacao fisica voce tem um fisico com um peso considerado muito elevado, procure um especialista com urgencia por favor!");
                }

            } else if (avaliacaoFisica != null && avaliacaoFisica.getPessoa().equals(pessoa) && avaliacaoFisica.getPessoa().getSexo().equals("Feminino") || avaliacaoFisica != null && avaliacaoFisica.getPessoa().getSexo().equals("feminino")) {
                if (avaliacaoFisica.getBF() < 16 && avaliacaoFisica.getIdade() <= 29
                        || avaliacaoFisica.getBF() < 17 && avaliacaoFisica.getIdade() >= 30 && avaliacaoFisica.getIdade() <= 39
                        || avaliacaoFisica.getBF() < 18 && avaliacaoFisica.getIdade() >= 40 && avaliacaoFisica.getIdade() <= 49
                        || avaliacaoFisica.getBF() < 19 && avaliacaoFisica.getIdade() >= 50 && avaliacaoFisica.getIdade() <= 59) {
                    System.out.println("De acordo com a sua avaliacao fisica voce tem o fisico de um atleta, parabens!!!");
                } else if (avaliacaoFisica.getBF() >= 16 && avaliacaoFisica.getBF() <= 19 && avaliacaoFisica.getIdade() <= 29
                        || avaliacaoFisica.getBF() >= 17 && avaliacaoFisica.getBF() <= 20 && avaliacaoFisica.getIdade() >= 30 && avaliacaoFisica.getIdade() <= 39
                        || avaliacaoFisica.getBF() >= 18 && avaliacaoFisica.getBF() <= 21 && avaliacaoFisica.getIdade() >= 40 && avaliacaoFisica.getIdade() <= 49
                        || avaliacaoFisica.getBF() >= 19 && avaliacaoFisica.getBF() <= 22 && avaliacaoFisica.getIdade() >= 50 && avaliacaoFisica.getIdade() <= 59) {
                    System.out.println("De acordo com a sua avaliacao fisica voce tem um fisico considerado bom, parabens!!!");
                } else if (avaliacaoFisica.getBF() >= 20 && avaliacaoFisica.getBF() <= 28 && avaliacaoFisica.getIdade() <= 29
                        || avaliacaoFisica.getBF() >= 21 && avaliacaoFisica.getBF() <= 29 && avaliacaoFisica.getIdade() >= 30 && avaliacaoFisica.getIdade() <= 39
                        || avaliacaoFisica.getBF() >= 22 && avaliacaoFisica.getBF() <= 30 && avaliacaoFisica.getIdade() >= 40 && avaliacaoFisica.getIdade() <= 49
                        || avaliacaoFisica.getBF() >= 23 && avaliacaoFisica.getBF() <= 31 && avaliacaoFisica.getIdade() >= 50 && avaliacaoFisica.getIdade() <= 59) {
                    System.out.println("De acordo com a sua avaliacao fisica voce tem um fisico considerado normal, parabens!!!");
                } else if (avaliacaoFisica.getBF() >= 29 && avaliacaoFisica.getBF() <= 31 && avaliacaoFisica.getIdade() <= 29
                        || avaliacaoFisica.getBF() >= 30 && avaliacaoFisica.getBF() <= 32 && avaliacaoFisica.getIdade() >= 30 && avaliacaoFisica.getIdade() <= 39
                        || avaliacaoFisica.getBF() >= 31 && avaliacaoFisica.getBF() <= 33 && avaliacaoFisica.getIdade() >= 40 && avaliacaoFisica.getIdade() <= 49
                        || avaliacaoFisica.getBF() >= 32 && avaliacaoFisica.getBF() <= 34 && avaliacaoFisica.getIdade() >= 50 && avaliacaoFisica.getIdade() <= 59) {
                    System.out.println("De acordo com a sua avaliacao fisica voce tem um fisico com um peso considerado elevado, procure um especialista por favor!");
                } else if (avaliacaoFisica.getBF() > 31 && avaliacaoFisica.getIdade() <= 29
                        || avaliacaoFisica.getBF() > 32 && avaliacaoFisica.getIdade() >= 30 && avaliacaoFisica.getIdade() <= 39
                        || avaliacaoFisica.getBF() > 33 && avaliacaoFisica.getIdade() >= 40 && avaliacaoFisica.getIdade() <= 49
                        || avaliacaoFisica.getBF() > 34 && avaliacaoFisica.getIdade() >= 50 && avaliacaoFisica.getIdade() <= 59) {
                    System.out.println("De acordo com a sua avaliacao fisica voce tem um fisico com um peso considerado muito elevado, procure um especialista com urgencia por favor!");
                }

            }
        }
    }

    public boolean alterarPeso(String nome, double novoPeso) {
        if (vetorDeAvaliacaoFisica != null) {
            for (AvaliacaoFisica avaliacaoFisica : vetorDeAvaliacaoFisica) {
                if (avaliacaoFisica != null && avaliacaoFisica.getPessoa().getNome().equals(nome)) {
                    avaliacaoFisica.setPeso(novoPeso);
                    return true;
                }
            }
        }
        return false;
    }

}

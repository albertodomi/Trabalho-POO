package Model;

import java.time.LocalDate;

public class PessoaDAO {

    public PessoaDAO() {
        Pessoa pessoa1 = new Pessoa("Joao", "Masculino", LocalDate.of(2002, 4, 30), "jv", "jv123", "sedentario");
        Pessoa pessoa2 = new Pessoa("Alberto", "Masculino", LocalDate.of(2002, 1, 20), "ab", "ab123", "levemente ativo");

        inserirPessoa(pessoa1);
        inserirPessoa(pessoa2);
    }

    private Pessoa vetorDePessoas[] = new Pessoa[5];

    public boolean inserirPessoa(Pessoa pessoa) {
        for (int i = 0; i < vetorDePessoas.length; i++) {

            if (vetorDePessoas[i] == null) {
                vetorDePessoas[i] = pessoa;

                return true;
            }
        }
        return false;
    }

    public void exibirPessoas() {
        for (Pessoa pessoa : vetorDePessoas) {

            if (pessoa != null) {
                System.out.println(pessoa + "\n");
            }
        }
    }

    public Pessoa buscaPessoaQuerendoLogar(String login, String senha) {
        for (Pessoa pessoa : vetorDePessoas) {

            if (pessoa != null && pessoa.getLogin().equals(login)
                    && pessoa.getSenha().equals(senha)) {
                return pessoa;
            }
        }

        return null;
    }

    public Pessoa buscaPessoaPorNome(String nomePessoa) {
        for (Pessoa pessoa : vetorDePessoas) {

            if (pessoa != null && pessoa.getNome().equals(nomePessoa)) {
                return pessoa;
            }
        }
        return null;
    }

    public boolean removerPessoaPorNome(String nomePessoa) {
        for (int i = 0; i < vetorDePessoas.length; i++) {
            if (vetorDePessoas[i] != null && vetorDePessoas[i].getNome().equals(nomePessoa)) {
                vetorDePessoas[i] = null;
                return true;
            }
        }
        return false;

    }

    public boolean alterarNome(String nome, String novoNome) {
        for (Pessoa vetorDePessoa : vetorDePessoas) {
            if (vetorDePessoa != null && vetorDePessoa.getNome().equals(nome)) {
                vetorDePessoa.setNome(novoNome);
                return true;
            }

        }
        return false;

    }
}

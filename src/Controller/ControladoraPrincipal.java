package Controller;

import Model.SeguirDAO;
import Model.Mensagem;
import Model.MensagemDAO;
import Model.Pessoa;
import Model.PessoaDAO;
import Model.Post;
import Model.PostDAO;
import Model.Seguir;
import Model.Alimento;
import Model.AlimentoDAO;
import Model.AlimentoRefeicoesDAO;
import Model.AvaliacaoFisica;
import Model.AvaliacaoFisicaDAO;
import Model.Dieta;
import Model.DietaDAO;
import Model.Preferencias;
import Model.PreferenciasDAO;
import Model.Refeicoes;
import Model.RefeicoesDAO;
import Model.TipodeDieta;
import Model.TipodeDietaDAO;
import View.TelaInicial;
import View.TelaMenuCadastro;
import View.TelaOpcoesRedeSocial;
import View.TelaPrincipal;
import View.TelaPrescricaoDieta;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ControladoraPrincipal {

    Scanner scanner = new Scanner(System.in);

    PessoaDAO pessoaDAO = new PessoaDAO();
    SeguirDAO seguirDAO = new SeguirDAO();
    MensagemDAO mensagemDAO = new MensagemDAO();
    PostDAO postDAO = new PostDAO(pessoaDAO);
    AlimentoDAO alimentoDAO = new AlimentoDAO();
    AvaliacaoFisicaDAO avaliacaoFisicaDAO = new AvaliacaoFisicaDAO(pessoaDAO);
    TipodeDietaDAO tipodeDietaDAO = new TipodeDietaDAO();
    PreferenciasDAO preferenciaDAO = new PreferenciasDAO(pessoaDAO, alimentoDAO);
    DietaDAO dietaDAO = new DietaDAO(pessoaDAO, avaliacaoFisicaDAO, tipodeDietaDAO);
    RefeicoesDAO refeicaoDAO = new RefeicoesDAO();
    AlimentoRefeicoesDAO alimentoRefeicoesDAO = new AlimentoRefeicoesDAO();

    public ControladoraPrincipal() {

        menuInicial();
    }

    public void menuInicial() {
        TelaInicial telaInicial = new TelaInicial();

        int opcao;

        do {

            opcao = telaInicial.telaMenuInicial();

            switch (opcao) {
                case 1: {
                    Pessoa pessoaEncontrada = fazLogin();

                    if (pessoaEncontrada != null) {

                        menuPrincipal(pessoaEncontrada);

                    } else {
                        System.out.println("\nNao foi possivel fazer login");
                    }
                    break;
                }

                case 2: {
                    cadastrarPessoa();
                    break;
                }
            }

        } while (opcao != 0);

    }

    public Pessoa fazLogin() {
        System.out.print("\nInforme aqui o seu login: ");
        String login = scanner.nextLine();

        System.out.print("\nInforme aqui a sua senha: ");
        String senha = scanner.nextLine();

        Pessoa pessoaEncontrada = pessoaDAO.buscaPessoaQuerendoLogar(login, senha);

        return pessoaEncontrada;

    }

    public void cadastrarPessoa() {
        System.out.print("\nInforme o nome da pessoa: ");
        String nome = scanner.nextLine();

        System.out.print("\nInforme o sexo da pessoa: ");
        String sexo = scanner.nextLine();

        System.out.print("\nInforme a data de nascimento da pessoa (dd-MM-yyy): ");
        String nascimento = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dataNascimento = LocalDate.parse(nascimento, formatter);

        System.out.print("\nInforme o login da pessoa: ");
        String login = scanner.nextLine();

        System.out.print("\nInforme a senha da pessoa: ");
        String senha = scanner.nextLine();

        System.out.print("\nInforme o tipo de usuario da pessoa: ");
        String tipodeusuario = scanner.nextLine();

        Pessoa pessoa = new Pessoa(nome, sexo, dataNascimento, login, senha, tipodeusuario);

        boolean inserido = pessoaDAO.inserirPessoa(pessoa);

        if (inserido) {
            System.out.println("\nCadastrou!");
        } else {
            System.out.println("\nNao Cadastrou.");
        }

    }

    public void menuPrincipal(Pessoa pessoa) {

        TelaPrincipal telaPrincipal = new TelaPrincipal();

        int opcao;

        do {

            opcao = telaPrincipal.telaMenuPrincipal();

            switch (opcao) {
                case 1: {

                    menuOpcoesCadastro(pessoa);

                    break;
                }
            }

        } while (opcao != 0);

    }

    public void menuOpcoesCadastro(Pessoa pessoa) {

        TelaMenuCadastro telaMenuCadastro = new TelaMenuCadastro();

        int opcao;

        do {

            opcao = telaMenuCadastro.telaMenuOpcoesCadastro();

            switch (opcao) {
                case 1: {

                    System.out.println("\n");
                    System.out.println(pessoa);
                    break;
                }

                case 2: {

                    System.out.println("\n");
                    pessoaDAO.exibirPessoas();
                    break;
                }
                case 3: {
                    atualizarNome(pessoa);
                    break;
                }
                case 4: {
                    menuOpcoesRedeSocial(pessoa);
                    break;
                }
                case 5: {
                    menuOpcoesPrescricaoDieta(pessoa);
                    break;
                }
            }

        } while (opcao != 0);

    }

    public void atualizarNome(Pessoa pessoa) {

        System.out.print("Informe o seu novo nome: ");
        String novoNome = scanner.nextLine();

        boolean nomeAtualizado = pessoaDAO.alterarNome(pessoa.getNome(), novoNome);

        if (nomeAtualizado == true) {
            System.out.println("\nNome atualizado com sucesso!");
        } else {
            System.out.println("\nNao foi possivel atualizar o nome.");
        }

    }

    public void menuOpcoesRedeSocial(Pessoa pessoa) {

        TelaOpcoesRedeSocial telaOpcoesRedeSocial = new TelaOpcoesRedeSocial();

        int opcao;

        do {

            opcao = telaOpcoesRedeSocial.telaMenuOpcoesRedeSocial();

            switch (opcao) {

                case 1: {
                    System.out.println("\n");
                    pedirPermissaoParaSeguirPessoa(pessoa);
                    break;
                }

                case 2: {

                    System.out.println("\n");
                    seguirDAO.verSolicitacoesParaSeguirFeitaPeloDonoDaConta(pessoa);
                    break;
                }

                case 3: {

                    System.out.println("\n");
                    responderSolicitacaoParaSeguir(pessoa);
                    break;
                }

                case 4: {

                    System.out.println("\n");
                    seguirDAO.verPessoasQueAlguemSegue(pessoa);
                    break;
                }

                case 5: {

                    System.out.println("\n");
                    mensagemDAO.verMensagensDeUmaPessoa(pessoa);
                    break;
                }

                case 6: {
                    System.out.println("\n");
                    escreverMensagem(pessoa);
                    break;
                }
                case 7: {
                    System.out.println("\n");
                    postDAO.verMeusPosts(pessoa);

                    break;
                }
                case 8: {
                    System.out.println("\n");
                    fazerPost(pessoa);

                    break;
                }
                case 9: {
                    System.out.println("\n");
                    verTimeline(pessoa);

                    break;
                }
                case 10: {
                    System.out.println("\n");
                    verPostsDeUmaPessoaEspecifica();

                    break;
                }
            }

        } while (opcao != 0);

    }

    public void pedirPermissaoParaSeguirPessoa(Pessoa pessoaDonoDaConta) {
        System.out.println("\n");
        pessoaDAO.exibirPessoas();

        System.out.print("\nInforme o nome da pessoa que deseja seguir: ");
        String nomePessoaSeguir = scanner.nextLine();

        Pessoa pessoaBuscadaParaSeguir = pessoaDAO.buscaPessoaPorNome(nomePessoaSeguir);

        if (pessoaBuscadaParaSeguir != null) {
            Seguir seguir = new Seguir(pessoaDonoDaConta, pessoaBuscadaParaSeguir, false);

            boolean solicitou = seguirDAO.inserirSeguir(seguir);

            if (solicitou == true) {
                System.out.println("\nSolicitacao realizada com sucesso!");
            } else {
                System.out.println("\nNao foi possivel realizar a solicitacao.");
            }
        } else {
            System.out.println("\nPessoa nao encontrada!");
        }
    }

    public void responderSolicitacaoParaSeguir(Pessoa pessoa) {
        TelaOpcoesRedeSocial telaOpcoesRedeSocial = new TelaOpcoesRedeSocial();

        System.out.println("\n");
        seguirDAO.verSolicitacoesParaSeguirFeitaPeloSolicitanteParaDonodaConta(pessoa);

        System.out.print("\nInforme o nome da pessoa que deseja responder a solicitacao: ");
        String nomePessoaQueSolicitou = scanner.nextLine();

        Seguir solicitacaoParaSeguir = seguirDAO.buscaSolicitcaoPorNomeDoSolicitante(pessoa, nomePessoaQueSolicitou);

        if (solicitacaoParaSeguir != null) {

            int opcaoResposta;

            opcaoResposta = telaOpcoesRedeSocial.telaMenuOpcoesRespostaSolicitacao();

            if (opcaoResposta == 1) {

                boolean respostaSolicitacao = seguirDAO.responderSolicitacaoDeSeguir(solicitacaoParaSeguir, true);

                if (respostaSolicitacao == true) {
                    System.out.println("\nSolicitacao aceita com sucesso!");
                } else {
                    System.out.println("\nNao foi possivel aceitar a solicitacao.");
                }
            } else {
                System.out.println("\nSolicitacao negada com sucesso!");
            }

        } else {
            System.out.println("\nSolicitacao nao encontrada.");
        }

    }

    public void escreverMensagem(Pessoa pessoaOrigem) {
        System.out.println("\n");
        seguirDAO.verPessoasQueAlguemSegue(pessoaOrigem);

        System.out.print("\nInforme o nome da pessoa para qual deseja mandar a mensagem: ");
        String nomePessoaMensagem = scanner.nextLine();

        Pessoa pessoaDestino = seguirDAO.buscaSeguidorPorNome(nomePessoaMensagem);

        if (pessoaDestino != null) {
            System.out.print("\nEscreva a sua mensagem: ");
            String mensagemPessoa = scanner.nextLine();

            Mensagem mensagem = new Mensagem(pessoaOrigem, pessoaDestino, mensagemPessoa);

            boolean mensagemEnviada = mensagemDAO.inserirMensagem(mensagem);

            if (mensagemEnviada == true) {
                System.out.println("\nMensagem enviada com sucesso!");
            } else {
                System.out.println("\nNao foi possivel enviar a mensagem.");
            }
        } else {
            System.out.println("\nPessoa nao encontrada!");
        }
    }

    public void fazerPost(Pessoa pessoa) {
        System.out.print("\nFaça o seu post: ");
        String postPessoa = scanner.nextLine();

        Post post = new Post(pessoa, postPessoa);

        boolean postPostado = postDAO.inserirPost(post);

        if (postPostado == true) {
            System.out.println("\nPostagem feita com sucesso!");
        } else {
            System.out.println("\nNao foi possivel realizar a postagem.");
        }
    }

    public void verTimeline(Pessoa pessoa) {
        System.out.println("\n");
        seguirDAO.buscaPessoasQueAlguemSegue(pessoa, postDAO);

    }

    public void verPostsDeUmaPessoaEspecifica() {
        
        pessoaDAO.exibirPessoas();
        
        System.out.print("\nInforme o nome da pessoa que você deseja ver os posts: ");
        String nomePessoaBuscada = scanner.nextLine();

        Pessoa pessoaBuscada = pessoaDAO.buscaPessoaPorNome(nomePessoaBuscada);

        if (pessoaBuscada != null) {
            postDAO.verMeusPosts(pessoaBuscada);
        } else {
            System.out.println("\nPessoa não encontrada!");
        }
    }

    public void menuOpcoesPrescricaoDieta(Pessoa pessoa) {

        TelaPrescricaoDieta telaPrescricaoDieta = new TelaPrescricaoDieta();

        int opcao;

        do {
            opcao = telaPrescricaoDieta.telaMenuPrescricaoDieta();

            switch (opcao) {

                case 1: {
                    System.out.println("\n");
                    menuOpcoesAlimentos(pessoa);

                    break;
                }

                case 2: {
                    System.out.println("\n");
                    gerarDietaRecomendada(pessoa);
                    break;
                }

                case 3: {
                    System.out.println("\n");
                    menuOpcoesAvaliacaoFisica(pessoa);

                    break;
                }

                case 4: {
                    System.out.println("\n");
                    menuOpcoesDieta(pessoa);
                    break;
                }

                case 5: {
                    System.out.println("\n");
                    menuOpcoesPreferencia(pessoa);

                    break;
                }

                case 6: {
                    System.out.println("\n");
                    menuOpcoesRefeicoes(pessoa);
                    break;
                }

                case 7: {
                    System.out.println("\n");
                    menuOpcoesTipoDeDieta(pessoa);
                    break;
                }

                case 8: {
                    System.out.println("\n-------Dietas Personalizadas------------" + "\n");
                    alimentoRefeicoesDAO.exibirMinhaDietaPersonalizada(pessoa);
                    break;
                }

            }

        } while (opcao != 0);

    }

    public void menuOpcoesAlimentos(Pessoa pessoa) {

        TelaPrescricaoDieta telaPrescricaoDieta = new TelaPrescricaoDieta();

        int opcao;

        do {
            opcao = telaPrescricaoDieta.telaMenuOpcoesAlimentos();

            switch (opcao) {

                case 1: {
                    System.out.println("\n");
                    cadastrarAlimento();
                    break;
                }

                case 2: {
                    System.out.println("\n");
                    alimentoDAO.exibirAlimentos();
                    break;
                }

                case 3: {
                    System.out.println("\n");
                    atualizarNomeAlimento();
                    break;
                }

                case 4: {
                    System.out.println("\n");
                    buscarAlimento();
                    break;
                }

            }

        } while (opcao != 0);

    }

    public void cadastrarAlimento() {

        System.out.print("\nInforme o nome do alimento: ");
        String nome = scanner.nextLine();

        System.out.print("\nInforme a quantidade de carboidratos do alimento: ");
        double carboidratos = Double.parseDouble(scanner.nextLine());

        System.out.print("\nInforme a quantidade de proteinas do alimento: ");
        double proteinas = Double.parseDouble(scanner.nextLine());

        System.out.print("\nInforme a quantidade de gorduras do alimento: ");
        double gorduras = Double.parseDouble(scanner.nextLine());

        Alimento alimento = new Alimento(nome, carboidratos, proteinas, gorduras);

        boolean inserido = alimentoDAO.inserirAlimento(alimento);

        if (inserido) {
            System.out.println("\nCadastrou!");
        } else {
            System.out.println("\nNao Cadastrou.");
        }
    }

    public void atualizarNomeAlimento() {

        System.out.print("\nInforme o nome do alimento que quer atualizar: ");
        String nome = scanner.nextLine();

        System.out.print("\nInforme o novo nome desse alimento: ");
        String novoNome = scanner.nextLine();

        boolean atualizado = alimentoDAO.alterarNome(nome, novoNome);

        if (atualizado) {
            System.out.println("\nAtualizou!");
        } else {
            System.out.println("\nNão foi possivel atualizar o nome!");
        }

    }

    public void buscarAlimento() {
        
        alimentoDAO.exibirAlimentos();

        System.out.print("\nInforme o nome do alimento que deseja buscar: ");
        String nome = scanner.nextLine();

        Alimento alimentoBuscado = alimentoDAO.buscaAlimentoPorNome(nome);
        
        if(alimentoBuscado != null)
        {
            System.out.println(alimentoBuscado);
        }
        else
        {
            System.out.println("Alimento nao encontrado!");
        }
        
    }

    public void removerAlimento() {

        System.out.print("\nInforme o nome do alimento que deseja remover: ");
        String nome = scanner.nextLine();
        alimentoDAO.removerAlimentoPorNome(nome);

    }

    public void menuOpcoesAvaliacaoFisica(Pessoa pessoa) {

        TelaPrescricaoDieta telaPrescricaoDieta = new TelaPrescricaoDieta();

        int opcao;

        do {
            opcao = telaPrescricaoDieta.telaMenuOpcoesAvaliacaoFisica();

            switch (opcao) {

                case 1: {
                    System.out.println("\n");
                    cadastrarAvaliacaoFisica(pessoa);
                    break;
                }

                case 2: {
                    System.out.println("\n");
                    avaliacaoFisicaDAO.verMinhaAvaliacaoFisica(pessoa);
                    break;
                }

                case 3: {
                    System.out.println("\n");
                    atualizarPesoAvaliacaoFisica();
                    break;
                }

                case 4: {
                    System.out.println("\n");
                    buscarAvaliacaoFisica();
                    break;
                }
                case 5: {
                    System.out.println("\n");
                    avaliacaoFisicaDAO.exibirAvaliacoesFisicas();
                    break;
                }
                case 6: {
                    System.out.println("\n");
                    avaliacaoFisicaDAO.verMeuRelatorio(pessoa);
                    break;
                }

            }

        } while (opcao != 0);

    }

    private void cadastrarAvaliacaoFisica(Pessoa pessoa) {

        System.out.print("\nInforme o peso da pessoa: ");
        double peso = Double.parseDouble(scanner.nextLine());

        System.out.print("\nInforme a altura da pessoa: ");
        double altura = Double.parseDouble(scanner.nextLine());

        System.out.print("\nInforme a idade da pessoa: ");
        int idade = Integer.parseInt(scanner.nextLine());

        System.out.print("\nInforme o tamanho do pescoco da pessoa: ");
        double pescoco = Double.parseDouble(scanner.nextLine());

        System.out.print("\nInforme o tamanho da cintura da pessoa: ");
        double cintura = Double.parseDouble(scanner.nextLine());

        System.out.print("\nInforme o tamanho do quadril da pessoa: ");
        double quadril = Double.parseDouble(scanner.nextLine());

        AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica(pessoa, peso, altura, idade, pescoco, cintura, quadril);

        boolean inserido = avaliacaoFisicaDAO.inserirAvaliacaoFisica(avaliacaoFisica);

        if (inserido) {
            System.out.println("\nCadastrou!");
        } else {
            System.out.println("\nNao Cadastrou.");
        }

    }

    public void atualizarPesoAvaliacaoFisica() {

        System.out.print("\nInforme o nome da pessoa com avaliacao fisica que voce quer atualizar: ");
        String nome = scanner.nextLine();

        System.out.print("\nInforme o novo peso para ser usado na avaliacao fisica: ");
        double novoPeso = Double.parseDouble(scanner.nextLine());

        boolean atualizado = avaliacaoFisicaDAO.alterarPeso(nome, novoPeso);

        if (atualizado) {
            System.out.println("\nAtualizou!");
        } else {
            System.out.println("\nNão foi possivel atualizar o peso!");
        }

    }

    public void buscarAvaliacaoFisica() {
        
        avaliacaoFisicaDAO.exibirAvaliacoesFisicas();

        System.out.print("\nInforme o nome da pessoa que possui uma avaliacao fisica que deseja buscar: ");
        String nome = scanner.nextLine();

        AvaliacaoFisica avaliacaoBuscada = avaliacaoFisicaDAO.buscaAvaliacaoFisicaPorNomeDaPessoa(nome);
        
        if(avaliacaoBuscada != null)
        {
            System.out.println(avaliacaoBuscada);
        }
        else
        {
            System.out.println("Avalicao fisica não encontrada");
        }

    }

    public void menuOpcoesTipoDeDieta(Pessoa pessoa) {

        TelaPrescricaoDieta telaPrescricaoDieta = new TelaPrescricaoDieta();

        int opcao;

        do {
            opcao = telaPrescricaoDieta.telaMenuOpcoesTipoDeDieta();

            switch (opcao) {

                case 1: {
                    System.out.println("\n");
                    cadastrarTipoDeDieta();
                    break;
                }

                case 2: {
                    System.out.println("\n");
                    atualizarTipoDeDieta();
                    break;
                }

                case 3: {
                    System.out.println("\n");
                    buscarTipoDeDieta();
                    break;
                }

                case 4: {
                    System.out.println("\n");
                    tipodeDietaDAO.exibirTiposDeDieta();
                    break;
                }

            }

        } while (opcao != 0);

    }

    private void cadastrarTipoDeDieta() {

        System.out.print("Insira aqui o nome do tipo da dieta: ");
        String nome = scanner.nextLine();

        System.out.print("\nInsira aqui a porcentagem de carboidrato desse tipo de dieta: ");
        Double carboidrato = Double.parseDouble(scanner.nextLine());

        System.out.print("\nInsira aqui a porcentagem de proteina desse tipo de dieta: ");
        Double proteina = Double.parseDouble(scanner.nextLine());

        System.out.print("\nInsira aqui a porcentagem de gordura desse tipo de dieta: ");
        Double gordura = Double.parseDouble(scanner.nextLine());

        TipodeDieta tipoDeDieta = new TipodeDieta(nome, carboidrato, proteina, gordura);

        boolean inserido = tipodeDietaDAO.inserirTipoDeDieta(tipoDeDieta);

        if (inserido) {
            System.out.println("\nNovo tipo de dieta criado!");
        } else {
            System.out.println("\nNao foi possivel criar.");
        }
    }

    private void atualizarTipoDeDieta() {

        System.out.print("\nInsira aqui o ID do tipo de dieta que deseja atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("\nInsira aqui o novo ID do tipo de dieta: ");
        int novoID = Integer.parseInt(scanner.nextLine());

        boolean atualizado = tipodeDietaDAO.alterarIDTipoDeDieta(id, novoID);

        if (atualizado) {
            System.out.println("\nAtualizou!");
        } else {
            System.out.println("\nNao foi possivel atualizar o ID!");
        }

    }

    private void buscarTipoDeDieta() {
        
        tipodeDietaDAO.exibirTiposDeDieta();

        System.out.println("\nInsira aqui o nome do tipo de dieta que deseja buscar: ");
        String nome = scanner.nextLine();

        TipodeDieta tipoDeDietaBuscada = tipodeDietaDAO.buscaTipoDeDietaPorNome(nome);
        
        if(tipoDeDietaBuscada != null)
        {
            System.out.println(tipoDeDietaBuscada);
        }
        else
        {
            System.out.println("Tipo de dieta nao encontrado!");
        }
        

    }

    public void menuOpcoesPreferencia(Pessoa pessoa) {

        TelaPrescricaoDieta telaPrescricaoDieta = new TelaPrescricaoDieta();

        int opcao;

        do {
            opcao = telaPrescricaoDieta.telaMenuOpcoesPreferencias();

            switch (opcao) {

                case 1: {
                    System.out.println("\n");
                    cadastrarPreferencia(pessoa);
                    break;
                }

                case 2: {
                    System.out.println("\n");
                    atualizarPreferencia();
                    break;
                }

                case 3: {
                    System.out.println("\n");
                    preferenciaDAO.verMinhasPreferencias(pessoa);
                    break;
                }

            }

        } while (opcao != 0);

    }

    private void cadastrarPreferencia(Pessoa pessoa) {
        alimentoDAO.exibirAlimentos();

        System.out.print("\nInsira aqui o nome do alimento que gostaria de colocar na sua preferencia: ");
        String nomeAlimento = scanner.nextLine();

        Alimento alimento = alimentoDAO.buscaAlimentoPorNome(nomeAlimento);

        Preferencias preferencias = new Preferencias(pessoa, alimento);

        boolean inserido = preferenciaDAO.inserirPreferencias(preferencias);

        if (inserido) {
            System.out.println("\nNova preferencia criada!");
        } else {
            System.out.println("\nNao foi possivel criar a nova preferencia.");
        }

    }

    private void atualizarPreferencia() {

        System.out.print("\nInsira aqui o ID da preferencia que deseja atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("\nInsira aqui o novo ID da preferencia: ");
        int novoID = Integer.parseInt(scanner.nextLine());

        boolean atualizado = preferenciaDAO.alterarIDPreferencia(id, novoID);

        if (atualizado) {
            System.out.println("\nAtualizou!");
        } else {
            System.out.println("\nNao foi possivel atualizar o ID!");
        }
    }

    public void menuOpcoesDieta(Pessoa pessoa) {

        TelaPrescricaoDieta telaPrescricaoDieta = new TelaPrescricaoDieta();

        int opcao;

        do {
            opcao = telaPrescricaoDieta.telaMenuOpcoesDieta();

            switch (opcao) {

                case 1: {
                    System.out.println("\n");
                    cadastrarDieta(pessoa);
                    break;
                }

                case 2: {
                    System.out.println("\n");
                    atualizarDieta();
                    break;
                }

                case 3: {
                    System.out.println("\n");
                    dietaDAO.exibirMinhaDieta(pessoa);
                    break;
                }
                case 4: {
                    System.out.println("\n");
                    dietaDAO.exibirDieta();
                    break;
                }

            }

        } while (opcao != 0);

    }

    private void cadastrarDieta(Pessoa pessoa) {

        AvaliacaoFisica avaliacaoFisica = avaliacaoFisicaDAO.buscaAvaliacaoFisicaPorPessoa(pessoa);

        System.out.println("\n");

        tipodeDietaDAO.exibirTiposDeDieta();

        System.out.print("Insira aqui o ID do tipo de dieta desejado: ");
        int id = Integer.parseInt(scanner.nextLine());

        TipodeDieta tipodeDieta = tipodeDietaDAO.buscaTipoDeDietaPorId(id);

        TelaPrescricaoDieta telaPrescricaoDieta = new TelaPrescricaoDieta();

        int codigoObjetivo = telaPrescricaoDieta.telaMenuOpcoesObjetivosDieta();

        String objetivoDieta = DefineObjetivoDieta(codigoObjetivo);

        if (objetivoDieta == null) {
            System.out.println("Objetivo invalido!!!!");
        } else {
            double calorias = DefineCalorias(objetivoDieta, avaliacaoFisica);

            System.out.print("Insira aqui o numero de refeicoes da dieta: ");
            int numeroRefeicoes = Integer.parseInt(scanner.nextLine());

            Dieta dieta = new Dieta(pessoa,
                    avaliacaoFisica, tipodeDieta, objetivoDieta, calorias, numeroRefeicoes);

            boolean adicionado = dietaDAO.inserirDieta(dieta);

            if (adicionado) {
                System.out.println("\nDieta adicionada com sucesso!");
            } else {
                System.out.println("\nNao foi possivel adicionar");
            }
        }

    }

    private void atualizarDieta() {
        
        System.out.print("\nInsira aqui o ID da dieta que deseja atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("\nInsira aqui o novo ID da dieta: ");
        int novoID = Integer.parseInt(scanner.nextLine());

        boolean atualizado = dietaDAO.alterarIDDeDieta(id, novoID);

        if (atualizado) {
            System.out.println("\nAtualizou!");
        } else {
            System.out.println("\nNao foi possivel atualizar o ID!");
        }

    }

    private String DefineObjetivoDieta(int opcaoDieta) {
        switch (opcaoDieta) {
            case 1:
                return "Diminuir o peso";
            case 2:
                return "Manter o peso";
            case 3:
                return "Melhorar composição corporal";
            case 4:
                return "Aumentar o peso";
            default:
                return null;
        }
    }

    private double DefineCalorias(String objetivoDieta, AvaliacaoFisica avaliacaoFisica) {
        switch (objetivoDieta) {
            case "Diminuir o peso": {
                double tbm = avaliacaoFisica.getTMB();
                double calorias = tbm - 300;
                return calorias;
            }
            case "Aumentar o peso": {
                double tbm = avaliacaoFisica.getTMB();
                double calorias = tbm + 300;
                return calorias;
            }
            case "Manter o peso":
            case "Melhorar composição corporal": {
                double tbm = avaliacaoFisica.getTMB();
                double calorias = tbm;
                return calorias;
            }
            default:
                break;
        }
        return 0;
    }

    public void menuOpcoesRefeicoes(Pessoa pessoa) {

        TelaPrescricaoDieta telaPrescricaoDieta = new TelaPrescricaoDieta();

        int opcao;

        do {
            opcao = telaPrescricaoDieta.telaMenuOpcoesRefeicoes();

            switch (opcao) {

                case 1: {
                    System.out.println("\n");
                    cadastrarRefeicao(pessoa);
                    break;
                }

                case 2: {
                    System.out.println("\n");
                    atualizarRefeicao();
                    break;
                }

                case 3: {
                    System.out.println("\n");
                    refeicaoDAO.exibirMinhaRefeicao(pessoa);
                    break;
                }
                case 4: {
                    System.out.println("\n");
                    refeicaoDAO.exibirRefeicao();
                    break;
                }

            }

        } while (opcao != 0);

    }

    private void cadastrarRefeicao(Pessoa pessoa) {

        dietaDAO.exibirMinhaDieta(pessoa);

        System.out.println("\n");

        System.out.print("\nInsira aqui o ID da dieta: ");
        int id = Integer.parseInt(scanner.nextLine());

        Dieta dieta = dietaDAO.buscaDietaPessoaPorID(id);

        System.out.print("\nInsira aqui o nome da refeicao: ");
        String nomeRefeicao = scanner.nextLine();

        double calorias = dieta.getCalorias();

        double caloriaRefeicao = calorias / dieta.getNroRefeicoes();

        Refeicoes refeicao = new Refeicoes(dieta, dieta.getTipodedieta().getCarboidrato(), dieta.getTipodedieta().getProteina(),
                dieta.getTipodedieta().getGordura(), caloriaRefeicao, nomeRefeicao);

        boolean inserido = refeicaoDAO.inserirRefeicao(refeicao);

        if (inserido) {
            System.out.println("\nRefeicao adicionada com sucesso!");
        } else {
            System.out.println("\nNao foi possivel adicionar refeicao");
        }

    }

    private void atualizarRefeicao() {
        
        System.out.print("\nInsira aqui o ID da refeicao que deseja atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("\nInsira aqui o novo ID da refeicao: ");
        int novoID = Integer.parseInt(scanner.nextLine());

        boolean atualizado = refeicaoDAO.alterarIDRefeicao(id, novoID);

        if (atualizado) {
            System.out.println("\nAtualizou!");
        } else {
            System.out.println("\nNao foi possivel atualizar o ID!");
        }

    }

    private void gerarDietaRecomendada(Pessoa pessoa) {
        refeicaoDAO.exibirMinhaRefeicao(pessoa);

        System.out.println("\n");

        System.out.print("Insira aqui o ID da refeicao: ");
        int id = Integer.parseInt(scanner.nextLine());

        Refeicoes refeicoes = refeicaoDAO.buscaRefeicaoPorID(id);

        System.out.println("\n");

        if (refeicoes != null) {
            boolean gerouDieta = preferenciaDAO.gerarDietaComPreferencia(refeicoes, alimentoRefeicoesDAO);

            if (gerouDieta == true) {
                System.out.println("\nSua dieta personalizada foi gerada com sucesso!");
            } else {
                System.out.println("\nNao foi possivel gerar a sua dieta personalizada");
            }
        }
        else
        {
            System.out.println("\nDieta Nao Encontrada.");
        }

    }

}

package Model;

public class DietaDAO {
    
    private Dieta vetorDeDieta[] = new Dieta[5];
    
    public DietaDAO(PessoaDAO pessoaDAO, AvaliacaoFisicaDAO avaliacaoFisicaDAO, TipodeDietaDAO tipodeDietaDAO) {    
        
        Pessoa pessoa1 = pessoaDAO.buscaPessoaPorNome("Joao");
        AvaliacaoFisica avaliacaoFisica1 = avaliacaoFisicaDAO.buscaAvaliacaoFisicaPorPessoa(pessoa1);
        TipodeDieta tipodeDieta1 = tipodeDietaDAO.buscaTipoDeDietaPorId(1);
        double caloria = DefineCalorias("Diminuir o peso", avaliacaoFisica1);
        Dieta dieta = new Dieta(pessoa1, avaliacaoFisica1, tipodeDieta1, "Diminuir o peso", caloria , 3);
        
        inserirDieta(dieta);
        
    }

    public boolean inserirDieta(Dieta dieta) {
        for (int i = 0; i < vetorDeDieta.length; i++) {

            if (vetorDeDieta[i] == null) {
                vetorDeDieta[i] = dieta;

                return true;
            }
        }
        return false;
    }

    public void exibirDieta() {
        for (Dieta dieta : vetorDeDieta) {

            if (dieta != null) {
                System.out.println(dieta + "\n");
            }
        }
    }
    
    public Dieta buscaDietaPessoaPorID(int id)
    {
        for (Dieta dieta : vetorDeDieta) {
            if(dieta != null && dieta.getId() == id)
            {
                return dieta;
            }
        }
        return null;
    }
    
    public void exibirMinhaDieta(Pessoa pessoa) {
        for (Dieta dieta : vetorDeDieta) {

            if (dieta != null && dieta.getPessoa().equals(pessoa)) {
                System.out.println(dieta + "\n");
            }
        }
    }
    
    private double DefineCalorias(String objetivoDieta, AvaliacaoFisica avaliacaoFisica) {
        switch (objetivoDieta) {
            case "Diminuir o peso":
            {
                double tbm = avaliacaoFisica.getTMB();
                double calorias = tbm - 300;
                return calorias;
            }
            case "Aumentar o peso":
            {
                double tbm = avaliacaoFisica.getTMB();
                double calorias = tbm + 300;
                return calorias;
            }
            case "Manter o peso":
            case "Melhorar composição corporal":
            {
                double tbm = avaliacaoFisica.getTMB();
                double calorias = tbm;
                return calorias;
            }
            default:
                break;
        }
        return 0;
    }

    public boolean alterarIDDeDieta(int id, int novoID) {
        for (Dieta dieta : vetorDeDieta) {
            if (dieta != null && dieta.getId() == id) {
                dieta.setId(novoID);
                return true;
            }

        }
        return false;

    }
    
}

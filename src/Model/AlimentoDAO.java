package Model;

public class AlimentoDAO {

    public AlimentoDAO() {

        Alimento arroz = new Alimento("arroz branco", 28.18, 2.5, 0.23); //124,79
        Alimento feijao = new Alimento("feijao carioca", 13.61, 5.04, 0.85); //82,25 TOTAL = 451,29
        Alimento bife = new Alimento("bife de vaca", 0, 27.29, 15.01); // 244,25
        Alimento cafe = new Alimento("cafe", 30, 30, 30);
        Alimento alface = new Alimento("alface", 1.63, 0.5, 0.08);
        Alimento asa_frango = new Alimento ("asa de frango", 0, 26.64, 19.3);
        Alimento presunto = new Alimento ("presunto", 3.83, 16.6, 8.6);
        Alimento costela = new Alimento ("costela de frango", 0, 27.91, 14.57);
        Alimento pao_branco = new Alimento ("pao", 50.61, 7.64, 3.29);
        Alimento ovo = new Alimento ("ovo", 0.77, 12.58, 9.94);
        Alimento peixe = new Alimento ("peixe", 0, 20.08, 1.7);
        Alimento leite = new Alimento ("leite", 4.71, 3.29, 2);
        Alimento manteiga = new Alimento ("manteiga", 0.06, 0.85, 81.11);
        Alimento arroz_legumes = new Alimento ("arroz_legumes", 19.14, 2.54, 1.37);
        Alimento feijao_preto = new Alimento ("feijao preto", 16.56, 6.03, 0.29);
        Alimento salada_batata = new Alimento ("salda de batata", 11.17, 2.68, 8.2);

        inserirAlimento(arroz);
        inserirAlimento(feijao);
        inserirAlimento(bife);
        inserirAlimento(cafe);
        inserirAlimento(alface);
        inserirAlimento(asa_frango);
        inserirAlimento(costela);
        inserirAlimento(pao_branco);
        inserirAlimento(ovo);
        inserirAlimento(peixe);
        inserirAlimento(leite);
        inserirAlimento(manteiga);
        inserirAlimento(arroz_legumes);
        inserirAlimento(feijao_preto);
        inserirAlimento(salada_batata);
        inserirAlimento(presunto);

    }

    private Alimento vetorDeAlimentos[] = new Alimento[10];

    public boolean inserirAlimento(Alimento alimento) {
        for (int i = 0; i < vetorDeAlimentos.length; i++) {

            if (vetorDeAlimentos[i] == null) {
                vetorDeAlimentos[i] = alimento;

                return true;
            }
        }
        return false;

    }

    public void exibirAlimentos() {
        for (Alimento alimento : vetorDeAlimentos) {

            if (alimento != null) {
                System.out.println(alimento + "\n");
            }
        }
    }

    public Alimento buscaAlimentoPorNome(String nomeAlimento) {
        for (Alimento alimento : vetorDeAlimentos) {

            if (alimento != null && alimento.getNome().equals(nomeAlimento)) {
                return alimento;
            }
        }
        return null;
    }

    public boolean removerAlimentoPorNome(String nomeAlimento) {
        for (int i = 0; i < vetorDeAlimentos.length; i++) {
            if (vetorDeAlimentos[i] != null && vetorDeAlimentos[i].getNome().equals(nomeAlimento)) {
                vetorDeAlimentos[i] = null;
                return true;
            }
        }
        return false;

    }

    public boolean alterarNome(String nome, String novoNome) {
        for (Alimento vetorDeAlimento : vetorDeAlimentos) {
            if (vetorDeAlimento != null && vetorDeAlimento.getNome().equals(nome)) {
                vetorDeAlimento.setNome(novoNome);
                return true;
            }

        }
        return false;

    }

}

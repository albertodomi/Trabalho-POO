package Model;

public class TipodeDietaDAO {

    public TipodeDietaDAO() {

        TipodeDieta tipoDeDieta1 = new TipodeDieta("Equilibrada", 40, 30, 30);
        TipodeDieta tipoDeDieta2 = new TipodeDieta("Low Carb", 30, 50, 20);
        TipodeDieta tipoDeDieta3 = new TipodeDieta("Cetogênica", 15, 15, 70);

        inserirTipoDeDieta(tipoDeDieta1);
        inserirTipoDeDieta(tipoDeDieta2);
        inserirTipoDeDieta(tipoDeDieta3);

    }

    private TipodeDieta vetorDeTipoDeDieta[] = new TipodeDieta[5];

    public boolean inserirTipoDeDieta(TipodeDieta tipoDeDieta) {
        for (int i = 0; i < vetorDeTipoDeDieta.length; i++) {

            if (vetorDeTipoDeDieta[i] == null) {
                vetorDeTipoDeDieta[i] = tipoDeDieta;

                return true;
            }
        }
        return false;
    }

    public void exibirTiposDeDieta() {
        for (TipodeDieta tipoDeDieta : vetorDeTipoDeDieta) {

            if (tipoDeDieta != null) {
                System.out.println(tipoDeDieta + "\n");
            }
        }
    }

    public TipodeDieta buscaTipoDeDietaPorNome(String nomeTipodeDieta) {
        for (TipodeDieta tipoDeDieta : vetorDeTipoDeDieta) {

            if (tipoDeDieta != null && tipoDeDieta.getNome().equals(nomeTipodeDieta)) {
                return tipoDeDieta;
            }
        }
        return null;
    }
    
    public TipodeDieta buscaTipoDeDietaPorId(int IdTipoDieta) {
        for (TipodeDieta tipoDeDieta : vetorDeTipoDeDieta) {

            if (tipoDeDieta != null && tipoDeDieta.getId() == IdTipoDieta) {
                return tipoDeDieta;
            }
        }
        return null;
    }

    public boolean removerTipoDeDietaPorNome(String nomeTipodeDieta) {
        for (int i = 0; i < vetorDeTipoDeDieta.length; i++) {
            if (vetorDeTipoDeDieta[i] != null && vetorDeTipoDeDieta[i].getNome().equals(nomeTipodeDieta)) {
                vetorDeTipoDeDieta[i] = null;
                return true;
            }
        }
        return false;

    }

    public boolean alterarIDTipoDeDieta(int id, int novoID) {
        for (TipodeDieta tipoDeDieta : vetorDeTipoDeDieta) {
            if (tipoDeDieta != null && tipoDeDieta.getId() == id) {
                tipoDeDieta.setId(novoID);
                return true;
            }

        }
        return false;

    }
}

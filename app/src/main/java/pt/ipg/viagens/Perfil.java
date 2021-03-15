package pt.ipg.viagens;

public class Perfil {
    String id;
    public String getid() {
        return id;
    }

        String Nome;

    public String getNome() {
        return Nome;
    }

    public String getLocalidade() {
        return Localidade;
    }

    public String getData() {
        return Data;
    }

    public String getTelemovel() {
        return Telemovel;
    }

    String Localidade;
        String Data;
        String Telemovel;

        public Perfil(){

        }
    public Perfil(String id, String nome, String localidade, String data, String telemovel) {
        Nome = nome;
        Localidade = localidade;
        Data = data;
        Telemovel = telemovel;
    }

}

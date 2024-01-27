package model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Contato implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String sobrenome;
    private List<Telefone> telefones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    @Override
    public String toString() {
        //return id + " | " + nome + " " + sobrenome + " | " + telefones.toString();
        return id + " | " + nome + " " + sobrenome + " | " + telefones;
    }
}

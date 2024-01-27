package model;

import java.io.Serial;
import java.io.Serializable;

public class Telefone implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String ddd;
    private Long numero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Telefone(Long id, String ddd, Long numero) {
        this.id = id;
        this.ddd = ddd;
        this.numero = numero;
    }

    @Override
    public String toString() {
        return ddd + numero;
    }
}

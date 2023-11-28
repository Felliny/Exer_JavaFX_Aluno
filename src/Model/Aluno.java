package Model;

import java.time.LocalDate;

public class Aluno {

    String id;
    String ra;
    String nome;
    LocalDate nascimento;

    public String getId() {
        return id;
    }

    public Aluno setId(String id) {
        this.id = id;
        return (this);
    }

    public String getRa() {
        return ra;
    }

    public Aluno setRa(String ra) {
        this.ra = ra;
        return (this);
    }

    public String getNome() {
        return nome;
    }

    public Aluno setNome(String nome) {
        this.nome = nome;
        return (this);
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public Aluno setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
        return (this);
    }
}

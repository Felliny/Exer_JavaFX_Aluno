package Persistence;

import Model.Aluno;

import java.util.List;

public interface AlunoDAO {

    public void adicionar(Aluno aluno);
    public List<Aluno> pesquisar(String ra);


}

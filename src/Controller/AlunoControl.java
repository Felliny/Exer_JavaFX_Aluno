package Controller;

import Model.Aluno;
import Persistence.AlunoDAOImplementation;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public class AlunoControl {

    ObservableList<Aluno> listAluno= FXCollections.observableArrayList();

    private StringProperty idAluno= new SimpleStringProperty();
    private StringProperty raAluno= new SimpleStringProperty();
    private StringProperty nomeAluno= new SimpleStringProperty();
    private ObjectProperty<LocalDate> nascimentoAluno= new SimpleObjectProperty<>(LocalDate.of(2003, 3, 15));

    AlunoDAOImplementation alunoDAOImplementatio= new AlunoDAOImplementation();

    public void populateTable(Aluno a) {
        if (a != null){
            idAluno.set(a.getId());
            raAluno.set(a.getRa());
            nomeAluno.set(a.getNome());
            nascimentoAluno.set(a.getNascimento());
        }

    }

    public void adicionar(){
        Aluno aluno= new Aluno()
                        .setId(idAluno.get())
                        .setRa(raAluno.get())
                        .setNome(nomeAluno.get())
                        .setNascimento(nascimentoAluno.get());

        if (aluno != null){
            alunoDAOImplementatio.adicionar(aluno);
            idAluno.set("");
            raAluno.set("");
            nomeAluno.set("");
        }
    }

    public void pesquisar(){
        String nome= nomeAluno.get();
        listAluno.clear();
        idAluno.set("");
        raAluno.set("");
        List<Aluno> novaList= alunoDAOImplementatio.pesquisar(nome);
        listAluno.addAll(novaList);
    }

    public ObservableList<Aluno> getListAluno() {
        return listAluno;
    }

    public StringProperty idAlunoProperty() {
        return idAluno;
    }

    public StringProperty raAlunoProperty() {
        return raAluno;
    }

    public StringProperty nomeAlunoProperty() {
        return nomeAluno;
    }

    public ObjectProperty<LocalDate> nascimentoAlunoProperty() {
        return nascimentoAluno;
    }
}

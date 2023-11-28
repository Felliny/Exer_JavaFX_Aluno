package View;

import Controller.AlunoControl;
import Model.Aluno;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class AlunoBoundary extends Application {

    private TextField txtId= new TextField();
    private TextField txtRa= new TextField();
    private TextField txtNome= new TextField();
    private DatePicker txtNascimento= new DatePicker();
    private Button btnAdicionar = new Button("Adicionar");
    private Button btnPesquisar= new Button("Pesquisar");
    private TableView<Aluno> alunoTableView= new TableView<>();

    AlunoControl alunoControl= new AlunoControl();

    public void generateBidings(){
        Bindings.bindBidirectional(txtId.textProperty(), alunoControl.idAlunoProperty());
        Bindings.bindBidirectional(txtRa.textProperty(), alunoControl.raAlunoProperty());
        Bindings.bindBidirectional(txtNome.textProperty(), alunoControl.nomeAlunoProperty());
        Bindings.bindBidirectional(txtNascimento.valueProperty(), alunoControl.nascimentoAlunoProperty());
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane= new BorderPane();
        GridPane gridPane= new GridPane();
        pane.setTop(gridPane);
        pane.setCenter(alunoTableView);

        Scene scn = new Scene(pane, 600, 400);

        gridPane.add(new Label("Id:"), 0, 0);
        gridPane.add(txtId, 1, 0);
        gridPane.add(new Label("Ra:"), 0, 1);
        gridPane.add(txtRa, 1, 1);
        gridPane.add(new Label("Nome:"), 0, 2);
        gridPane.add(txtNome, 1, 2);
        gridPane.add(new Label("Nascimento:"), 0, 3);
        gridPane.add(txtNascimento, 1, 3);
        gridPane.add(btnAdicionar, 0, 4);
        gridPane.add(btnPesquisar, 1, 4);

        btnAdicionar.setOnMouseClicked(event -> alunoControl.adicionar());
        btnPesquisar.setOnMouseClicked(event -> alunoControl.pesquisar());


        generateBidings();
        generateTable();

        primaryStage.setTitle("Gestão de Alunos");
        primaryStage.setScene(scn);
        primaryStage.show();

    }

    public void generateTable(){
        TableColumn<Aluno, String> columnId= new TableColumn<>("Id");
        columnId.setCellValueFactory(item ->
                new ReadOnlyStringWrapper(String.valueOf(item.getValue().getId()))
        );

        TableColumn<Aluno, String> columnRa= new TableColumn<>("RA");
        columnRa.setCellValueFactory(item -> new ReadOnlyStringWrapper(item.getValue().getRa()));

        TableColumn<Aluno, String> columnNome= new TableColumn<>("Nome");
        columnNome.setCellValueFactory(item -> new ReadOnlyStringWrapper(
                item.getValue().getNome()
                )
        );

        TableColumn<Aluno, String> columnNascimento= new TableColumn<>("Nascimento");
        columnNascimento.setCellValueFactory(item -> {
            DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date= item.getValue().getNascimento();
            String dateStr= dtf.format(date);
            return  new ReadOnlyStringWrapper(dateStr);
        });

        alunoTableView.getColumns().addAll(columnId, columnRa, columnNome, columnNascimento);
        alunoTableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, novo) -> {
                    alunoControl.populateTable(novo);
                }
        );

        alunoTableView.setItems(alunoControl.getListAluno());

    }

    public static void main(String[] args) {
        Application.launch();
    }
}

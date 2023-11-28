package Persistence;

import Model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAOImplementation implements AlunoDAO{

    ConnectionBuilder connectionBuilde= new ConnectionBuilder();
    @Override
    public void adicionar(Aluno aluno) {
        try {
        Connection connection= connectionBuilde.getConnection();
        String sql= "insert into alunoTable (id, ra, nome, nascimento)\n" +
                "values (?, ?, ?, ?);";
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(String.valueOf(aluno.getId())));
            ps.setString(2, aluno.getRa());
            ps.setString(3, aluno.getNome());
            ps.setDate(4, Date.valueOf(aluno.getNascimento()));

            ps.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Aluno> pesquisar(String nome) {
        List<Aluno> list= new ArrayList<>();
        try {
            Connection connection= connectionBuilde.getConnection();
            String sql= """
                    select *
                    from alunoTable
                    where nome = ?""";
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet rs= ps.executeQuery();

            while (rs.next()){
                Aluno aluno= new Aluno();

                aluno.setId(String.valueOf(rs.getInt(1)));
                aluno.setRa(rs.getString(2));
                aluno.setNome(rs.getString(3));
                aluno.setNascimento(rs.getDate(4).toLocalDate());


                list.add(aluno);
            }
            connection.close();

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

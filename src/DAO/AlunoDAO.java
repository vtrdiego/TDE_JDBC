package DAO;

import com.mysql.cj.jdbc.Driver;
import model.Aluno;
import model.Curso;

import java.sql.*;
import java.util.ArrayList;

public class AlunoDAO {

    public static boolean inserirAluno(Aluno alu) {

        boolean sucesso=false;

        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);

            Connection c = DriverManager.getConnection("jdbc:mysql://localhost", "root", "diego27");

            PreparedStatement stmt = c.prepareStatement("INSERT INTO sgbd_curso.aluno (matricula,nome,codigo_curso) VALUES (?,?,?)");

            stmt.setInt(1, alu.getMatricula());
            stmt.setString(2, alu.getNome());
            stmt.setInt(3, alu.getCodCurso());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                sucesso=true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    public static ArrayList<Aluno> buscarAluno(){

        ArrayList<Aluno> alunos =new ArrayList();

        try{
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);

            Connection c = DriverManager.getConnection("jdbc:mysql://localhost","root","diego27");

            PreparedStatement stmt =c.prepareStatement("SELECT * FROM sgbd_curso.aluno");

            ResultSet rs=stmt.executeQuery();

            while(rs.next()){

                int matricula = rs.getInt("matricula");
                String nome = rs.getString("nome");
                int cod_curso = rs.getInt("codigo_curso");

                Aluno al = new Aluno();
                al.setMatricula(matricula);
                al.setNome(nome);
                al.setCodCurso(cod_curso);

                alunos.add(al);

            }

            stmt.close();
            c.close();


        }catch(SQLException e){
            e.printStackTrace();
        }

        return alunos;

    }

}

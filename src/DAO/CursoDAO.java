package DAO;
import com.mysql.cj.jdbc.Driver;
import model.Curso;
import java.sql.*;
import java.util.ArrayList;

public class CursoDAO {

    public static boolean inserirCurso(Curso cur) {

        boolean sucesso=false;

        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);

            Connection c = DriverManager.getConnection("jdbc:mysql://localhost", "root", "diego27");

            PreparedStatement stmt = c.prepareStatement("INSERT INTO sgbd_curso.curso (codigo,nome,carga_horaria) VALUES (?,?,?)");

            stmt.setInt(1, cur.getCodigo());
            stmt.setString(2, cur.getNome());
            stmt.setString(3, cur.getCarga());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                sucesso=true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sucesso;
    }

    public static ArrayList<Curso> buscarCurso(){

        ArrayList<Curso> cursos =new ArrayList();

        try{
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);

            Connection c = DriverManager.getConnection("jdbc:mysql://localhost","root","diego27");

            PreparedStatement stmt =c.prepareStatement("SELECT * FROM sgbd_curso.curso");

            ResultSet rs=stmt.executeQuery();

            while(rs.next()){

                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                String carga_horaria = rs.getString("carga_horaria");

                Curso cs = new Curso();
                cs.setCodigo(codigo);
                cs.setNome(nome);
                cs.setCarga(carga_horaria);

                cursos.add(cs);

            }

            stmt.close();
            c.close();


        }catch(SQLException e){
            e.printStackTrace();
        }

        return cursos;

    }

}

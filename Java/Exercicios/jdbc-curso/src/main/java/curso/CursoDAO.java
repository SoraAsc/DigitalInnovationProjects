package curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    //Read One
    public Curso list(int id){
        Curso curso = null;

        try(Connection conn = ConnectionFactory.getConnection()){
            String sql = "SELECT * FROM curso WHERE id = ?";

            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1,id);

            ResultSet rs = pStmt.executeQuery();

            rs.next();
            String nome = rs.getString("nome");
            int duracaoHoras = rs.getInt("duracao_horas");

            curso = new Curso(id,nome,duracaoHoras);

        } catch (SQLException e) {
            System.out.println("\nHouve uma falha ao tentar encontrar o curso");
            e.printStackTrace();
        }
        return curso;
    }

    //Read
    public List<Curso> list(){
        List<Curso> todosCursos = new ArrayList<>();

        try(Connection conn = ConnectionFactory.getConnection()){
            String sql = "SELECT * FROM curso";

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int duracaoHoras = rs.getInt("duracao_horas");

                todosCursos.add(new Curso(
                        id,
                        nome,
                        duracaoHoras
                ));
            }
        } catch (SQLException e){
            System.out.println("\nListagem de Cursos FALHOU");
            e.printStackTrace();
        }

        return todosCursos;
    }

    //Create
    public void create(Curso curso){
        try(Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO curso(nome,duracao_horas) VALUES(?,?)";

            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,curso.getNome());
            pStmt.setInt(2,curso.getDuracaoHoras());

            int rowsAffected = pStmt.executeUpdate();
            System.out.println("Inserção BEM SUCEDIDA!. Foi adicionado um total de " + rowsAffected + " linha");

        } catch (SQLException e) {
            System.out.println("Inserção FALHOU");
            e.printStackTrace();
        }
    }

    //Create Multiple
    public void create(List<Curso> cursoList){
        try(Connection conn = ConnectionFactory.getConnection()) {
            if (cursoList.size()<=0){ System.out.println("\nA Lista Informada está Vazia"); return; }

            String sql = "INSERT INTO curso(nome,duracao_horas) VALUES" + "(?,?),".repeat(cursoList.size()-1)+"(?,?)";

            PreparedStatement pStmt = conn.prepareStatement(sql);
            for (int i = 0,j = 1; i < cursoList.size(); i++,j+=2) {

                pStmt.setString(j,cursoList.get(i).getNome());
                pStmt.setInt(j+1,cursoList.get(i).getDuracaoHoras());
            }
            int rowsAffected = pStmt.executeUpdate();
            System.out.println("Inserção BEM SUCEDIDA!. Foi adicionado um total de " + rowsAffected + " linha");

        } catch (SQLException e){
            System.out.println("Inserção FALHOU");
            e.printStackTrace();
        }

    }


    //Update
    public void update(Curso curso){
        try(Connection conn = ConnectionFactory.getConnection()){
            String sql = "UPDATE curso SET nome = ?,duracao_horas = ? WHERE id = ?";

            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,curso.getNome());
            pStmt.setInt(2,curso.getDuracaoHoras());
            pStmt.setInt(3,curso.getId());

            int rowsAffected = pStmt.executeUpdate();
            System.out.println("Atualização BEM SUCEDIDA! Foi atualizada: " + rowsAffected + " linha");
        } catch (SQLException e) {
            System.out.println("Atualização FALHOU!");
            e.printStackTrace();
        }
    }

    //Delete
    public void delete(int id){
        try(Connection conn = ConnectionFactory.getConnection()){
            String sql = "DELETE FROM curso WHERE id = ?";

            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1,id);

            int rowsAffected = pStmt.executeUpdate();
            System.out.println("Delete BEM SUCEDIDA! Foi deletado " + rowsAffected + " linha");

        } catch (SQLException e) {
            System.out.println("Delete FALHOU!");
            e.printStackTrace();
        }
    }

}

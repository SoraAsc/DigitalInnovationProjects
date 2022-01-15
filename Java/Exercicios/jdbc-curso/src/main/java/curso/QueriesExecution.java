package curso;

import java.util.ArrayList;
import java.util.List;

public class QueriesExecution {

    public static void main(String[] args) {

        //create table curso ( id integer primary key auto_increment, nome varchar(80) not null, duracao_horas integer not null);

        CursoDAO cursoDAO = new CursoDAO();

        //cursoDAO.create(new Curso("Informática",20));
        //cursoDAO.create(new Curso("Mecânica",15));

        //cursoDAO.list().stream().forEach(System.out::println);

/*        System.out.println();
        cursoDAO.update(new Curso(1,"Informatica",300));
        cursoDAO.update(new Curso(2,"Automacao",150));

        System.out.println();
        cursoDAO.list().stream().forEach(System.out::println);*/

/*        System.out.println();
        cursoDAO.delete(1);
        cursoDAO.list().stream().forEach(System.out::println);
        cursoDAO.delete(2);
        cursoDAO.list().stream().forEach(System.out::println);*/

        //System.out.println("\n"+cursoDAO.list(2));

/*        System.out.println();

        cursoDAO.create(new ArrayList<>() {{

        }});

        cursoDAO.create(new ArrayList<>() {{
            add(new Curso("Test0", 23) );
        }});

        cursoDAO.create(new ArrayList<>() {{
            add(new Curso("Test1", 210) );
            add(new Curso("Test2", 240) );
            add(new Curso("Test3", 560) );
            add(new Curso("Test4", 100) );
        }});

        cursoDAO.list().stream().forEach(System.out::println);*/
    }
}

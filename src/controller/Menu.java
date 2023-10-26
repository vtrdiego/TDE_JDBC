package controller;
import java.util.ArrayList;
import java.util.Scanner;
import DAO.AlunoDAO;
import DAO.CursoDAO;
import model.Aluno;
import model.Curso;
import static DAO.AlunoDAO.inserirAluno;
import static DAO.CursoDAO.inserirCurso;

public class Menu {

    public static void main(String[] arg){

        Scanner scanner = new Scanner(System.in);
        ArrayList<Curso> cursos = new ArrayList();
        ArrayList<Aluno> alunos = new ArrayList();

        boolean rodando = true;

        while(rodando){
            //Menu + input o usuário
            System.out.println("[1] - Cadastrar curso");
            System.out.println("[2] - Cadastrar aluno");
            System.out.println("[3] - Consultar curso de aluno");
            System.out.println("[4] - Listar alunos de um curso");
            System.out.println("[5] - Sair");
            System.out.println("\nDigite a opção:");
            String opcao = scanner.nextLine();

            //Processar o input do usuário
            switch(opcao){
                case "1": {
                    System.out.println("\nDigite o código do curso:");
                    String cd=scanner.nextLine();
                    int codigo=Integer.parseInt(cd);

                    System.out.println("Digite o nome do curso:");
                    String nome_curso=scanner.nextLine();

                    System.out.println("Digite a carga horária:");
                    String carga_horaria=scanner.nextLine();

                    Curso c=new Curso();
                    c.setCodigo(codigo);
                    c.setNome(nome_curso);
                    c.setCarga(carga_horaria);

                    //INSERIR AS INFORMAÇÕES NO DAO
                    inserirCurso(c);
                    System.out.println("\nCurso cadastrado com sucesso!\n");
                    break;
                }
                case "2": {
                    alunos=AlunoDAO.buscarAluno();
                    cursos=CursoDAO.buscarCurso();

                    boolean confirma=false;

                    System.out.println("\nDigite a matrícula do aluno:");
                    String mt=scanner.nextLine();
                    int matricula=Integer.parseInt(mt);

                    System.out.println("Digite o nome do aluno:");
                    String nome_aluno=scanner.nextLine();

                    System.out.println("Digite o código do curso do aluno:");
                    String cd=scanner.nextLine();
                    int codigo=Integer.parseInt(cd);

                    //Verificar se matrícula de aluno já possui curso vinculado
                    for(Aluno a: alunos){
                        int mat=a.getMatricula();
                        int cod_curso=a.getCodCurso();

                        if(matricula==mat && cod_curso!=0){
                            confirma=true;
                            break;
                        }
                    }
                    if(confirma) {
                        System.out.println("\nMatrícula de aluno já cadastrada em um curso!\n");
                        break;
                    }//Fim da verificação

                    //Verificar se o curso existe no banco de dados
                    for(Curso c: cursos){
                        int cod_curso=c.getCodigo();

                        if(codigo==cod_curso){
                            confirma=true;
                            break;
                        }
                    }
                    if(!confirma){
                        System.out.println("\nCódigo de curso não existe!\n");
                        break;
                    }//Fim da verificação

                    Aluno a =new Aluno();
                    a.setMatricula(matricula);
                    a.setNome(nome_aluno);
                    a.setCodCurso(codigo);
                    inserirAluno(a);
                    System.out.println("\nAluno cadastrado com sucesso!\n");
                    break;
                }
                case "3": {

                    alunos=AlunoDAO.buscarAluno();
                    cursos=CursoDAO.buscarCurso();

                    System.out.println("\nDigite a matrícula do aluno para consultar curso:");
                    String mat=scanner.nextLine();
                    int matricula=Integer.parseInt(mat);

                    //Consultar o curso do aluno via matricula e codigo
                    for(Aluno a:alunos){

                        int matr=a.getMatricula();
                        int cod=a.getCodCurso();

                        if(matricula==matr){

                            for(Curso c:cursos){

                                int codi=c.getCodigo();

                                if(cod==codi){
                                    System.out.println("Curso do aluno: "+c.getNome()+"\n");
                                    break;
                                }
                            }

                        }
                    }
                    break;
                }
                case "4": {

                    alunos = AlunoDAO.buscarAluno();
                    cursos = CursoDAO.buscarCurso();

                    System.out.println("Digite o código do curso para exibir alunos:");
                    String cd=scanner.nextLine();
                    int codigo=Integer.parseInt(cd);

                    //Consultar os alunos que estão no determinado curso
                    System.out.println("Alunos:");
                    for(Aluno a:alunos){

                        int cod_curso=a.getCodCurso();
                        String nome=a.getNome();

                        if(cod_curso==codigo){
                            System.out.println("- "+nome);
                        }
                    }
                    System.out.println("\n");
                    break;
                }
                case "5": {
                    rodando= false;
                    System.out.println("\nPrograma encerrado!\n");
                    break;
                }
                default: {
                    break;
                }
            }


        }//while

    }//main

}//fim

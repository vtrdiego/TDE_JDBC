package model;

public class Aluno {

    private int matricula;
    private String nome;
    private int cod_curso;

    public int getMatricula(){
        return matricula;
    }

    public void setMatricula(int matricula){
        this.matricula=matricula;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome=nome;
    }

    public int getCodCurso(){
        return cod_curso;
    }

    public void setCodCurso(int cod_curso){
        this.cod_curso=cod_curso;
    }

}

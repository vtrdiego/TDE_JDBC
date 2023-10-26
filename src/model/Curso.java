package model;

public class Curso {

    private int codigo;
    private String nome;
    private String carga_horaria;

    public int getCodigo(){
        return codigo;
    }

    public void setCodigo(int codigo){
        this.codigo = codigo;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome=nome;
    }

    public String getCarga(){
        return carga_horaria;
    }

    public void setCarga(String carga_horaria){
        this.carga_horaria=carga_horaria;
    }

}
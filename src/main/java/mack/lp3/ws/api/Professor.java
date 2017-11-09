package mack.lp3.ws.api;

import java.util.Date;

public class Professor {
    private long id;
    private String nome;
    private int matricula;
    private String area;
    private Date contratacao;
    private Date dataNascimento;
    private String titulo;
    
    public Professor() {}
    public Professor(long id, String n, int m, String a, Date c, String t, Date d) {
        this.id = id;
        nome = n;
        matricula = m;
        area = a;
        contratacao = c;
        titulo = t;
        dataNascimento = d;
    }
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String n) { nome = n; }
    public int getMatricula() { return matricula; }
    public void setMatricula(int m) { matricula = m; }
    public String getArea() { return area; }
    public void setArea(String a) { area = a; }
    public Date getContratacao() { return contratacao; }
    public void setContratacao(Date c) { contratacao = c; }
    public Date getDataNascimento(){return dataNascimento;}
    public void setDataNascimento(Date d){dataNascimento = d;}
    public String getTitulo(){return titulo;}
    public void setTitulo(String t){titulo = t;}
}

public class Livro {
    private String titulo;
    private String autor;
    private int idLivro;
    private String status;

    public Livro(String titulo, String autor, int idLivro, String status) {
        this.titulo = titulo;
        this.autor = autor;
        this.idLivro = idLivro;
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

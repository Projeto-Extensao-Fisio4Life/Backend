package projeto.fisio4life.fenix.Dto.servicoDto;

public class ServicoResponseDto {

    private Integer idServico;
    private Integer idClinica;
    private String nome;
    private Integer quantidadeSessoes;
    private Double valor;
    private String tipo;

    public ServicoResponseDto() {
    }

    public ServicoResponseDto(Integer idServico, Integer idClinica, String nome, Integer quantidadeSessoes, Double valor, String tipo) {
        this.idServico = idServico;
        this.idClinica = idClinica;
        this.nome = nome;
        this.quantidadeSessoes = quantidadeSessoes;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    public Integer getIdClinica() {
        return idClinica;
    }

    public void setIdClinica(Integer idClinica) {
        this.idClinica = idClinica;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidadeSessoes() {
        return quantidadeSessoes;
    }

    public void setQuantidadeSessoes(Integer quantidadeSessoes) {
        this.quantidadeSessoes = quantidadeSessoes;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

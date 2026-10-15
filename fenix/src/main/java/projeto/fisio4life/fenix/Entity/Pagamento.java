package projeto.fisio4life.fenix.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento", nullable = false)
    private Integer id_pagamento;

    @ManyToOne
    @JoinColumn(name = "fk_paciente", nullable = true)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "fk_servico", nullable = true)
    private Servico servico;

    @Column(name = "valor", nullable = true, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "forma_pagamento", nullable = true, length = 45)
    private String forma_pagamento;

    @Column(name = "status_pagamento", nullable = true, columnDefinition = "TINYINT")
    private Byte status_pagamento;

    @Column(name = "data_concluida", nullable = true, length = 45)
    private String data_concluida;

    public Pagamento() {}
    public Integer getId_pagamento() { return id_pagamento; }
    public void setId_pagamento(Integer valor) { this.id_pagamento = valor; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente valor) { this.paciente = valor; }
    public Servico getServico() { return servico; }
    public void setServico(Servico valor) { this.servico = valor; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public String getForma_pagamento() { return forma_pagamento; }
    public void setForma_pagamento(String valor) { this.forma_pagamento = valor; }
    public Byte getStatus_pagamento() { return status_pagamento; }
    public void setStatus_pagamento(Byte valor) { this.status_pagamento = valor; }
    public String getData_concluida() { return data_concluida; }
    public void setData_concluida(String valor) { this.data_concluida = valor; }
}

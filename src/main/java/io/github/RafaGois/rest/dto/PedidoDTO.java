package io.github.RafaGois.rest.dto;

import io.github.RafaGois.validation.NotEmpityList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDTO {
    //data transfer object

    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private Integer cliente;
    @NotNull(message = "{campo.total-pedido.obrigatorio}")
    private BigDecimal total;
    @NotEmpityList(message = "{campo.itens-pedido.obrigatorio}")
    private List<ItemPedidoDTO> items;

}

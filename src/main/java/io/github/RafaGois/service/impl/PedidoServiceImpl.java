package io.github.RafaGois.service.impl;

import io.github.RafaGois.domain.entity.Cliente;
import io.github.RafaGois.domain.entity.ItemPedido;
import io.github.RafaGois.domain.entity.Pedido;
import io.github.RafaGois.domain.entity.Produto;
import io.github.RafaGois.domain.enums.StatusPedido;
import io.github.RafaGois.domain.repository.Clientes;
import io.github.RafaGois.domain.repository.ItensPedido;
import io.github.RafaGois.domain.repository.Pedidos;
import io.github.RafaGois.domain.repository.Produtos;
import io.github.RafaGois.exception.PedidoNaoEncontradoException;
import io.github.RafaGois.exception.RegraNegocioException;
import io.github.RafaGois.rest.dto.ItemPedidoDTO;
import io.github.RafaGois.rest.dto.PedidoDTO;
import io.github.RafaGois.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItensPedido itensPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer id = dto.getCliente();
        Cliente cliente = clientesRepository.findById(id).orElseThrow( () -> new RegraNegocioException("Código de cliente inválido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedido = converterItens(pedido, dto.getItems());
        repository.save(pedido);
        itensPedidoRepository.saveAll(itemsPedido);

        pedido.setItens(itemsPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {

        return repository.findByIdFetchItens(id);
    }

    @Override
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        repository.findById(id).map( pedido -> {
            pedido.setStatus(statusPedido);
            return repository.save(pedido);
        }).orElseThrow( () -> new PedidoNaoEncontradoException());
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens");
        }

        return items
                .stream()
                .map( dto -> {

                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository.findById(idProduto)
                            .orElseThrow( () -> new RegraNegocioException("Código de produto inválido: "+ idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
        }).collect(Collectors.toList());
    }
}

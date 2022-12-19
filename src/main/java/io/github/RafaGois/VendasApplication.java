package io.github.RafaGois;

import io.github.RafaGois.domain.entity.Cliente;
import io.github.RafaGois.domain.entity.Pedido;
import io.github.RafaGois.domain.repository.Clientes;
import io.github.RafaGois.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init (
            @Autowired Clientes clientes,
            @Autowired Pedidos pedidos
    ) {
        return args -> {

//            System.out.println("SALVANDO CLIENTES");
//            Cliente fulano = new Cliente(1,"Fulano");
//            clientes.save(fulano);
//            Cliente ciclano = new Cliente(2,"Ciclano");
//            clientes.save(ciclano);


//            Pedido p = new Pedido();
//            p.setCliente(fulano);
//            p.setDataPedido(LocalDate.now());
//            p.setTotal(BigDecimal.valueOf(100));
//
//            pedidos.save(p);

//            pedidos.findByCliente(fulano).forEach(System.out::println);

//            System.out.println("IMPRIMINDO CLIENTES");
//            List<Cliente> todosClientes = clientes.findAll();
//            todosClientes.forEach(System.out::println);


//            System.out.println("IMPRIMINDO CLIENTES");
//            List<Cliente> result = clientes.encontrarPorNome("Filipe");
//            result.forEach(System.out::println);

//            todosClientes.forEach(c -> {
//                c.setNome(c.getNome()+" Fontana");
//                clientes.save(c);
//            });
//
//            System.out.println("IMPRIMINDO CLIENTES ATUALIZADOS");
//            todosClientes = clientes.findAll();
//            todosClientes.forEach(System.out::println);
//
//            System.out.println("IMPRIMINDO CLIENTE POR BUSCA");
//            clientes.findByNomeLike("Filipe Fontana").forEach(System.out::println);
//
//            System.out.println("DELETANDO CLIENTES");
//            clientes.findAll().forEach(c -> {
//                clientes.delete(c);
//            });
//
//            todosClientes = clientes.findAll();
//
//            if (todosClientes.isEmpty()) {
//                System.out.println("VAZIO...");
//            } else {
//
//                todosClientes.forEach(System.out::println);
//            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class,args);
    }
}

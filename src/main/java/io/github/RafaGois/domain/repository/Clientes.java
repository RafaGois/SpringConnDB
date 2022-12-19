package io.github.RafaGois.domain.repository;

import io.github.RafaGois.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//query method spring
@Repository
public interface Clientes extends JpaRepository<Cliente, Integer> {

    //Query(value = "select c from Cliente c where c.nome like :nome")
    @Query(value = "select * from Cliente c where c.nome like '%:nome%' ",nativeQuery = true)
    List<Cliente> encontrarPorNome( @Param("nome") String nome);

    @Query("delete from Cliente c where c.nome like :nome")
    @Modifying
    void deleteByNome(@Param("nome") String nome);

    List<Cliente> findByNomeOrId ( String nome, Integer id);

    List<Cliente> findOneByNome (String nome);

    boolean existsByNome(String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos p where c.id = :id ")
    Cliente findClienteFetchPedidos (@Param("id") Integer id);
}

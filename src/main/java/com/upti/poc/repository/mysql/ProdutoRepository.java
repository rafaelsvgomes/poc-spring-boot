package com.upti.poc.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upti.poc.model.mysql.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}

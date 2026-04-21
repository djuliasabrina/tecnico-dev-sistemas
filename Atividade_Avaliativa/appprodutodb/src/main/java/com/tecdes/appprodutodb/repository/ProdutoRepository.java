package com.tecdes.appprodutodb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tecdes.appprodutodb.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}

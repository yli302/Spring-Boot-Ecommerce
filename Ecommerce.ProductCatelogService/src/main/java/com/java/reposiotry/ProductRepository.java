package com.java.reposiotry;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.java.entity.ProductEntity;

@Repository
public interface ProductRepository extends ElasticsearchRepository<ProductEntity, Long>{
	public List<ProductEntity> findByCatalogueName(String catalogueName);
	
	public List<ProductEntity> findByCatalogueNameAndCatagoryName(String catalogueName, String categoryName);

	public Page<ProductEntity> findByCatalogueNameAndCatagoryName(String catalogueName, String categoryName, PageRequest of);


	public Optional<ProductEntity> findByIdAndCatalogueNameAndCatagoryName(long id, String catalogueName, String categoryName);
}

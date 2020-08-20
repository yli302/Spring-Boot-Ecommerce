package com.java.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Data
@Document(indexName = "product")
public class ProductEntity {
	@Id
	private long id;
	@Field(type = FieldType.Text, analyzer = "myanalyzer", searchAnalyzer = "standard")
	private String productName;
	@Field(type = FieldType.Text, analyzer = "myanalyzer", searchAnalyzer = "standard")
	private String catalogueName;
	@Field(type = FieldType.Text, analyzer = "myanalyzer", searchAnalyzer = "standard")
	private String catagoryName;
	private int price;
	private double star;
}

package per.cr.solr.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import per.cr.solr.entity.Product;
import per.cr.solr.entity.Result;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	SolrServer solrServer;

	@Override
	public Result query(SolrQuery solrQuery) throws Exception {
		Result result = new Result();
		List<Product> productList = new ArrayList<>();
		QueryResponse queryResponse = solrServer.query(solrQuery);
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		// 结果数量
		result.setRecordCount(solrDocumentList.getNumFound());
		// 获取内容
		for (SolrDocument solrDocument : solrDocumentList) {
			Product product = new Product();
			product.setCatalog_name((String) solrDocument.get("product_catalog_name"));
			product.setPid((String) solrDocument.get("id"));
			product.setPrice((float) solrDocument.get("product_price"));
			product.setPicture((String) solrDocument.get("product_picture"));
			// 获取高亮信息
			Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
			List<String> list = highlighting.get(solrDocument.get("id")).get("product_name");
			if (list != null && list.size() != 0) {
				product.setName(list.get(0));
			} else {
				product.setName(solrDocument.get("product_name").toString());
			}
			productList.add(product);
		}
		result.setProductList(productList);
		return result;
	}
}

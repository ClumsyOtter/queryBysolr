package per.cr.solr.dao;

import org.apache.solr.client.solrj.SolrQuery;

import per.cr.solr.entity.Result;

public interface ProductDao {

	Result query(SolrQuery solrQuery) throws Exception;

}

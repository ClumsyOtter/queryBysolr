package per.cr.solr.service;


import per.cr.solr.entity.Result;

public interface ProductService {

	Result query(String query, String sort, String product, String price, Integer page) throws Exception;

}

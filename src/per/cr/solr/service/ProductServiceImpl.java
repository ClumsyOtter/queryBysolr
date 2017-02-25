package per.cr.solr.service;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.springframework.stereotype.Service;

import per.cr.solr.dao.ProductDao;
import per.cr.solr.entity.Result;

@Service
public class ProductServiceImpl implements ProductService {

	@Resource
	ProductDao productDao;

	@Override
	public Result query(String query, String sort, String product, String price, Integer page) throws Exception {
		SolrQuery solrQuery = new SolrQuery();
	
		if (query != null && !"".equals(query)) {
			solrQuery.setQuery(query);
		} else {
			solrQuery.setQuery("*:*");
		}
		if ("1".equals(sort)) {
			solrQuery.addSort("product_price", ORDER.asc);
		} else {
			solrQuery.addSort("product_price", ORDER.desc);
		}
		if (product != null && !"".equals(product)) {
			solrQuery.setFilterQueries("product_catalog_name:" + product);
		}
		if (price != null && !"".equals(price)) {
			String[] prices = price.split("-");
			solrQuery.addFilterQuery("product_price : [" + prices[0] + " TO " + prices[1] + "]");
		}
		int pageSize = 10;
		if (page != null && !"0".equals(page))
			solrQuery.setStart((page - 1) * pageSize);
		else
			solrQuery.setStart(1);
		solrQuery.setRows(pageSize);

		solrQuery.setHighlight(false);
		solrQuery.addHighlightField("product_name");
		solrQuery.setHighlightSimplePre("<span style=\"color:red\" >");
		solrQuery.setHighlightSimplePost("</span>");

		solrQuery.set("df", "product_keywords");

		Result result = productDao.query(solrQuery);
		Long recordCount = result.getRecordCount();

		if (recordCount % 10 == 0)
			result.setPageCount((int) (recordCount / 10));
		else
			result.setPageCount((int) (recordCount / 10) + 1);
		if (null == page) {
			result.setCurPage(1);
		} else {
			result.setCurPage(page);
		}

		System.out.println(solrQuery);
		return result;
	}
}

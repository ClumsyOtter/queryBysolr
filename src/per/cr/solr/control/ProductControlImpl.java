package per.cr.solr.control;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import per.cr.solr.entity.Result;
import per.cr.solr.service.ProductService;

@Controller
public class ProductControlImpl {

	@Resource
	ProductService productService;

	@RequestMapping(value="/list.action")
	public String query(String queryString, String sort, String catalog_name, String price, Integer page, Model model)
			throws Exception {
		if(null != catalog_name){
			byte[] utf8890 = catalog_name.getBytes("ISO-8859-1");
			catalog_name = new String(utf8890, "utf-8");
		}
		System.out.println(catalog_name);
		Result result = productService.query(queryString, sort, catalog_name, price, page);
		model.addAttribute("result", result);
		model.addAttribute("catalog_name", catalog_name);
		model.addAttribute("queryString", queryString);
		model.addAttribute("sort", sort);
		model.addAttribute("price", price);	
		return "product_list.jsp";
	}
}

package per.cr.solr.entity;

import java.util.List;

public class Result {
	// ��Ʒ�б�
	private List<Product> productList;
	// ��Ʒ����
	private Long recordCount;
	// ��ҳ��
	private int pageCount;
	// ��ǰҳ
	private int curPage;

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

}

package per.cr.solr.entity;

public class Product {
	// ��Ʒ���
	private String pid;
	// ��Ʒ����
	private String name;
	// ��Ʒ��������
	private String catalog_name;
	// �۸�
	private float price;
	// ��Ʒ����
	private String description;
	// ͼƬ����
	private String picture;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCatalog_name() {
		return catalog_name;
	}

	public void setCatalog_name(String catalog_name) {
		this.catalog_name = catalog_name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}

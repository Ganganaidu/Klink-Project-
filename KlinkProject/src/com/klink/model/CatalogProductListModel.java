package com.klink.model;

public class CatalogProductListModel {

	public int entity_id = 0;
	public int entity_type_id = 0;
	public int attribute_set_id = 0; 
	public String type_id = ""; 
	public String sku = ""; 
	public int has_options = 0;
	public int required_options = 0;
	public String created_at = "";
	public String updated_at = "";
	public String name = "";
	public String description = "";
	public String short_description = "";
	public long price = 0;
	public String image = "";
	public int dd_iskeg = 0;
	public int category_id = 0;
	public String dd_deposit = "";
	public int status = 0;

	public CatalogProductListModel() {

	}

	public CatalogProductListModel(int entity_id, int entity_type_id,
			int attribute_set_id, String type_id, String sku, int has_options,
			int required_options, String created_at, String updated_at,
			String name, String description, String short_description,
			long price, String image, int dd_iskeg, int category_id,
			String dd_deposit, int status) {
		
		this.entity_id = entity_id;
		this.entity_type_id = entity_type_id;
		this.attribute_set_id = attribute_set_id;
		this.type_id = type_id;
		this.sku = sku;
		this.has_options = has_options;
		this.required_options = required_options;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.name = name;
		this.description = description;
		this.short_description = short_description;
		this.price = price;
		this.image = image;
		this.dd_iskeg = dd_iskeg;
		this.category_id = category_id;
		this.dd_deposit = dd_deposit;
		this.status = status;
	}


	public int getEntity_id() {
		return entity_id;
	}
	public void setEntity_id(int entity_id) {
		this.entity_id = entity_id;
	}
	public int getEntity_type_id() {
		return entity_type_id;
	}
	public void setEntity_type_id(int entity_type_id) {
		this.entity_type_id = entity_type_id;
	}
	public int getAttribute_set_id() {
		return attribute_set_id;
	}
	public void setAttribute_set_id(int attribute_set_id) {
		this.attribute_set_id = attribute_set_id;
	}
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public int getHas_options() {
		return has_options;
	}
	public void setHas_options(int has_options) {
		this.has_options = has_options;
	}
	public int getRequired_options() {
		return required_options;
	}
	public void setRequired_options(int required_options) {
		this.required_options = required_options;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getShort_description() {
		return short_description;
	}
	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getDd_iskeg() {
		return dd_iskeg;
	}
	public void setDd_iskeg(int dd_iskeg) {
		this.dd_iskeg = dd_iskeg;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getDd_deposit() {
		return dd_deposit;
	}
	public void setDd_deposit(String dd_deposit) {
		this.dd_deposit = dd_deposit;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}

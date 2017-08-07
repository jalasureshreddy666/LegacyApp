package com.legacyapp.Models;


import java.util.ArrayList;

public class TopModels {

    private String product_id;
    private String product_name;
    private String product_image;
    private String prod_desc;
    private String prod_benefits;

    public String getMsds() {
        return msds;
    }

    public void setMsds(String msds) {
        this.msds = msds;
    }

    private String msds;

    private String industries_name;
    private String industriesid;
    private String parentId;

    private String productTypeId;
    private String industryId;

    private ArrayList<String> product;

    public ArrayList<String> getProduct()
    {
        return product;
    }

    public void setProduct(ArrayList<String> product) {
        this.product = product;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    public String getIndustries_namelist() {
        return industries_namelist;
    }

    public void setIndustries_namelist(String industries_namelist) {
        this.industries_namelist = industries_namelist;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    private String industries_namelist;
    private String productType;


    public String getIndustries_name() {
        return industries_name;
    }

    public void setIndustries_name(String industries_name) {
        this.industries_name = industries_name;
    }

    public String getIndustriesid() {
        return industriesid;
    }

    public void setIndustriesid(String industriesid) {
        this.industriesid = industriesid;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setproduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getproduct_id() {
        return product_id;
    }

    public void setproduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getproduct_name() {
        return product_name;
    }

    public void setproduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getproduct_image() {
        return product_image;
    }

    public void setProd_desc(String prod_desc) {
        this.prod_desc = prod_desc;
    }

    public String getProd_desc() {
        return prod_desc;
    }

    public void setProd_benefits(String prod_benefits) {
        this.prod_benefits = prod_benefits;
    }

    public String getProd_benefits() {
        return prod_benefits;
    }
}

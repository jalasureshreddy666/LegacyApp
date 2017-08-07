package com.legacyapp.Models;

public class RowItem {

    private String imageId;
    private String title;
    private String desc;
    private String productimage;
    private String brochurefile;
    private String msdsfile;

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public String getBrochurefile() {
        return brochurefile;
    }

    public void setBrochurefile(String brochurefile) {
        this.brochurefile = brochurefile;
    }

    public String getMsdsfile() {
        return msdsfile;
    }

    public void setMsdsfile(String msdsfile) {
        this.msdsfile = msdsfile;
    }

    public RowItem(String  imageId, String title, String desc , String productimage, String brochurfile, String msdsfile)
    {
        this.imageId = imageId;
        this.title = title;
        this.desc = desc;
        this.productimage=productimage;
        this.brochurefile=brochurfile;
        this.msdsfile=msdsfile;

    }
    public String getImageId() {
        return imageId;
    }
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return title + "\n" + desc;
    }
}

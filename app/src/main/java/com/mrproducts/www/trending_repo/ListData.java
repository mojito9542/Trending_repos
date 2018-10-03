package com.mrproducts.www.trending_repo;

/*
 * ListData class will hold data for displaying in ListView
 * */
public class ListData {

    String name,link,desc;

    public String getLink() {
        return link;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}



package com.item.leyou.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Zhang Ao on 2020/1/19 20:23
 * Email:17863572518@163.com
 */
@Table(name = "tb_brand")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;// 品牌名称
    private String image;// 品牌图片

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    private Character letter;
    // getter setter 略
}

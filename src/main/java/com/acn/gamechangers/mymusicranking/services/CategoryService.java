package com.acn.gamechangers.mymusicranking.services;

import com.acn.gamechangers.mymusicranking.model.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getCategoryList();

    void addCategory(Category category);
}

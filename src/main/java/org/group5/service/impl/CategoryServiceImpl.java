package org.group5.service.impl;

import org.group5.dao.CategoryDao;
import org.group5.model.Category;
import org.group5.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bijay on 7/10/2016.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Set<Category> getAll() {
        Set<Category> result = new HashSet<>();
        categoryDao.findAll().forEach(item -> result.add(item));
        return result;
    }
}
package com.green.jpaexam.category;

import com.green.jpaexam.entity.CategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CateDaoImpl implements CateDao{

    private final CategoryRepository cateRepository;


    @Override
    public int saveCategory(CategoryEntity entity) {
        return 0;
    }
}

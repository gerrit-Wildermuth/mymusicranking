package com.acn.gamechangers.mymusicranking.repository;


import com.acn.gamechangers.mymusicranking.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}

package com.example.allforyourhome.preview;

import com.example.allforyourhome.preview.entity.Preview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreviewRepository extends JpaRepository<Preview, Integer> {

}

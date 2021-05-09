package com.test.mykola.repository;

import com.test.mykola.dao.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {

    @Query("SELECT id FROM Goods WHERE status.id = :statusId")
    List<Long> findByStatusId(@Param("statusId") Long statusId);
}

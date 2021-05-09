package com.test.mykola.repository;

import com.test.mykola.dao.GoodsStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoodsStatusRepository extends JpaRepository<GoodsStatus, Long> {

    Optional<GoodsStatus> findByName(String name);
}

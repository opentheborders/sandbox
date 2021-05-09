package com.test.mykola.service;

import com.test.mykola.dao.GoodsStatus;
import org.springframework.stereotype.Service;

@Service
public interface GoodsStatusService {

    GoodsStatus getGoodsStatusById(Long statusId);

    GoodsStatus getGoodsStatusByName(String name);
}

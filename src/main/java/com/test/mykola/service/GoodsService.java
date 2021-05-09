package com.test.mykola.service;

import com.test.mykola.dao.Goods;
import com.test.mykola.model.UpdatedStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GoodsService {

    ResponseEntity<Goods> createGoods(Goods newGoods);

    Goods getGoodsInformation(Long goodsId);

    UpdatedStatus updateGoodsStatus(Long goodsId, Long statusId);

    List<Long> getGoodsListByStatus(Long statusId);
}

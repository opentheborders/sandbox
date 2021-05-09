package com.test.mykola.service.impl;

import com.test.mykola.dao.Goods;
import com.test.mykola.dao.GoodsStatus;
import com.test.mykola.exception.GoodsNotFoundException;
import com.test.mykola.model.UpdatedStatus;
import com.test.mykola.repository.GoodsRepository;
import com.test.mykola.service.GoodsService;
import com.test.mykola.service.GoodsStatusService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.test.mykola.constant.Constant.GS_OUT_OF_STOCK;

@Service
@AllArgsConstructor
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private final GoodsRepository goodsRepository;

    @Autowired
    private final GoodsStatusService goodsStatusService;

    /**
     * Add new goods.
     *
     * @param newGoods the new goods.
     * @return the response entity with unique goods id.
     */
    @Override
    public ResponseEntity<Goods> createGoods(Goods newGoods) {
        newGoods.setStatus(goodsStatusService.getGoodsStatusByName(GS_OUT_OF_STOCK));
        Goods goods = goodsRepository.save(newGoods);

        return ResponseEntity.ok(Goods.builder().id(goods.getId()).build());
    }

    /**
     * Return goods information.
     *
     * @param goodsId the goods id.
     * @return the response entity with goods information.
     * @throws GoodsNotFoundException if goods does not exist
     */
    @Override
    public Goods getGoodsInformation(Long goodsId) {
        return goodsRepository.findById(goodsId).orElseThrow(() -> new GoodsNotFoundException(goodsId));
    }

    /**
     * Update goods status.
     *
     * @param goodsId  the goods id.
     * @param statusId the status id.
     * @return the response entity with goods id, old and new goods status.
     * @throws GoodsNotFoundException if goods does not exist
     */
    @Override
    public UpdatedStatus updateGoodsStatus(Long goodsId, Long statusId) {
        Goods goods = goodsRepository.findById(goodsId).orElseThrow(() -> new GoodsNotFoundException(goodsId));

        GoodsStatus oldStatus = goods.getStatus();
        GoodsStatus newStatus = goodsStatusService.getGoodsStatusById(statusId);

        goods.setStatus(newStatus);
        goodsRepository.save(goods);

        return UpdatedStatus.builder().oldStatus(oldStatus).newStatus(newStatus).goodsId(goodsId).build();
    }

    /**
     * Get goods ids by status.
     *
     * @param statusId the goods status id.
     * @return the response entity with list goods ids.
     */
    @Override
    public List<Long> getGoodsListByStatus(Long statusId) {
        return goodsRepository.findByStatusId(statusId);
    }
}

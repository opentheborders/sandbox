package com.test.mykola.controller;

import com.test.mykola.dao.Goods;
import com.test.mykola.exception.GoodsNotFoundException;
import com.test.mykola.model.UpdatedStatus;
import com.test.mykola.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/goods", produces = "application/json")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    //2: add new goods
    /**
     * Add new goods.
     *
     * @param goods the new goods.
     * @return the response entity with unique goods id.
     */
    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Goods> createGoods(@Valid @RequestBody Goods goods) {
        return goodsService.createGoods(goods);
    }

    //3: return goods information by id
    /**
     * Return goods information.
     *
     * @param goodsId the goods id.
     * @return the response entity with goods information.
     * @throws GoodsNotFoundException if goods does not exist
     */
    @GetMapping(value = "/{goodsId}")
    public Goods getGoodsById(@PathVariable("goodsId") Long goodsId) {
        return goodsService.getGoodsInformation(goodsId);
    }

    //4: update goods status
    /**
     * Update goods status.
     *
     * @param goodsId  the goods id.
     * @param statusId the status id.
     * @return the response entity with goods id, old and new goods status.
     * @throws GoodsNotFoundException if goods does not exist
     */
    @PutMapping(value = "/")
    public UpdatedStatus updateGoodsStatus(@RequestParam("goodsId") Long goodsId, @RequestParam("statusId") Long statusId) {
        return goodsService.updateGoodsStatus(goodsId, statusId);
    }

    //5: return goods by status
    /**
     * Get goods ids by status.
     *
     * @param statusId the goods status id.
     * @return the response entity with list goods ids.
     */
    @GetMapping(value = "/status/{statusId}")
    public List<Long> getGoodsIdByStatus(@PathVariable("statusId") Long statusId) {
        return goodsService.getGoodsListByStatus(statusId);
    }
}

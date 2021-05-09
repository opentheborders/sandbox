package com.test.mykola.service.impl;

import com.test.mykola.exception.GoodsStatusNotFoundException;
import com.test.mykola.dao.GoodsStatus;
import com.test.mykola.repository.GoodsStatusRepository;
import com.test.mykola.service.GoodsStatusService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GoodsStatusServiceImpl implements GoodsStatusService {

    @Autowired
    private final GoodsStatusRepository goodsStatusRepository;

    /**
     * Return goods status by id.
     *
     * @param statusId the id of status.
     * @return the goods status.
     * @throws GoodsStatusNotFoundException if status not found.
     */
    @Override
    public GoodsStatus getGoodsStatusById(Long statusId) {
        return goodsStatusRepository.findById(statusId).orElseThrow(() -> new GoodsStatusNotFoundException(statusId));
    }

    /**
     * Return goods status by id.
     *
     * @param name the status name.
     * @return the goods status.
     * @throws GoodsStatusNotFoundException if status not found.
     */
    @Override
    public GoodsStatus getGoodsStatusByName(String name) {
        return goodsStatusRepository.findByName(name).orElseThrow(() -> new GoodsStatusNotFoundException(name));
    }
}

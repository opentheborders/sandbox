package com.test.mykola.service.impl;

import com.test.mykola.dao.GoodsStatus;
import com.test.mykola.exception.GoodsNotFoundException;
import com.test.mykola.exception.GoodsStatusNotFoundException;
import com.test.mykola.repository.GoodsStatusRepository;
import com.test.mykola.service.GoodsStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.test.mykola.constant.Constant.GS_OUT_OF_STOCK;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GoodsStatusServiceImplUTest {

    @Mock
    GoodsStatusRepository goodsStatusRepository;

    GoodsStatusService goodsStatusService;

    @BeforeEach
    void setUp() {
        goodsStatusService = new GoodsStatusServiceImpl(goodsStatusRepository);
    }

    @Test
    void ok_getGoodsStatusById() {
        Mockito.when(goodsStatusRepository.findById(1L)).thenReturn(Optional.of(validGoodsStatus()));

        GoodsStatus actual = goodsStatusService.getGoodsStatusById(1L);
        GoodsStatus expected = validGoodsStatus();

        assertEquals(expected, actual);
    }

    @Test
    void goodsStatusNotFoundException_getGoodsStatusById() {
        Mockito.when(goodsStatusRepository.findById(1L)).thenThrow(new GoodsStatusNotFoundException(1L));

        assertThrows(GoodsStatusNotFoundException.class, () -> {
            goodsStatusService.getGoodsStatusById(1L);
        }, "Goods status with id=1 not found!");
    }

    @Test
    void ok_getGoodsStatusByName() {
        Mockito.when(goodsStatusRepository.findByName(GS_OUT_OF_STOCK)).thenReturn(Optional.of(validGoodsStatus()));

        GoodsStatus actual = goodsStatusService.getGoodsStatusByName(GS_OUT_OF_STOCK);
        GoodsStatus expected = validGoodsStatus();

        assertEquals(expected, actual);
    }

    @Test
    void goodsStatusNotFoundException_getGoodsStatusByName() {
        Mockito.when(goodsStatusRepository.findByName(GS_OUT_OF_STOCK)).thenThrow(new GoodsStatusNotFoundException(GS_OUT_OF_STOCK));

        assertThrows(GoodsStatusNotFoundException.class, () -> {
            goodsStatusService.getGoodsStatusByName(GS_OUT_OF_STOCK);
        }, "Goods status with name=Out of Stock not found!");
    }

    private GoodsStatus validGoodsStatus() {
        return GoodsStatus.builder().id(1L).name(GS_OUT_OF_STOCK).build();
    }
}
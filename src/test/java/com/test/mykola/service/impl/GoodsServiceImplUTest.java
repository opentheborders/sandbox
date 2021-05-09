package com.test.mykola.service.impl;

import com.test.mykola.dao.Goods;
import com.test.mykola.dao.GoodsStatus;
import com.test.mykola.exception.GoodsNotFoundException;
import com.test.mykola.model.GoodsType;
import com.test.mykola.model.UpdatedStatus;
import com.test.mykola.repository.GoodsRepository;
import com.test.mykola.service.GoodsService;
import com.test.mykola.service.GoodsStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.test.mykola.constant.Constant.GS_OUT_OF_STOCK;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class GoodsServiceImplUTest {

    @Mock
    GoodsStatusService goodsStatusService;

    @Mock
    GoodsRepository goodsRepository;

    GoodsService goodsService;

    @BeforeEach
    void setUp() {
        goodsService = new GoodsServiceImpl(goodsRepository, goodsStatusService);
    }

    @Test
    void ok_createNewGoods() {
        Goods goods = Goods.builder().id(1L).image("image").name("name").type(GoodsType.TYPE1).build();
        Mockito.when(goodsRepository.save(any())).thenReturn(goods);
        Mockito.when(goodsStatusService.getGoodsStatusByName(GS_OUT_OF_STOCK)).thenReturn(validGoodsStatus());

        ResponseEntity<Goods> actual = goodsService.createGoods(validGoods());
        ResponseEntity<Goods> expected = ResponseEntity.ok(Goods.builder().id(1L).build());

        assertEquals(expected, actual);
    }

    @Test
    void ok_getGoodsInformation() {
        Mockito.when(goodsRepository.findById(1L)).thenReturn(Optional.of(validGoods()));

        Goods actual = goodsService.getGoodsInformation(1L);
        Goods expected = validGoods();

        assertEquals(expected, actual);
    }

    @Test
    void goodsNotFoundException_GetGoodsInformation() {
        Mockito.when(goodsRepository.findById(1L)).thenThrow(new GoodsNotFoundException(1L));

        assertThrows(GoodsNotFoundException.class, () -> {
            goodsService.getGoodsInformation(1L);
        }, "Goods with id=1 not found!");
    }

    @Test
    void ok_updateGoodsStatus() {
        Mockito.when(goodsRepository.findById(1L)).thenReturn(Optional.of(validGoods()));
        Mockito.when(goodsStatusService.getGoodsStatusById(1L)).thenReturn(validGoodsStatus());

        UpdatedStatus actual = goodsService.updateGoodsStatus(1L, 1L);
        UpdatedStatus expected = UpdatedStatus.builder()
                .oldStatus(validGoodsStatus()).newStatus(validGoodsStatus()).goodsId(1L)
                .build();

        assertEquals(expected, actual);
    }

    @Test
    void goodsNotFoundException_updateGoodsStatus() {
        Mockito.when(goodsRepository.findById(1L)).thenThrow(new GoodsNotFoundException(1L));

        assertThrows(GoodsNotFoundException.class, () -> {
            goodsService.updateGoodsStatus(1L, 1L);
        }, "Goods with id=1 not found!");
    }

    @Test
    void  ok_getGoodsListByStatus() {
        Mockito.when(goodsRepository.findByStatusId(1L)).thenReturn(List.of(1L, 2L, 3L));

        List<Long> actual = goodsService.getGoodsListByStatus(1L);
        List<Long> expected = List.of(1L, 2L, 3L);

        assertEquals(expected, actual);
    }

    private Goods validGoods() {
        return Goods.builder()
                .id(1L).image("image").name("name").type(GoodsType.TYPE1).status(validGoodsStatus())
                .warrantyPeriod(1).createdDate(new Date(1220227200))
                .build();
    }

    private GoodsStatus validGoodsStatus() {
        return GoodsStatus.builder().id(1L).name(GS_OUT_OF_STOCK).build();
    }
}
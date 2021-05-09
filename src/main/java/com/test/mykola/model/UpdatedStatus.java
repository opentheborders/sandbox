package com.test.mykola.model;

import com.test.mykola.dao.GoodsStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatedStatus {

    private Long goodsId;
    private GoodsStatus oldStatus;
    private GoodsStatus newStatus;
}

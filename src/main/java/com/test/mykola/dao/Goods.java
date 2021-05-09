package com.test.mykola.dao;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.mykola.model.GoodsType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String image;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GoodsType type;

    @NotNull
    private Integer warrantyPeriod;

    @ManyToOne
    @JoinColumn(name = "status")
    private GoodsStatus status;

    @Column(name="created_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdDate;
}


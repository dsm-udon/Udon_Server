package com.example.udon_server.domain.action.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Builder
@Entity(name = "actions")
@AllArgsConstructor
@NoArgsConstructor
public class Action {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "BINARY(16)", nullable = false)
    private UUID id;

    @Column(name = "safety_cate_nm1", columnDefinition = "VARCHAR(50)")
    private String safetyCateNm1;

    @Column(name = "safety_cate_nm2", columnDefinition = "VARCHAR(50)")
    private String safetyCateNm2;

    @Column(name = "safety_cate_nm3", columnDefinition = "VARCHAR(50)")
    private String safetyCateNm3;

    @Column(name = "image_url", columnDefinition = "VARCHAR(200)")
    private String url;

    @Column(name = "content", columnDefinition = "VARCHAR(1000)", nullable = false)
    private String content;
}

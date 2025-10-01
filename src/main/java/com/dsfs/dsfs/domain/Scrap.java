package com.dsfs.dsfs.domain;

import com.dsfs.dsfs.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "scraps")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Scrap extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scrapId;
}

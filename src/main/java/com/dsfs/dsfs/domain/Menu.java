package com.dsfs.dsfs.domain;

import com.dsfs.dsfs.domain.common.BaseEntity;
import com.dsfs.dsfs.domain.enums.Icon;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "menus")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;
    private String name;
    private Double price;
    private String description;
    private String info_url;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "icons")
    private List<Icon> icons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}

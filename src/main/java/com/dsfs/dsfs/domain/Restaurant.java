package com.dsfs.dsfs.domain;

import com.dsfs.dsfs.domain.common.BaseEntity;
import com.dsfs.dsfs.domain.enums.CampusType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;
    private String name;
    private Long addressId; //카카오맵 고유 장소 id
    private String address; // 도로명 주소
    private Double longitude; // 경도, x
    private Double latitude; // 위도, y

    @Enumerated(EnumType.STRING)
    private CampusType campusType; // 교내, 교외

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarks;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Menu> menus;
}

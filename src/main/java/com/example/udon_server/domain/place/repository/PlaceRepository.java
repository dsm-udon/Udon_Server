package com.example.udon_server.domain.place.repository;

import com.example.udon_server.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query(value = "SELECT *, (6371 * acos(cos(radians(:x)) * cos(radians(u.latitude)) * cos(radians(u.longitude) - radians(:y)) + sin(radians(:x)) * sin(radians(u.latitude)))) AS distance FROM tbl_user AS u HAVING distance <= 5 ORDER BY distance;", nativeQuery = true)
    List<Place> findAllUser(@Param(value = "x") BigDecimal x, @Param(value = "y") BigDecimal y);

    // 이 쿼리문 실행할 때 SET sql_mode = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION'; 설정해주기
    // @@sql_mode에서 ONLY_FULL_GROUP_BY 없애주기
    @Query(value = "SELECT *, (6371 * acos(cos(radians(:x)) * cos(radians(u.latitude)) * cos(radians(u.longitude) - radians(:y)) + sin(radians(:x)) * sin(radians(u.latitude)))) AS distance FROM tbl_user AS u JOIN tbl_category_status tcs ON u.id = tcs.user_id JOIN tbl_category tc ON tc.id = tcs.category_id WHERE tc.id = :category1 OR tc.id = :category2 GROUP BY u.id HAVING distance <= 5 ORDER BY distance;", nativeQuery = true)
    List<Place> findAllUserByCategory(@Param(value = "x") BigDecimal x, @Param(value = "y") BigDecimal y, @Param(value = "category1") Long category1, @Param(value = "category2") Long category2);

}

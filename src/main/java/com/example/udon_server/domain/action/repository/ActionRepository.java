package com.example.udon_server.domain.action.repository;

import com.example.udon_server.domain.action.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActionRepository extends JpaRepository<Action, UUID> {

    List<Action> findAllBySafetyCateNm2AndSafetyCateNm3(String safetyCateNm2, String safetyCateNm3);
}

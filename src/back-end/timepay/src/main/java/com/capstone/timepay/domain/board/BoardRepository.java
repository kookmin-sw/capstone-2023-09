package com.capstone.timepay.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long>, JpaSpecificationExecutor<Board> {
    List<Board> findByUuid(Long uuid);

    List<Board> getBoardsByUuidAndCategory(Long uuid, String category);
}

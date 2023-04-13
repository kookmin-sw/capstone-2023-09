package com.capstone.timepay.domain.board;

import com.capstone.timepay.domain.dealBoard.DealBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long>, JpaSpecificationExecutor<Board> {
    List<Board> findByUuid(Long uuid);

    List<Board> getBoardsByUuidAndCategory(Long uuid, String category);

    Page<Board> findByIsHiddenFalse(Pageable pageable);
}

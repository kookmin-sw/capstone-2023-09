package com.capstone.timepay.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findByUuid(Long uuid);

    List<Board> getBoardsByUuidAndCategory(Long uuid, String category);
}

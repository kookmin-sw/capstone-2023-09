package com.capstone.timepay.domain.freeBoard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FreeBoardRepository extends JpaRepository<FreeBoard,Long> {
    Page<FreeBoard> findByIsHiddenFalse(Pageable pageable);
    FreeBoard findByType(String type);
    @Modifying
    @Query(value = "update FreeBoard fb set fb.freeBoardHits=fb.freeBoardHits+1 where fb.id=:id")
    void updateHits(@Param("id") Long id);
}

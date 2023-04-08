package com.capstone.timepay.domain.comment;

import com.capstone.timepay.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByUuid(Long uuid);
}

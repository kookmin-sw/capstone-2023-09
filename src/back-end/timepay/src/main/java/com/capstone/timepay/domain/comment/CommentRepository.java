package com.capstone.timepay.domain.comment;

import com.capstone.timepay.domain.dealBoardComment.DealBoardComment;
import com.capstone.timepay.domain.freeBoardComment.FreeBoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    Optional<Comment> findByCommentId(Long commentId);

    Comment findByFreeBoardComment(FreeBoardComment freeBoardComment);

    Comment findByDealBoardComment(DealBoardComment dealBoardComment);
}

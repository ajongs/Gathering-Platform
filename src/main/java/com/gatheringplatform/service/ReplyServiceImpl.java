package com.gatheringplatform.service;

import com.gatheringplatform.domain.Reply;
import com.gatheringplatform.enums.ErrorEnum;
import com.gatheringplatform.exception.RequestException;
import com.gatheringplatform.format.DefaultResponse;
import com.gatheringplatform.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service // 핵심 비즈니스 로직
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private BoardService boardService;

    @Value("${categories}")
    String[] categories;

    @Override // 개별 댓글 반환
    public Reply view(long reply_id) { // 해당 게시물의 특정 댓글 반환
        Reply reply = replyMapper.getReply(reply_id);

        //삭제된 페이지이거나 id값이 존재하지 않는 경우
        if (reply == null) {
            throw new RequestException(ErrorEnum.DELETED_PAGE);
        }

        return reply;
    }

    @Override
    public List<Reply> viewAll(long post_id, long page_num) { // 해당 게시물의 전체 댓글 목록 반환

        // 해당 페이지가 존재하지 않을때
        if (page_num == 0) {
            throw new RequestException(ErrorEnum.NON_EXISTED_PAGE);
        }

        long startIndex = (page_num - 1) * 5;

        // DB에 담겨진 인덱스를 초과한 페이지를 요청했을 때
        if (replyMapper.countReplyByPost(post_id) < startIndex) {
            throw new RequestException(ErrorEnum.NON_EXISTED_PAGE);
        }

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("post_id", post_id);
        parameter.put("startIndex", startIndex);
        return replyMapper.getReplies(parameter);
    }

    @Override
    public DefaultResponse post(Reply reply) {
        reply.setAuthor(userService.getLoginNickname());
        if (boardService.getBoard(reply.getPost_id()) == null) {
            throw new RequestException(ErrorEnum.NON_EXISTED_PAGE);
        }
        replyMapper.insert(reply);

        return new DefaultResponse("댓글 등록 완료!", HttpStatus.OK);
    }

    @Override
    public DefaultResponse update(long reply_id, Reply reply) {
        // 권한 검사
        verifyAuthorization(reply_id);
        reply.setId(reply_id);
        if (boardService.getBoard(reply.getPost_id()) == null) {
            throw new RequestException(ErrorEnum.NON_EXISTED_PAGE);
        }
        replyMapper.update(reply);

        return new DefaultResponse("댓글 수정 완료!", HttpStatus.OK);
    }

    @Override
    public DefaultResponse delete(long reply_id) {
        // 권한 검사
        verifyAuthorization(reply_id);
        replyMapper.delete(reply_id);

        return new DefaultResponse("댓글 삭제 완료!", HttpStatus.OK);
    }

    private void verifyAuthorization(long reply_id) { // 추후 수정
        String replyAuthor = replyMapper.getAuthor(reply_id);
        String loginNickname = userService.getLoginNickname();

        if (!replyAuthor.equals(loginNickname)) {
            throw new RequestException(ErrorEnum.UNAUTHORIZED);
        }
    }
}
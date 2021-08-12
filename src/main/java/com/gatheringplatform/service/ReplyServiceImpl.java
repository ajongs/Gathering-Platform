package com.gatheringplatform.service;

import com.gatheringplatform.domain.Reply;
import com.gatheringplatform.mapper.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service // 핵심 비즈니스 로직
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public Reply view(Long postNo, Long replyNo) { // 해당 게시물의 특정 댓글 반환
        return replyMapper.getReplyByNumber(postNo, replyNo);
    }

    @Override
    public List<Reply> viewAll(Long postNo) { // 해당 게시물의 전체 댓글 목록 반환
        /* 테스트 용도 */
        List<Reply> list = replyMapper.getRepliesByNumber(postNo);
        // replyMapper.getRepliesByNumber(postNo);를 하면 long 자료형 값은 0이 나오고 timestamp 자료형 값은 null이 나옴
        System.out.println("게시물의 전체 댓글 목록:" + list.get(0).getPost_no() + "/" + list.get(0).getReply_no()
                           + "/" + list.get(0).getParent_reply_no() + "/" + list.get(0).getContent());
        System.out.println(list.get(1).getPost_no() + "/" + list.get(1).getReply_no()
                           + "/" + list.get(1).getParent_reply_no() + "/" + list.get(1).getContent());
        System.out.println(list.get(2).getPost_no() + "/" + list.get(2).getReply_no()
                           + "/" + list.get(2).getParent_reply_no() + "/" + list.get(2).getContent());
        /**/

        return replyMapper.getRepliesByNumber(postNo);
    }

    @Override
    public void post(Reply reply) {
        List<Reply> replyList = viewAll(reply.getPost_no());
        if (replyList.isEmpty()) {
            reply.setReply_no(1);
        }
        else {
            reply.setReply_no(replyList.size() + 1);
        }
        replyMapper.insert(reply);
    }

    @Override
    public void update(Reply reply) {
        replyMapper.update(reply);
    }

    @Override
    public void delete(Reply reply) {  // 부모 댓글이 삭제되면 자식 댓글까지 모두 삭제
        replyMapper.delete(reply);
    }
}
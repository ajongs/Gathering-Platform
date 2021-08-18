package com.gatheringplatform.mapper;

import com.gatheringplatform.domain.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BoardMapper {
    long insertBoard(Board board);
    String insertImages(Map imageList);
    List<Board> getBoardList(Map parameter);
    long countBoardByCategory(String category);
    Board getBoard(long board_id);
    String getAuthor(long board_id);
    void modifyBoard(Board board);
    void deleteBoard(long board_id);
}

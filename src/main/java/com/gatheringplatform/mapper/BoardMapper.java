package com.gatheringplatform.mapper;

import com.gatheringplatform.domain.Board;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface BoardMapper {
    long insertBoard(Board board);
    String insertImages(Map imageList);
}

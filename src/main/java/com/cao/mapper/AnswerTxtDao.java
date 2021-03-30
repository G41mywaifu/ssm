package com.cao.mapper;

import com.cao.entity.AnswerTxt;

import java.util.List;
import java.util.Map;

public interface AnswerTxtDao {
	public int create(AnswerTxt pi);
	public int delete(Map<String, Object> paramMap);
	public int update(Map<String, Object> paramMap);
	public List<AnswerTxt> query(Map<String, Object> paramMap);
	public AnswerTxt detail(Map<String, Object> paramMap);
	public int count(Map<String, Object> paramMap);
}
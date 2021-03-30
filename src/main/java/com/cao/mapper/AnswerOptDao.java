package com.cao.mapper;

import com.cao.entity.AnswerOpt;

import java.util.List;
import java.util.Map;

public interface AnswerOptDao {
	public int create(AnswerOpt pi);
	public int delete(Map<String, Object> paramMap);
	public int update(Map<String, Object> paramMap);
	public List<AnswerOpt> query(Map<String, Object> paramMap);
	public AnswerOpt detail(Map<String, Object> paramMap);
	public int count(Map<String, Object> paramMap);
}
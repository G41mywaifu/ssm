package com.cao.mapper;

import com.cao.entity.Admin;

import java.util.List;
import java.util.Map;

public interface AdminDao {

    public int create(Admin admin);

    public int delete(Map<String, Object> paramMap);

    public int update(Map<String, Object> paramMap);

    public List<Admin> query(Map<String, Object> paramMap);

    public Admin detail(Map<String, Object> paramMap);

    public int count(Map<String, Object> paramMap);

}
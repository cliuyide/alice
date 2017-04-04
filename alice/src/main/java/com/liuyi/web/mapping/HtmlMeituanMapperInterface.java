package com.liuyi.web.mapping;

import com.liuyi.web.model.HtmlMeituan;

public interface HtmlMeituanMapperInterface {

    int deleteByPrimaryKey(String id);

    int insertSelective(HtmlMeituan record);

    HtmlMeituan selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HtmlMeituan record);

    int updateByPrimaryKey(HtmlMeituan record);
}
package org.edupro.webapi.mapper;

import org.apache.ibatis.annotations.*;
import org.edupro.webapi.model.response.LookupRes;

import java.util.List;

@Mapper
public interface LookupMapper {
    @Select("SELECT * FROM T_LOOKUP WHERE LKID = #{id}")
    @Results({
            @Result(property = "id", column = "LKID"),
            @Result(property = "group", column = "LKGRP"),
            @Result(property = "kode", column = "LKKD"),
            @Result(property = "nama", column = "LKNM"),
            @Result(property = "urutan", column = "LKURT"),
            @Result(property = "status", column = "LKSTAT"),
    })
    LookupRes getById(@Param("id") String id);

    @Select("SELECT * FROM T_LOOKUP WHERE LKGRP = #{group}")
    @Results({
            @Result(property = "id", column = "LKID"),
            @Result(property = "group", column = "LKGRP"),
            @Result(property = "kode", column = "LKKD"),
            @Result(property = "nama", column = "LKNM"),
            @Result(property = "urutan", column = "LKURT"),
            @Result(property = "status", column = "LKSTAT"),
    })
    List<LookupRes> getByGroup(@Param("group") String group);
}

package com.ty.blog.dao;

import com.ty.blog.pojo.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: RelationDao
 * @Description: 关系实体持久层
 * @author zhangtainyi
 * @date 2019/9/17 16:20
 *
 */
@Repository
public interface RelationDao extends JpaRepository<Relation, Long> {

//    /**
//     * 通过关系类型type和关系码值1查询结果集
//     * @param type 关系类型
//     * @param code1 关系码值1
//     * @return
//     */
//    List<Relation>  findRelationsByRelationTypeAndAndCode1(String type, String code1);

}

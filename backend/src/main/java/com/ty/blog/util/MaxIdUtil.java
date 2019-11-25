package com.ty.blog.util;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;

/**
 * @ClassName: MaxIdUtil
 * @Description: 获取表最大id+1
 * @author zhangtainyi
 * @date 2019/9/17 16:21
 *
 */
@Component
public class MaxIdUtil {
    /**
     * 注入的是实体管理器,执行持久化操作
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     * 获取表id最大值+1
     * @param tableName
     * @return id最大值+1
     */
    public Integer getMaxId(String tableName){
        String sql = "select max(id+1) as id from " + tableName;
        Query nativeQuery = entityManager.createNativeQuery(sql);
        BigInteger maxId = (BigInteger)nativeQuery.getResultList().get(0);
        return maxId.intValue();
    }
}

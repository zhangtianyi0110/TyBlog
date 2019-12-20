package com.ty.blog.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: GlobalException
 * @Description: 自定义全局异常
 * @author zhangtainyi
 * @date 2019/9/17 16:20
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GlobalException extends RuntimeException {
    /**
     * 异常码
     */
    private Integer code;
    /**
     * 异常信息
     */
    private String message;

}

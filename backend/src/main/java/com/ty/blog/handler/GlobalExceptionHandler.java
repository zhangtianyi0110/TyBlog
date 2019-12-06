package com.ty.blog.handler;

import com.google.gson.Gson;
import com.ty.blog.entity.ResponseData;
import com.ty.blog.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: GlobalException
 * @Description: 全局异常处理
 * @author zhangtainyi
 * @date 2019/9/17 16:20
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseData validationBodyException(ConstraintViolationException e) {

        return ResponseUtil.failure(400, e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public ResponseData validationBodyException(BindException e) {
        List<ObjectError> allErrors = e.getAllErrors();
        List<String> msgs = new ArrayList<>();
        allErrors.forEach(error -> {
            msgs.add("错误：" + error.getDefaultMessage());
        });
        return ResponseUtil.failure(400, new Gson().toJson(msgs));
    }


        /**
         *  校验错误拦截处理
         *
         * @param exception 错误信息集合
         * @return 错误信息
         */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseData validationBodyException(MethodArgumentNotValidException exception){

        BindingResult result = exception.getBindingResult();
        if (result.hasErrors()) {

            List<ObjectError> errors = result.getAllErrors();
            StringBuffer sb = new StringBuffer();
            errors.forEach(p ->{

                FieldError fieldError = (FieldError) p;
                log.error("Data check failure : object{"+fieldError.getObjectName()+"},field{"+fieldError.getField()+
                        "},errorMessage{"+fieldError.getDefaultMessage()+"}");
                sb.append("错误：").append(fieldError.getDefaultMessage()).append("\r\n");

            });

        }
        return ResponseUtil.failure(400,"请填写正确信息");
    }

    /**
     * 参数类型转换错误
     *
     * @param exception 错误
     * @return 错误信息
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseData parameterTypeException(HttpMessageConversionException exception){

        log.error(exception.getCause().getLocalizedMessage());
        return ResponseUtil.failure(400, "类型转换错误");

    }

}

package com.springboot.shoehome.handle;

import com.springboot.shoehome.domain.Result;
import com.springboot.shoehome.exception.UserException;
import com.springboot.shoehome.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by acer on 2018/1/13.
 */
@ControllerAdvice
public class ExceptionHandle {

    private  final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if(e instanceof UserException){
            UserException userException=(UserException) e;
            return ResultUtil.error(userException.getCode(),userException.getMessage());
        }else{
            logger.error("system error",e);
            return ResultUtil.error(-1,"unknown error");
        }

    }
}

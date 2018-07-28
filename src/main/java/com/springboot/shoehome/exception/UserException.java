package com.springboot.shoehome.exception;


import com.springboot.shoehome.enums.ResultEnum;

/**
 *
 * @author acer
 * @date 2018/1/13
 */
public class UserException extends RuntimeException {

    private Integer code;

    public UserException(ResultEnum resultEnum)throws Exception {
        super(resultEnum.getMsg());
        this.code=resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

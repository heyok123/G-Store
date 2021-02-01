package com.cupdata.common.exception;

import lombok.Getter;

/**
 * @Description: TODO
 * Created by Wsork on 2021/2/1 16:35.
 **/
@Getter
public class MyException extends RuntimeException {

    private String code;
    private String msg;

    public MyException(){super();}

    public MyException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public MyException(String code,String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }


}

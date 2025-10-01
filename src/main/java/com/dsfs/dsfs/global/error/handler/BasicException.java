package com.dsfs.dsfs.global.error.handler;

import com.dsfs.dsfs.global.code.BaseErrorCode;
import com.dsfs.dsfs.global.error.exception.GeneralException;

public class BasicException extends GeneralException {
    public BasicException(BaseErrorCode code) {
        super(code);
    }
}
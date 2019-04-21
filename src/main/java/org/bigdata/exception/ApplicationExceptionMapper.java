package org.bigdata.exception;

import org.bigdata.domain.SimpleMsg;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * 全局异常处理
 */
//@Provider是关键，如果不添加异常处理不会生效，只会在后端提示错误
@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        return Response
                .status(Response.Status.BAD_REQUEST.getStatusCode())
                .type(MediaType.APPLICATION_JSON)
                .entity(new SimpleMsg(e.getMessage()))
                .build();
    }
}

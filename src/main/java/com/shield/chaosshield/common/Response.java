package com.shield.chaosshield.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    Integer code;
    String msg;
    public static final Integer SUCCEED_NUM = 200;
    public static final Integer FAILED_NUM = 0;
    public static final Integer ERROR_NUM = -1;
    public static final Response SUCCEED = new Response(200, "成功");
    public static final Response FAILED = new Response(0, "失败");
    public static final Response ERROR = new Response(-1, "异常");
}

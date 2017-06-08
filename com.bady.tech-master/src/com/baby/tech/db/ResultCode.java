package com.baby.tech.db;

/**
 * 错误码定义类
 * 
 * @author panyoufu
 */
public class ResultCode {

    // Authentication state code

    /** 认证成功 */
    public static final int AUTH_SUCCESS = 0;

    /** 认证用户名密码错误 */
    public static final int AUTH_FAILED_UNKNOWN_ACCOUNT = 1;

    /** 用户已达最大限制 */
    public static final int AUTH_FAILED_USER_LIMIT = 2;

    // Coordinate transition service

    /** 转换成功 */
    public static final int COORD_TRANSIT_SUCCESS = 10;

    /** 转换失败 */
    public static final int COORD_TRANSIT_FAILED = 11;

    // Feature code

    /** 服务器不支持该功能 */
    public static final int FEATURE_UNSUPPORTED = 20;

    // Default code

    /** 缺省的成功状态码 */
    public static final int DEFAULT_SUCCESS = 30;

    /** 缺省的失败状态码 */
    public static final int DEFAULT_FAILED = 31;

    // Http status code
    /** Http请求成功的标志 */
    public static final int HTTP_STATUS_OK = 200;

    /** 请求参数有误，当前请求无法被服务器理解 */
    public static final int HTTP_STATUS_BAD_REQUEST = 400;

    /** 请求失败，请求所希望得到的资源未被在服务器上发现 */
    public static final int HTTP_STATUS_NOT_FOUND = 404;

    /** 服务器遇到了一个未曾预料的状况，导致了它无法完成对请求的处理 */
    public static final int HTTP_STATUS_INTERNAL_SERVER_ERROR = 500;

    /** 读取数据超时 */
    public static final int HTTP_STATUS_SOCKET_TIMEOUT = 900;

    /** 连接超时 */
    public static final int HTTP_STATUS_CONN_TIMEOUT = 901;

    /** 网络中断 */
    public static final int HTTP_STATUS_INTERRUPTED_IO_EXCEPTION = 902;

    /** 网络数据流传输出错 */
    public static final int HTTP_STATUS_IO_EXCEPTION = 903;

    /** 服务器忙而未响应或是服务器相应的监听端口未打开 */
    public static final int HTTP_STATUS_CONN_EXCEPTION = 904;

    /** 缺省错误 */
    public static final int HTTP_STATUS_DEFAULT_EXCEPTION = 905;

    // Net state
    /** 网络服务不可用 */
    public static final int NETWORK_SERVICE_UNAVAILABLE = 906;

    /** 网络服务已恢复 */
    public static final int NETWORK_SERVICE_AVAILABLE = 907;
}

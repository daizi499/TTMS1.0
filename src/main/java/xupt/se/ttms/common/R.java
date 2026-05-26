package xupt.se.ttms.common;

public class R {
    private Integer code;
    private String msg;
    private Object data;

    public R() {}

    public R(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static R ok() {
        return new R(200, "success", null);
    }

    public static R ok(Object data) {
        return new R(200, "success", data);
    }

    public static R ok(String msg, Object data) {
        return new R(200, msg, data);
    }

    public static R error(String msg) {
        return new R(500, msg, null);
    }

    public Integer getCode() { return code; }
    public void setCode(Integer code) { this.code = code; }
    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }
    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }
}

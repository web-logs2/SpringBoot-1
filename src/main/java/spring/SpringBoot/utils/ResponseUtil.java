package spring.SpringBoot.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务端返回结果封装工具类
 */
public class ResponseUtil {
    /**
     * 正常返回（只含code和msg）
     *
     * @return Map类型的对象
     * <p>
     * 例如：
     * {
     * "code": 200,
     * "msg": "成功"
     * }
     */
    public static Object ok() {
        Map<String, Object> obj = new HashMap<>();
        obj.put("code", 200);
        obj.put("msg", "success");
        return obj;
    }


    /**
     * 正常返回（含code，msg，data）
     *
     * @param data 需要返回的数据对象
     * @return Map类型的对象
     * <p>
     * 例如：
     * {
     * "code": 200,
     * "msg": "成功",
     * "data":{
     * "result":10
     * }
     * }
     */
    public static Object ok(Object data) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("code", 200);
        obj.put("msg", "success");
        obj.put("data", data);
        return obj;
    }

    /**
     * 正常返回（含code，msg，data）
     *
     * @param data 需要返回的数据对象
     * @return Map类型的对象
     * <p>
     * 例如：
     * {
     * "code": 200,
     * "msg": "成功",
     * "data":{
     * "result":10
     * }
     * }
     */
    public static Object okFstComponentDependency(int totalSize, Object data) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("code", 200);
        obj.put("msg", "success");
        obj.put("totalSize", totalSize);
        obj.put("data", data);
        return obj;
    }

    /**
     * 部分执行成功的返回
     *
     * @param data 需要返回的数据对象
     * @return Map类型的对象
     * <p>
     * 例如：
     * {
     * "code": 201,
     * "msg": "部分执行成功",
     * "data":{
     * "result":10
     * }
     * }
     */
    public static Object part(Object data) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("code", 201);
        obj.put("msg", "part success");
        obj.put("data", data);
        return obj;
    }

    /**
     * 返回正常的分页数据
     *
     * @param data  返回的数据对象
     * @param cn    第几页
     * @param sn    每页数据条数
     * @param total 返回数据总条数
     * @return Map类型的对象
     * <p>
     * 例如：
     * {
     * "code": 200,
     * "msg": "成功"
     * <p>
     * }
     */
    public static Object okList(Object data, int cn, int sn, int total) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("code", 200);
        obj.put("msg", "success");
        obj.put("data", data);
        obj.put("cn", cn);
        obj.put("sn", sn);
        obj.put("total", total);
        obj.put("pn", (int) Math.ceil((double) total / sn));
        return obj;
    }

    /**
     * 返回正常的分页数据
     *
     * @param data  返回的数据对象
     * @param cn    第几页
     * @param sn    每页数据条数
     * @param total 返回数据总条数
     * @return Map类型的对象
     * <p>
     * 例如：
     * {
     * "code": 200,
     * "msg": "成功"
     * <p>
     * }
     */
    public static Object okList(Object data, int cn, int sn, long total) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("code", 200);
        obj.put("msg", "success");
        obj.put("data", data);
        obj.put("cn", cn);
        obj.put("sn", sn);
        obj.put("total", total);
        obj.put("pn", (int) Math.ceil((double) total / sn));
        return obj;
    }


    /**
     * 失败/错误的返回
     *
     * @return Map类型的对象
     * <p>
     * 例如：
     * {
     * "code": -1,
     * "msg": "错误"
     * }
     */
    public static Object fail() {
        Map<String, Object> obj = new HashMap<>();
        obj.put("code", -1);
        obj.put("msg", "error");
        return obj;
    }

    /**
     * 自定义失败/错误的返回
     *
     * @param code code码
     * @param msg  错误信息
     * @return Map类型的对象
     * <p>
     * 例如：
     * {
     * "code": 301,
     * "msg"："记录不存在"
     * }
     */
    public static Object fail(int code, String msg) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("code", code);
        obj.put("msg", msg);
        return obj;
    }
}


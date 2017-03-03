package com.common.utils;



import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;

/**
 * JSON解析帮助类
 *
 * @author kevin
 * @version v1.0
 * @since 2014-11/7/14
 */
public class JsonUtils {

    public static boolean isJsonObject(String json) {
        if (!StringUtils.isEmpty(json)) {
            JsonParser parser = new JsonParser();
            StringReader reader = new StringReader(json);
            JsonReader jsonReader = new JsonReader(reader);
            jsonReader.setLenient(true);
            JsonElement element = parser.parse(jsonReader);
            return element.isJsonObject();
        }
        return false;
    }

    public static boolean isJsonArray(String json) {
        if (!StringUtils.isEmpty(json)) {
            JsonParser parser = new JsonParser();
            StringReader reader = new StringReader(json);
            JsonReader jsonReader = new JsonReader(reader);
            jsonReader.setLenient(true);
            JsonElement element = parser.parse(jsonReader);
            return element.isJsonArray();
        }
        return false;
    }

    public static boolean isJsonString(String json) {
        if (!StringUtils.isEmpty(json)) {
            JsonParser parser = new JsonParser();
            StringReader reader = new StringReader(json);
            JsonReader jsonReader = new JsonReader(reader);
            jsonReader.setLenient(true);
            JsonElement element = parser.parse(jsonReader);
            return element.isJsonPrimitive();
        }
        return false;
    }

    public static boolean isJsonNull(String json) {
        if (!StringUtils.isEmpty(json)) {
            JsonParser parser = new JsonParser();
            StringReader reader = new StringReader(json);
            JsonReader jsonReader = new JsonReader(reader);
            jsonReader.setLenient(true);
            JsonElement element = parser.parse(jsonReader);
            return element.isJsonNull();
        }
        return true;
    }

    public static JsonElement getJsonElement(String json) throws JSONException {
        if (!StringUtils.isEmpty(json)) {
            JsonParser parser = new JsonParser();
            StringReader reader = new StringReader(json);
            JsonReader jsonReader = new JsonReader(reader);
            jsonReader.setLenient(true);
            return parser.parse(jsonReader);
        }
        return null;
    }

    public static JsonObject getJsonObject(String json) throws JSONException {
        if (!StringUtils.isEmpty(json)) {
            JsonParser parser = new JsonParser();
            StringReader reader = new StringReader(json);
            JsonReader jsonReader = new JsonReader(reader);
            jsonReader.setLenient(true);
            JsonElement element = parser.parse(jsonReader);
            if (element.isJsonObject()) {
                return element.getAsJsonObject();
            }
        }
        return null;
    }

    public static JSONObject getJSONObject(String json) throws JSONException {
        if (!StringUtils.isEmpty(json)) {
            JsonParser parser = new JsonParser();
            StringReader reader = new StringReader(json);
            JsonReader jsonReader = new JsonReader(reader);
            jsonReader.setLenient(true);
            JsonElement element = parser.parse(jsonReader);
            if (element.isJsonObject()) {
                return new JSONObject(json);
            }
        }
        return null;
    }

    public static JsonArray getJsonArray(String json) throws JSONException {
        if (!StringUtils.isEmpty(json)) {
            JsonParser parser = new JsonParser();
            StringReader reader = new StringReader(json);
            JsonReader jsonReader = new JsonReader(reader);
            jsonReader.setLenient(true);
            JsonElement element = parser.parse(jsonReader);
            if (element.isJsonArray()) {
                return element.getAsJsonArray();
            }
        }
        return null;
    }

    public static JSONArray getJSONArray(String json) throws JSONException {
        if (!StringUtils.isEmpty(json)) {
            JsonParser parser = new JsonParser();
            StringReader reader = new StringReader(json);
            JsonReader jsonReader = new JsonReader(reader);
            jsonReader.setLenient(true);
            JsonElement element = parser.parse(jsonReader);
            if (element.isJsonArray()) {
                return new JSONArray(json);
            }
        }
        return null;
    }

    /**
     * ！！！对属性数量较多的时候，进行解析有性能问题
     *
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T getModel(String json, Class<T> tClass) {
        Gson gson = new Gson();
        return gson.fromJson(json, tClass);
    }

    public static String toJson(Object model) {
        Gson gson = new Gson();
        return gson.toJson(model);
    }

    public static String getModelItemAsString(JsonObject jsonObject, String name) {
        if (jsonObject != null && jsonObject.has(name)) {
            JsonElement item = jsonObject.get(name);
            if (!item.isJsonNull()) {
                return item.getAsString();
            }
        }
        return "";
    }

//    public static <T extends ApiModel> T getModelItemAsObject(JsonObject jsonObject, String name, T item)
//            throws JSONException {
//        if (jsonObject != null && jsonObject.has(name)) {
//            item.parseJson(jsonObject.get(name).toString());
//            return item;
//        }
//        return null;
//    }
//
//    public static <T extends ApiModel> ApiModelList<T> getModelItemAsList(JsonObject jsonObject, String name, T item) throws JSONException {
//        ApiModelList<T> list = null;
//        if (jsonObject != null && jsonObject.has(name)) {
//            list = new ApiModelList<>(item);
//            list.parseJson(jsonObject.get(name).toString());
//        }
//        return list;
//    }
}

package utils;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;


public class JsonUtils {

    private static final Gson gson = new Gson();

    public static <T> T readJsonFile(String filePath, Class<T> clazz) throws IOException {
        T obj = gson.fromJson(new FileReader(filePath), clazz);
        return obj;
    }
}

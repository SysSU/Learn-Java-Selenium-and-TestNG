package utils;

import java.io.FileReader;
import com.google.gson.Gson;

public class Data {

  public Object getJsonTestData(String filename, Class<?> type) {
    Gson gson = new Gson();
    Object object;

    try {
      FileReader jsonFile = new FileReader(
          System.getProperty("user.dir") + "\\src\\test\\java\\data\\" + filename + ".json");
      object = gson.fromJson(jsonFile, type);
    } catch (Exception e) {
      throw new Error("Error reading JSON file: " + e.getMessage());
    }

    if (type.isInstance(object)) {
      return object;
    } else {
      throw new Error("Object is not instance of " + type.getName());
    }

  }

  public String getProjectDirectory() {
    return System.getProperty("user.dir") + "\\";
  }

}

/*
 * Copyright 2015 Jacques Berger.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Le fichier UTF-8 file a été modifié le 19 avril 2017 et diffère de la version originale(2015) de M. Jacques Berger de la façon suivante:
 * 
 * 
 * Fonction originale:
 * 
 *  public static String loadFileIntoString(String filePath) throws FileNotFoundException, IOException {
 *           return IOUtils.toString(new FileInputStream(filePath), "UTF-8");
 *  }
 * 
 * 
 * Fonction modifiée:
 * 
 * public static String loadFileIntoString(String filePath) throws FileNotFoundException, IOException {
        FileInputStream file = new FileInputStream(filePath);
        String temp = IOUtils.toString(file, "UTF-8");
        file.close();
        return temp;
    }
 */
package refund;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;



public class Utf8File {

    public static String loadFileIntoString(String filePath) throws FileNotFoundException, IOException {
        FileInputStream file = new FileInputStream(filePath);
        String temp = IOUtils.toString(file, "UTF-8");
        file.close();
        return temp;
    }
    
    public static void saveStringIntoFile(String filePath, String content) throws IOException {
        File f = new File(filePath);
        FileUtils.writeStringToFile(f, content, "UTF-8");
    }
}
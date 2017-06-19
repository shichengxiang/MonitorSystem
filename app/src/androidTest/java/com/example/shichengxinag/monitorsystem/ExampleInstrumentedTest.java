package com.example.shichengxinag.monitorsystem;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.shichengxinag.monitorsystem", appContext.getPackageName());
    }
    @Test
    public void gen() {

        File file = new File("E:/dimens.xml");//这里如果找不到使用绝对路径即可
        BufferedReader reader = null;
//添加分辨率
        StringBuilder hdpi = new StringBuilder();
        StringBuilder xhdpi = new StringBuilder();
        StringBuilder xxhdpi = new StringBuilder();
        StringBuilder xxxhdpi = new StringBuilder();

        try {
            System.out.println("生成不同分辨率：");
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束

            while ((tempString = reader.readLine()) != null) {

                if (tempString.contains("</dimen>")) {
                    //tempString = tempString.replaceAll(" ", "");
                    String start = tempString.substring(0, tempString.indexOf(">") + 1);
                    String end = tempString.substring(tempString.lastIndexOf("<") - 2);
                    int num = Integer.valueOf(tempString.substring(tempString.indexOf(">") + 1, tempString.indexOf("</dimen>") - 2));

                    //主要核心就再这里了，如下3种分辨率分别缩小 0.6、0.75、0.9倍(根据实际情况自己随意DIY)
                    hdpi.append(start).append((int) Math.round(num * 0.6)).append(end).append("\n");
                    xhdpi.append(start).append((int) Math.round(num * 0.75)).append(end).append("\n");
                    xxhdpi.append(start).append((int) Math.round(num * 0.9)).append(end).append("\n");
                    xxxhdpi.append(tempString).append("\n");

                } else {
                    hdpi.append(tempString).append("\n");
                    xhdpi.append(tempString).append("\n");
                    xxhdpi.append(tempString).append("\n");
                    xxxhdpi.append(tempString).append("\n");
                }
                line++;
            }
            reader.close();

            String sw480file = "E:/values-hdpi/dimens.xml";
            String sw600file = "E:/values-xhdpi/dimens.xml";
            String sw720file = "E:/values-xxhdpi/dimens.xml";
            String sw800file = "E:/values-xxxhdpi/dimens.xml";
            writeFile(sw480file, hdpi.toString());
            writeFile(sw600file, xhdpi.toString());
            writeFile(sw720file, xxhdpi.toString());
            writeFile(sw800file, xxxhdpi.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void writeFile(String file, String text) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();
    }
}

package piecec.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by Andesite on 1/10/2015.
 */
public abstract class Vue {
    protected String readInput(String defaultValue) {
        String s = defaultValue;
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        try {
            s = buffer.readLine();
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        return s;
    }
}

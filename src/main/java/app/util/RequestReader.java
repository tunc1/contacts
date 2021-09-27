package app.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class RequestReader
{
    public String read(HttpServletRequest request) throws IOException
    {
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader reader=request.getReader())
        {
            String line;
            while((line=reader.readLine())!=null)
                stringBuilder.append(line).append('\n');
        }
        return stringBuilder.toString();
    }
}

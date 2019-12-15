import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


public class testclass {
    public static void main(String[] args) throws IOException, IOException {

        String urlString = "http://export.finam.ru/POLY_170620_170623.txt?market=1&em=175924&code=POLY&apply=0&df=20&mf=5&yf=2017&from=20.06.2017&dt=23&" +
                "mt=5&yt=2017&to=23.06.2017&p=8&f=POLY_170620_170623&e=.txt&cn=POLY&dtf=1&tmf=1&" +
                "MSOR=1&mstime=on&mstimever=1&sep=1&sep2=1&datf=1&at=1";
        URL url = new URL(urlString);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setConnectTimeout(60000);
        conn.setRequestMethod("GET");
// подготовили коннект

// Т.к. запрос у нас GET, то сразу принимаем входящие данные.
// Вот тут как раз (при открытии InputStream ) и происходит отправка GET запроса на сервер.
        InputStream inStream = conn.getInputStream();
// Т.к. файл у нас бинарный, открываем ReadableByteChannel и создаем файл
        ReadableByteChannel rbc = Channels.newChannel(conn.getInputStream());
        FileOutputStream fos = new FileOutputStream("input.txt");

// Перенаправляем данные из ReadableByteChannel прямо канал файла.
// Говорят, так быстрее, чем по одному байту вычитывать из потока и писать в файл.
        long filePosition = 0;
        long transferedBytes = fos.getChannel().transferFrom(rbc, filePosition, Long.MAX_VALUE);

        while(transferedBytes == Long.MAX_VALUE){
            filePosition += transferedBytes;
            transferedBytes = fos.getChannel().transferFrom(rbc, filePosition, Long.MAX_VALUE);
        }
        rbc.close();
        fos.close();
    }
}
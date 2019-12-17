import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadFromWeb {
        public String getUrl(
                //  https://habr.com/ru/post/332700//
                String code,
                String year_start,
                String month_start,
                String day_start,
                String year_end,
                String month_end,
                String day_end,
                String e,
                String market,
                String em,
                String p,
                String yf,
                String yt,
                String dtf,
                String tmf,
                String MSOR,
                String mstimever,
                String sep,
                String sep2,
                String datf,
                String at,
                String df,
                String mf,
                String dt,
                String mt)
        {

            String url = "http://export.finam.ru/"+
                    code+'_'+
                    year_start+
                    month_start+
                    day_start+'_'+
                    year_end+
                    month_end+
                    day_end+
                    e+
                    "?market="+market+
                    "&em="+em+
                    "&code="+code+
                    "&apply=0&df="+df+
                    "&mf="+mf+
                    "&yf="+yf+
                    "&from="+day_start+"."+month_start+"."+
                    yf+
                    "&dt="+dt+
                    "&mt="+mt+
                    "&yt="+yt+
                    "&to="+day_end+"."+month_end+"."+
                    yt+
                    "&p="+p+
                    "&f="+code+"_"+
                    year_start+
                    month_start+
                    day_start+"_"+
                    year_end+
                    month_end+
                    day_end+
                    "&e="+e+
                    "&cn="+code+
                    "&dtf="+dtf+
                    "&tmf="+tmf+
                    "&MSOR="+MSOR+
                    "&mstimever="+
                    mstimever+
                    "&sep="+sep+
                    "&sep2="+sep2+
                    "&datf="+datf+
                    "&at="+at;
            return url;
        }
        public File file(String urlString) throws IOException {

            URLConnection conn = new URL(urlString).openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            conn.connect();
// подготовили коннект

// Т.к. запрос у нас GET, то сразу принимаем входящие данные.
// Вот тут как раз (при открытии InputStream ) и происходит отправка GET запроса на сервер.
// Т.к. файл у нас бинарный, открываем ReadableByteChannel и создаем файл
            ReadableByteChannel rbc = Channels.newChannel(conn.getInputStream());
            FileOutputStream fos = new FileOutputStream("input.csv");

// Перенаправляем данные из ReadableByteChannel прямо канал файла.
// Говорят, так быстрее, чем по одному байту вычитывать из потока и писать в файл.
            long filePosition = 0;
            long transferedBytes = fos.getChannel().transferFrom(rbc, filePosition, Long.MAX_VALUE);

            while (transferedBytes == Long.MAX_VALUE) {
                filePosition += transferedBytes;
                transferedBytes = fos.getChannel().transferFrom(rbc, filePosition, Long.MAX_VALUE);
            }
            rbc.close();
            fos.close();

            File file = new File("input.csv");
            return file;
        }

}

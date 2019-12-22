import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadFromWeb {
        public static String getUrl(
                //  https://habr.com/ru/post/332700//
                String code,
                String[] from,
                String[] to,
                String em
                /*String dtf,
                String tmf,
                String MSOR,
                String mstimever,
                String sep,
                String sep2,
                String datf,
                String at,*/
                )
        {
            String yf=from[0];

            String mf=null;
            String mf2=from[1];
            if(Integer.parseInt(from[1])<10) {
                mf =String.valueOf(Integer.parseInt(from[1].substring(1))-1);
            } else mf=String.valueOf(Integer.parseInt(from[1])-1);



            String df=null;
            String df2=from[2];
            if(Integer.parseInt(from[2])<10) {
                df =from[2].substring(1);
            } else df=from[2];

            String yt=to[0];

            String mt=null;
            String mt2=to[1];
            if(Integer.parseInt(to[1])<10) {
                mt =String.valueOf(Integer.parseInt(to[1].substring(1))-1);
            } else mt=String.valueOf(Integer.parseInt(from[1]));

            String dt=null;
            String dt2=to[2];
            if(Integer.parseInt(to[2])<10) {
                dt =to[2].substring(1);
            } else dt=to[2];

            System.out.println(yf+mf2+df2+"      "+yt+mt2+dt2);
            String url = "http://export.finam.ru/input"+code+".csv"+
                    /*code+'_'+
                    year_start+
                    month_start+
                    day_start+'_'+
                    year_end+
                    month_end+
                    day_end+
                    e+*/
                    "?market="+"1"+ //market+
                    "&em="+em+
                    "&code="+code+
                    "&apply=0"+
                    "&df="+df+
                    "&mf="+mf+
                    "&yf="+yf+
                    "&from="+df2+"."+mf2+"."+yf+
                    "&dt="+dt+
                    "&mt="+mt+
                    "&yt="+yt+
                    "&to="+dt2+"."+mt2+"."+yt+
                    "&p="+"8"+//p+
                    "&f="+code+"_"+
                    "&e="+".csv"+
                    "&cn="+code+
                    "&dtf="+"1"+//dtf+
                    "&tmf="+"1"+//tmf+
                    "&MSOR="+"1"+//MSOR+
                    "&mstime=on"+
                    "&mstimever="+"1"+//mstimever+
                    "&sep="+"3"+//sep+
                    "&sep2="+"1"+//sep2+
                    "&datf="+"1"+//datf+
                    "&at="+"1";//at
            System.out.println("Привет из geturl,  ссылка сгенерирована");
            System.out.println(url);
            return url;

        }
        public static File getfile(String urlString) throws IOException {
            /**
             * Скачивание файла с финама
             */

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
            System.out.println("Привет из getfile, файл скачан");
            return file;
        }

}

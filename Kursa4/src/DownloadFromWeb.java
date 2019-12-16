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

}

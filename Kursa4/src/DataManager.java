import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;


public class DataManager {
    /**
     * Все файлы изначально загружаются со всеми столбцами, предлагамыми, финамом.
     * Название инструмента, период(в минутах), дата(гггг-мм-чч), время(чч-мм-сс), цена открытия,
     * максимальная ц., минимальная ц., ц.закрытия, объем торгов
     */
    static File inputfile;
    static String instrument;
    double[] open;
    double[] high;
    double[] low;
    double[] close;
    double[] volume;
    double[] date;

    public static String getInstrument() {
        try {
            readInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return instrument;
    }

    public static void setFile(File file) {
        DataManager.inputfile = file;
    }

    //Чтение первоначального файла
    public static void readInput() throws IOException {
        String line;
        BufferedReader br = new BufferedReader(new FileReader(inputfile));
        instrument=br.readLine().split(";",0)[0];
        instrument=br.readLine().split(";",0)[0];

    }
    public static double[] mnk(double[] y){
        /**Метод наименших квадратов
         * @return массив со значениями а и б для x=ax+b
         *
         */
        double[] r = new double[2];
        int n = y.length;
        double Y = 0;
        double x_sqr = 0;
        double xy = 0;
        double sum_x = 0;

        double[] x = new double[n];

        for (int i = 0; i < n; i++) {
            x[i] = i;
            Y += y[i];
            x_sqr += x[i] * x[i];
            xy += x[i] * y[i];
            sum_x += x[i];
        }
        r[0] = (n * xy - sum_x*Y)/(n * x_sqr - sum_x * sum_x);
        r[1] = (Y - r[0] * sum_x) / n;
        return r;
    }

    public static double[] sqrtStat(double[] y){
        /**
         * @return a,b,c для y=ax^2+bx+c
         */
        double[] r = new double[3];
        double n = y.length;
        double Y = 0;
        double xy = 0;

        double sum_x2y = 0;
        double sum_x = 0;
        double sum_x2 = 0;
        double sum_x3 = 0;
        double sum_x4 = 0;

        for (int i = 0; i < n; i++) {
            Y += y[i];
            xy += y[i] * i;
            sum_x2y += y[i] * i * i;
            sum_x += i;
            sum_x2 += i*i;
            sum_x3 += i*i*i;
            sum_x4 += i*i*i*i;
        }

        //Расчет определеителей
        double det = n * sum_x2 * sum_x4 + sum_x3 * sum_x * sum_x2 + sum_x3 * sum_x * sum_x2
                - sum_x2 * sum_x2 * sum_x2 - sum_x3 * sum_x3 * n - sum_x4 * sum_x * sum_x;
        double det1 = n * sum_x2 * sum_x2y + Y * sum_x * sum_x3 + xy * sum_x * sum_x2
                - Y * sum_x2 * sum_x2 - sum_x3 * n * xy - sum_x2y * sum_x * sum_x;
        double det2 = n * xy * sum_x4 + sum_x2y * sum_x * sum_x2 + sum_x3 * Y * sum_x2
                - sum_x2 * sum_x2 * xy - sum_x3 * sum_x2y * n - sum_x4 * Y * sum_x;
        double det3 = Y * sum_x2 * sum_x4 + sum_x3 * xy * sum_x2 + sum_x3 * sum_x * sum_x2y
                - sum_x2 * sum_x2 * sum_x2y - sum_x3 * sum_x3 * Y - sum_x4 * sum_x * xy;
        //Непосредственно решение
        r[0] = det1/det;
        r[1] = det2/det;
        r[2] = det3/det;

        return r;
    }

    public static double[] getdata(int trigger) throws IOException, ParseException {
        /**
         * Метод возвращает массив по заданному параметру
         * @param trigger 1-цены открытия
         *                2-максимальные цены
         *                3-минимальные цены
         *                4-цены закрытия
         *                5-объем торгов
         */

        //Подсчет количества строк в файле
        String line;
        BufferedReader br = new BufferedReader(new FileReader(inputfile));
        int i = 0;
        line = br.readLine();
        while ((line = br.readLine()) != null) {
            i++;
        }


        //Болванки нужного размера для массивов данных
        double[] open = new double[i];
        double[] high = new double[i];
        double[] low = new double[i];
        double[] close = new double[i];
        double[] volume = new double[i];


        //Запись в массивы данных с файла по стобцам
        BufferedReader br2 = new BufferedReader(new FileReader(inputfile));
        i=0;
        line = br2.readLine();
        while ((line = br2.readLine()) != null) {
            String[] cols = line.split(";");
            open[i]= Double.parseDouble(cols[4]);
            high[i]= Double.parseDouble(cols[5]);
            low[i]= Double.parseDouble(cols[6]);
            close[i]= Double.parseDouble(cols[7]);
            volume[i]= Double.parseDouble(cols[8]);

            i++;
        }
        switch(trigger)

    {
        case (1): {
            return open;
        }
        case (2): {
            return high;
        }
        case (3): {
            return low;
        }
        case (4): {
            return close;
        }
        case (5): {
            return volume;
        }
    }
        br.close();
        br2.close();
        return null;
}

    public static String[] getdate() throws IOException {
        /**
         * Считывает дату из файла
         */
        String line;
        BufferedReader br = new BufferedReader(new FileReader(inputfile));
        int i = 0;
        line = br.readLine();
        while ((line = br.readLine()) != null) {
            i++;
        }
        String[] date=new String[i];
        BufferedReader br2 = new BufferedReader(new FileReader(inputfile));
        i=0;
        line = br2.readLine();
        while ((line = br2.readLine()) != null) {
            String[] cols = line.split(";");
            date[i]=cols[2];
            i++;
        }
        return date;
    }

    public static ArrayList<String> comboNameArrList() throws IOException {
        /**
         * Метод заполняет комбо бокс при выборе инструментов для загрузки из инета
         */
        ArrayList<String> list = new ArrayList<>();
        String line;
        BufferedReader br = new BufferedReader(new FileReader("ListingSecurityList.csv"));
        int i = 0;
        line = br.readLine();
        while ((line = br.readLine()) != null) {
            String cols[]=line.split(";");
            list.add(cols[0]);

            i++;
        }
        return list;
    }

}

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class DataManager {
    /**
     * Все файлы изначально загружаются со всеми столбцами, предлагамыми, финамом.
     * Название инструмента, период(в минутах), дата(гггг-мм-чч), время(чч-мм-сс), цена открытия, ц.закрытия,
     * максимальная ц., минимальная ц., ц.закрытия, объем торгов
     */
    static File inputfile;
    static String instrument;

    public static String getInstrument() {
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

        while ((line = br.readLine()) != null) {
            String[] cols = line.split(";");
            System.out.println("Coulmn 4= " + cols[4] + " , Column 5=" + cols[5]);
        }

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

}

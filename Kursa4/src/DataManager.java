import java.io.File;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataManager {
    //Создание нового файла, по заданным пользователем аргументам
    public void makeNewTxt(File inputFile){}
    public void makeNewCsv(/*File inputFile*/){}

    //Чтение первоначального файла
    public void readInput(File inputFile){
        String extension=inputExtension(inputFile.getName());
        if extension

    }
    private String inputExtension(String fileName){
        /**
         @param  fileName Вставляем строку-название файла
         @return Метод, с помощью регулярных выражений определяет расширение файла, в данном случае(csv или txt)
         и возвращает соотвествующее расширение
         */
        String textExtension="";
        Pattern pattern = Pattern.compile(".{3}$");
        Matcher matcher = pattern.matcher(fileName);
        while (matcher.find()) {
            textExtension=fileName.substring(matcher.start(), matcher.end());
        }
        if (textExtension=="txt"){
            return "txt";
        }
        if (textExtension=="csv"){
            return "csv";
        }
        return "Неподдерживаемое расширение файла";
    }

}

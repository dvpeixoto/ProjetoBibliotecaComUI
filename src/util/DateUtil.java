package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * Classe para recebimento de datas
 *
 * @author Tainara Specht
 * @author Diego Peixoto
 */
public class DateUtil {
    /**
     * Método para formatar uma data do tipo String para tipo Date;
     * @param data - recebe a data em String;
     * @return retorna a data formatada em Date;
     * @throws ParseException - tratamento de exceção;
     */
    public static Date stringToDate(String data) throws ParseException {
        return (new SimpleDateFormat("dd/MM/yyyy").parse(data));
    }

    /**
     * Método para formatar uma data do tipo String para tipo DateHour;
     * @param data - recebe a data e hora em String;
     * @return retorna a data formatada em DateHour;
     * @throws ParseException - tratamento de exceção;
     */
    public static Date stringToDateHour(String data) throws ParseException {
        return (new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(data));
    }

    /**
     * Método para formatar uma data do tipo Date para tipo String;
     * @param data - recebe a data em Date;
     * @return retorna a data formatada em String;
     */
    public static String dateToString(Date data) {
        return (new SimpleDateFormat("dd/MM/yyyy").format(data));
    }

    /**
     * Método para formatar uma data do tipo dateHour para tipo String;
     * @param data - recebe a data em dateHour;
     * @return retorna a data formatada em String;
     */
    public static String dateHourToString(Date data) {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataString = formatador.format(data);
        return (dataString);
    }

    /**
     * Método booleano que verifica se a data está em um formato padrão definido;
     * @param data - recebe a data;
     * @return retorna 'true' ou 'false';
     */
    public static boolean verificaData(String data) {
        return (data.matches("\\d{2}/\\d{2}/\\d{4}"));
    }

    /**
     * Método booleano que verifica se a data e a hora estão em formato padrão definido;
     * @param data - recebe a data e hora;
     * @return retorna 'true' ou 'false';
     */
    public static boolean verificaDataHora(String data) {
        return (data.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}"));
    }

    /**
     * Método a formatação de uma Date e transforma em String;
     * @param dateFormat - recebe o formato de uma data;
     * @return retorna um dateFormat em String;
     */
    public static String dateToString(DateFormat dateFormat) {
        return (new SimpleDateFormat("dd/MM/yyyy").format(dateFormat));
    }
}

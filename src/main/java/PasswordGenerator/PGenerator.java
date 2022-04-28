package PasswordGenerator;

import java.lang.reflect.Array;
import java.util.HashMap;

public class PGenerator {
    HashMap<String, String> pTemplates;
    String template;
    /**
     * шаблон   S - символ A-Z в верхнем регистре
     *          s - символ a-z в нижнем регистре
     *          d - число 0-9
     *          $ - спецсимвол
     *          r - случайный символ из всего набора
     *          \ - экранирование(вставка в пароль символа после экраном)
     *          R01,02,.. 99 - пароль из случайных символов указанной длины
     *
     *          Sssdddddd - дает пароль типа Abc123456, Rne147854
     *          R08 - дает пароль длиной 8 знаков из случайного набора
     *
     *          пустой шаблон генерирует случайный пароль из 16 символов
     * @param template
     */
    public PGenerator(String template){

        pTemplates = new HashMap<>();
        pTemplates.put("S","QWERTYUIOPASDFGHJKLZXCVBNM");
        pTemplates.put("s","qwertyuiopasdfghjklzxcvbnm");
        pTemplates.put("d","0123456789");
        pTemplates.put("$","!@#$%^&*()_+=-/|\\<>,.:;'\"[]{}?`~");
        pTemplates.put("r",pTemplates.get("S")+pTemplates.get("s")+pTemplates.get("d")+pTemplates.get("$"));
        if(template.isEmpty()) {
            this.template = "R16";
        }else{
            this.template = template;
        }

    }

    public String getPassword() {
        String result = "";
        for (int i = 0; i < template.length(); i++) {
            switch (template.charAt(i)) {
                case 'S':
                    result += pTemplates.get("S").charAt((int) (pTemplates.get("S").length() * Math.random()));
                    break;
                case 's':
                    result += pTemplates.get("s").charAt((int) (pTemplates.get("s").length() * Math.random()));
                    break;
                case 'd':
                    result += pTemplates.get("d").charAt((int) (pTemplates.get("d").length() * Math.random()));
                    break;
                case '$':
                    result += pTemplates.get("$").charAt((int) (pTemplates.get("$").length() * Math.random()));
                    break;
                case 'r':
                    result += pTemplates.get("r").charAt((int) (pTemplates.get("r").length() * Math.random()));
                    break;
                case 'R':
                    i++;
                    int n = (Integer.parseInt(String.valueOf(template.charAt(i)))) * 10;
                    i++;
                    n += (Integer.parseInt(String.valueOf(template.charAt(i))));
                    for (int j = 0; j < n; j++) {
                        result += pTemplates.get("r").charAt((int) (pTemplates.get("r").length() * Math.random()));
                    }
                    return result;
                default:
                    throw new RuntimeException();
            }
        }
        return result;
    }

}

package scv.paul.opriskback.entity;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.MappedSuperclass;
import java.io.IOException;
import java.io.Serializable;

/**
 * Базовый пустой абстрактный класс, для идентификации Entity проекта
 */
@MappedSuperclass
public abstract class AGenericOpriskEntity implements Serializable {
    // private static final long serialVersionUID = 1L;

    /**
     * Удаляет лидирующие и завершающие пробелы в тнепустых строках.
     * Рекомендуется к встраиванию в сеттеры классов сущнеостей для тех полей (логин, телефон, email и т.п., где это необходимо).
     * Решает проблему некорректной работы валидаторов типа @Length и @Size, настренных на ограничение длин значений.
     * Например, если "логин должен быть от 3 до 16 символов", то ведь неправильно будет валидировать логин из 4 пробелов :)
     *
     * @author Rostislav Dublin
     * @since 21.03.2017
     */
    public static String trim(String string) {
        return string == null ? null : string.trim();
    }

    public String toJSONString() {
        String jsonInString ="";
        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonInString = mapper.writeValueAsString(this);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonInString;
    }

}

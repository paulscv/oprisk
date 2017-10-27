package scv.paul.opriskback.dao;

import org.hibernate.Session;
import scv.paul.opriskback.entity.AGenericOpriskEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * БАЗОВЫЙ Интерфейс DAO для CRUD операций
 *
 * @author Skvortsov Pavel, Rostislav Dublin
 * @since March 2017
 */
public interface IGenericDao<T extends AGenericOpriskEntity, PK extends Serializable> {
    /**
     * Возвращает действительный клвсс сущности
     * @return
     */
    Class<? extends T> getDaoType();

    /**
     * Создаёт сущность в БД
     *
     * @param entity создаваемая сущность
     * @return созданная сущность
     */
    T create(T entity);

    /**
     * Возвращает экземпляр сущности её Id
     *
     * @param id id
     * @return экземпляр сущности
     */
    T get(PK id);

    /**
     * Обновляет имеющуюся сущность в БД
     *
     * @param entity сущность
     * @return обновлённая сущность
     */
    T update(T entity);

    /**
     * Обновляет имеющуюся сущность в БД
     *
     * @param id            id обновялемой сущности
     * @param fieldValueMap - карта поле->новое значение
     * @return обновлённая сущность
     */
    T update(PK id, Map<String, Object> fieldValueMap);

    /**
     * Удаляет имеющуюся сущность из БД
     *
     * @param entity удаляемая сущность
     * @return удалённая сущность
     */
    T delete(T entity);

    /**
     * @return полный список сущностей
     */
    List<T> getAll();

    /**
     * @return текущая Hibernate-сессия
     */
    Session getCurrentSession();
}

package dao.generic;

import java.util.List;

/**
 * Dao generic interface
 * 
 * @author Lucas Grégoire
 * @param <T> The type entity type
 */
public interface DAO<T> {

    /**
     * Gets all the entities
     * 
     * @return The list of entities objects
     */
    public List<T> findAll();

    /**
     * Gets one entity by its id
     * 
     * @param id The integer id
     * @return The entity object
     */
    public T findOneById( Integer id );

    /**
     * Insert or update an entity
     * 
     * @param entity The entity to save
     * @return The entity saved
     */
    public T save( T entity );

    /**
     * Delete the entity
     * 
     * @param entity The entity to delete
     * @return The status of the deletion
     */
    public boolean delete( T entity );

}

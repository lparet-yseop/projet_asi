package dao.generic;

import java.util.List;

import beans.utils.DatabaseBean;

/**
 * Dao generic interface
 * 
 * @author Lucas Grégoire
 * @param <T> The type entity type
 */
public interface DAO<T extends DatabaseBean> {

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
     * Insert an entity
     * 
     * @param entity The entity to insert
     * @return The entity inserted
     */
    public T insert( T entity );

    /**
     * Update an entity
     * 
     * @param entity The entity to update
     * @return The entity inserted
     */
    public T update( T entity );

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

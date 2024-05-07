package com.gtvt.backendcustomermanagement.model.clonebase;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Make [TEntity] can be cloneable records
 *
 * @author haind6
 */
public interface CloneableAto<TEntity> {

    /**
     * Get [TEntity]'s class
     * The body should be [TEntity].class
     *
     * @return [TEntity]'s class
     */
    Class<TEntity> getEntityClass();

    /**
     * Reset id of [TEntity]
     * Make sure [TEntity]'s id is clear after run to this method
     *
     * @param data: input data
     * @return new data after reset id
     */
    TEntity resetId(TEntity data);

    /**
     * Get [TEntity]'s id by [TEntity]
     *
     * @param data: input data
     * @return id if data
     */
    Long getId(TEntity data);

    /**
     * Get Repository of [TEntity]
     *
     * @return repository
     */
    JpaRepository<TEntity, Long> getRepository();
}

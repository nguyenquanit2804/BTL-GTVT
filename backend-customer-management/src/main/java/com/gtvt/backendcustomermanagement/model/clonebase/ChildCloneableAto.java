package com.gtvt.backendcustomermanagement.model.clonebase;

import java.util.List;

/**
 * Make [TEntity] is a `Child Entity` can be clone by itself or it's parents
 *
 * @author haind6
 *
 */
public interface ChildCloneableAto<TEntity> extends CloneableAto<TEntity> {
    /**
     * Get [CloneableAto] of it's parent
     *
     * @return [CloneableAto] of it's parent
     */
    CloneableAto<?> getParentCloneable();

    /**
     * Get it' records by it's [parentId]
     *
     * @param parentId: [parentId]
     * @return List of [TEntity] by [parentId]
     */
    List<TEntity> getByParentId(Long parentId);

    /**
     * Change [data] by itself and [parentId]
     * You should set [parentId] into the parent's column in [data] itself
     *
     * @param data:     current [data]
     * @param parentId: [parentId] for set
     * @return [TEntity] setup by [parentId]
     */
    TEntity doCloneByParentId(TEntity data, Long parentId);
}

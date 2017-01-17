package es.bhavishchandnani.kcmadridguide.manager.db;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

public interface DAOPersistable<T> {

    /**
     * Insert a element in DB
     * @param data shouldn't be null
     * @return 0 if data is null, id if insert is OK, INVALID_ID if insert fails
     */
    long insert(final @NonNull T data);

    /**
     * Update a element in DB
     * @param data shouldn't be null
     * @param id shouldn't be null
     * @return 0 if data is null or number of rows updated
     */
    int update(final @NonNull long id, final @NonNull T data);

    /**
     * Delete a element in DB
     * @param id shouldn't be null
     * @return number of rows deleted
     */
    int delete(final long id);

    /**
     * Delete all elements in DB
     * @return number of rows deleted
     */
    int deleteAll();

    /**
     * Query
     * @return a cursor
     */
    @Nullable
    Cursor queryCursor();

    /**
     * Query
     * @param id of the element
     * @return a element of the DB
     */
    T query(final long id);

    /**
     * Query
     * @return a list of elements of the DB
     */
    @Nullable
    List<T> query();
}

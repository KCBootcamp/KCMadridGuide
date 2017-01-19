package es.bhavishchandnani.kcmadridguide.model;

public interface IUpdatable<T> {

    void add(T element);
    void delete(T element);
    void edit(T newElement, long index);

}

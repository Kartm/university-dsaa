package dsaa.lab_8;

public interface IExecutor<T,R> {
    void execute(T elem);
    R getResult();
}
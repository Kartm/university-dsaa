package dsaa_jk.assignment_7;

public interface IExecutor<T,R> {
    void execute(T elem);
    R getResult();
}
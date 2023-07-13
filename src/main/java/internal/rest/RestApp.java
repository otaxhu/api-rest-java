package internal.rest;

public interface RestApp {
    void start() throws RestAppException;
    void bindRoutes() throws RestAppException;
}

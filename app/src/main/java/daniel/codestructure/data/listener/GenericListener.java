package daniel.codestructure.data.listener;

public interface GenericListener<T> {
    public void onSuccess(T result);

    public void onFailed(Throwable error);
}
package software.nealk.concurrent.retrievable;

public abstract class MethodBlockingRetrievableTask<T> extends RetrievableTask<T>{
	
	private T obj;
	private java.util.concurrent.Semaphore objSem;
	
	protected MethodBlockingRetrievableTask() {
		this.objSem = new java.util.concurrent.Semaphore(0, false);
	}

	@Override
	public final void run() {
		this.execute();
		this.objSem.release();
	}
	
	protected abstract void execute();
	
	protected final void setVal(T obj) {
		this.obj = obj;
	}

	@Override
	public final T getVal() throws InterruptedException {
		// TODO Auto-generated method stub
		this.objSem.acquire();
		return this.obj;
	}

}

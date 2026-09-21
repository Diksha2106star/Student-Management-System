package JdbcPackage;

public class StudentRunnable implements Runnable {
	private static int count = 0;

	public void run() {
		String threadName = Thread.currentThread().getName();
		for (int i = 1; i <= 5; i++) {
			synchronized (StudentRunnable.class) {
				count++;
				System.out.println(threadName + "added student. Total = " + count);
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Thread interrupted!");
			}
		}

	}
}
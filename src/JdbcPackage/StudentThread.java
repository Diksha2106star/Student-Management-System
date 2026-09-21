package JdbcPackage;

public class StudentThread extends Thread {
	private static int count = 0;

	public void run() {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + "started...");
		for (int i = 1; i <= 5; i++) {
			addStudent(threadName);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Thread interrupted");
			}
		}
		System.out.println(threadName + " finished ");
	}

	public static synchronized void addStudent(String threadName) {
		System.out.println(threadName + " is accessing count....");
		count++;
		System.out.println(threadName + " added student .Total = " + count);
	}

}

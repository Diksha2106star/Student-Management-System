package JdbcPackage;

public class ThreadMain {

	public static void main(String[] args) {
		StudentRunnable studentRunnable = new StudentRunnable();
		Thread thread1 = new Thread(studentRunnable);
		Thread thread2 = new Thread(studentRunnable);
		thread1.setName(" Student Thread 1");
		thread2.setName(" Student Thread 2");
		thread1.start();
		thread2.start();
	}

}

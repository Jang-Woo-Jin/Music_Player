package Music;

import  java.lang.Thread;//������Դϴ�***for test
public class timetest extends Thread {
	static int timeget=0;
    public void run() {
        timeget=0;
        
        while(true){//�����̰� ���� �ð������ִ°� ���ϼ��ؼ� �ӽ÷� �ð����ư��°� ������
        	try {
				this.sleep(10);	//0.01�ʸ��� ������Ʈ�Ǵ� �ð�
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	timeget++;
        }
    }

    public static int getTG() {
		return timeget;
	}

	public static void main(String[] args) {
    
    }

}

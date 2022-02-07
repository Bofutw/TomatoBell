import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


// 無法解決關閉鈴聲問題，問題可能出在重複呼叫，或者clip的特性
// 透過clip.stop寫在 EndTask解決
public class filetest {
    Clip clip;

    filetest() {
        Timer timer = new Timer();
        TimerTask Task = new play(); //this
        timer.schedule(Task, 0, 10 * 1000);
        EndTask endTask = new EndTask(timer);
        timer.schedule(endTask, 3 * 1000);

    }

    class play extends TimerTask {
        @Override
        public void run() {
            try {
                File file = new File("D:\\bell.wav");
                //FileInputStream infile = new FileInputStream(file);
                //AudioInputStream inAudio = new AudioInputStream(infile, null, 0);
//				//AudioFormat format = inAudio.getFormat();

                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(file));
//				clip.open(inAudio);
//				clip.setFramePosition(0);
                clip.start();


            } catch (Exception e1) {
                System.out.println(e1.toString());
            }
            // TODO Auto-generated method stub

        }
    }

    class EndTask extends TimerTask {
        private Timer timer;

        public EndTask(Timer timer) {
            this.timer = timer;
        }

        @Override
        public void run() {
            clip.stop();
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }
//		public static void main(String[] args) {
//			new filetest();
//			System.out.println("ok");
//		}
}
